package me.bmjohansen.adventofcode.solver;

/**
 * @author Bendik Mathias Johansen
 */
public class DayFiveSolver implements Solver<Integer, Integer> {

    @Override
    public Integer solveFirst(String input) {
        var i = 0;
        var result = input;
        while (i < result.length() - 1) {
            if (reacts(result.charAt(i), result.charAt(i + 1))) {
                result = result.substring(0, i) + result.substring(i + 2);

                i = Math.max(0, i - 1);
            } else {
                i++;
            }
        }

        return result.length();
    }

    @Override
    public Integer solveSecond(String input) {
        var inputArray = input.toCharArray();
        var minValue = Integer.MAX_VALUE;

        for (char current = 'a'; current < 'z'; current++) {
            var upper = Character.toUpperCase(current);
            var builder = new StringBuilder();

            for (var c : inputArray) {
                if (c != current && c != upper) {
                    builder.append(c);
                }
            }

            var result = solveFirst(builder.toString());
            minValue = Math.min(minValue, result);
        }

        return minValue;
    }

    private boolean reacts(char current, char target) {
        return Math.abs(target - current) == 'a' - 'A';
    }

}
