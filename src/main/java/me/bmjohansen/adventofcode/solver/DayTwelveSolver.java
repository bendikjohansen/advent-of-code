package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bendik Mathias Johansen
 */
public class DayTwelveSolver implements Solver<Integer, Integer> {
    private static final int INITIAL_ENVIRONMENT_START = "initial state: ".length();
    private static final char PLANT_SYMBOL = '#';
    private static final int ENVIRONMENT_SIZE = 5;
    private static final int GENERATIONS = 20;

    private class Observation {
        private final boolean[] environment;
        private final boolean result;

        public Observation(boolean[] environment, boolean result) {
            this.environment = environment;
            this.result = result;
        }
    }

    @Override
    public Integer solveFirst(String input) {
        final var initialState = initialState(input.split("\n")[0]);
        final var observations = getObservations(input.split("\n\n")[1].trim());

        var genStart = 0;
        List<Boolean> prevGen = Arrays.asList(initialState);
        for (var gen = 0; gen < GENERATIONS; gen++) {
            final var curGen = new ArrayList<Boolean>(prevGen.size());
            final var start = -2;
            final var end = prevGen.size() + 1;
            for (var i = start; i < end; i++) {
                curGen.add(predict(getEnvironmentAround(i, prevGen), observations));
            }
            prevGen = curGen;
            genStart += start;
        }

        var sum = 0;
        for (var i = 0; i < prevGen.size(); i++) {
            if (prevGen.get(i)) {
                sum += genStart + i;
            }
        }

        return sum;
    }

    @Override
    public Integer solveSecond(String input) {
        return null;
    }

    private Boolean[] initialState(String input) {
        final var pots = input.substring(INITIAL_ENVIRONMENT_START).split("");
        final var initialState = new Boolean[pots.length];
        for (var i = 0; i < pots.length; i++) {
            initialState[i] = pots[i].charAt(0) == PLANT_SYMBOL;
        }

        return initialState;
    }

    private Observation[] getObservations(String input) {
        return input.lines()
                    .map(this::getObservation)
                    .toArray(Observation[]::new);
    }

    private Observation getObservation(String line) {
        final var plants = line.substring(0, ENVIRONMENT_SIZE).split("");
        final var environment = new boolean[ENVIRONMENT_SIZE];
        for (var i = 0; i < environment.length; i++) {
            environment[i] = plants[i].charAt(0) == PLANT_SYMBOL;
        }
        final var result = line.charAt(line.length() - 1) == PLANT_SYMBOL;
        return new Observation(environment, result);
    }

    private boolean[] getEnvironmentAround(int x, List<Boolean> state) {
        final var environment = new boolean[ENVIRONMENT_SIZE];
        for (int i = x - 2, j = 0; i < x + 3; i++, j++) {
            environment[j] = i < 0 || i >= state.size() ? false : state.get(i);
        }
        return environment;
    }

    private boolean predict(boolean[] environment, Observation[] observations) {
        for (var observation : observations) {
            if (Arrays.equals(observation.environment, environment)) {
                return observation.result;
            }
        }
        return false;
    }
}
