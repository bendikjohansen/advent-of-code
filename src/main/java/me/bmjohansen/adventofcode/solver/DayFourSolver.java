package me.bmjohansen.adventofcode.solver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Bendik Mathias Johansen
 */
public class DayFourSolver implements Solver<Integer, Integer> {

    private class Guard {
        private final int id;
        private final int[] minuteCount = new int[60];
        private int minutesSlept = 0;

        public Guard(int id) {
            this.id = id;
        }
    }

    @Override
    public Integer solveFirst(String input) {
        var guards = parseInput(input);

        var idOfMostSlept = -1;
        var maxMinutesSlept = 0;
        for (var id : guards.keySet()) {
            var guard = guards.get(id);
            if (guard.minutesSlept > maxMinutesSlept) {
                idOfMostSlept = id;
                maxMinutesSlept = guard.minutesSlept;
            }
        }


        var guard = guards.get(idOfMostSlept);
        var minute = 0;
        var maxTimes = 0;
        for (var i = 0; i < guard.minuteCount.length; i++) {
            if (guard.minuteCount[i] > maxTimes) {
                maxTimes = guard.minuteCount[i];
                minute = i;
            }
        }

        return idOfMostSlept * minute;
    }

    @Override
    public Integer solveSecond(String input) {
        var guards = parseInput(input);

        var idOfMostFrequentSleeper = -1;
        var minuteSleptOnMost = -1;
        var timeSleptOnMinute = -1;

        for (var id : guards.keySet()) {
            var guard = guards.get(id);

            for (int minute = 0; minute < guard.minuteCount.length; minute++) {
                if (timeSleptOnMinute < guard.minuteCount[minute]) {
                    timeSleptOnMinute = guard.minuteCount[minute];
                    minuteSleptOnMost = minute;
                    idOfMostFrequentSleeper = id;
                }
            }
        }

        return minuteSleptOnMost * idOfMostFrequentSleeper;
    }

    private int findId(String line) {
        var id = line.substring(line.indexOf("#") + 1, line.indexOf(" begins"));
        return Integer.parseInt(id);
    }

    private HashMap<Integer, Guard> parseInput(String input) {
        var log = input.lines().sorted().toArray(String[]::new);

        var guards = new HashMap<Integer, Guard>();
        Guard currentGuard = new Guard(-1);
        for (int i = 0, j = 1; i < log.length - 1; i++, j++) {
            if (log[i].contains("#")) {
                var id = findId(log[i]);
                guards.putIfAbsent(id, new Guard(id));
                currentGuard = guards.get(id);
                continue;
            } else if (log[i].contains("wakes up")) {
                continue;
            }

            var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date start = null;
            Date end = null;
            try {
                start = sdf.parse(log[i].substring(1, 17));
                end = sdf.parse(log[j].substring(1, 17));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            var sameDay = start.getDay() == end.getDay();
            var correctHour = start.getHours() == 0;
            if (sameDay) {
                int minute = 0;
                if (correctHour) {
                    minute = start.getMinutes();
                }
                currentGuard.minutesSlept += end.getMinutes() - minute;
                for (; minute < end.getMinutes(); minute++) {
                    if (correctHour || start.getMinutes() + minute >= 60) {
                        currentGuard.minuteCount[minute] += 1;
                    }
                }
            }
        }

        return guards;
    }
}
