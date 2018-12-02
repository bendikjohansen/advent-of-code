package me.bmjohansen.adventofcode.solver;

import java.util.HashMap;

/**
 * @author Bendik Mathias Johansen
 */
public class DayOneSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solveFirst(String input) {
        return input.lines().mapToInt(Integer::parseInt).sum();
    }

    @Override
    public Integer solveSecond(String input) {
        var numberStream = input.lines().mapToInt(Integer::parseInt);

        var visited = new HashMap<Integer, Boolean>();
        visited.put(0, true);
        var foundRepeated = false;
        var sum = 0;
        var i = 0;
        var numberArray = numberStream.toArray();

        while (!foundRepeated) {
            sum += numberArray[i++];
            foundRepeated = visited.getOrDefault(sum, false);
            visited.put(sum, true);

            if (i >= numberArray.length)
                i = 0;
        }

        return sum;
    }

}
