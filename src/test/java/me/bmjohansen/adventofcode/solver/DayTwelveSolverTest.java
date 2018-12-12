package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

public class DayTwelveSolverTest {

    private final Solver solver = new DayTwelveSolver();

    @Test
    public void firstExampleTest() {
        final var input = "initial state: #..#.#..##......###...###\n\n" +
                "...## => #\n" +
                "..#.. => #\n" +
                ".#... => #\n" +
                ".#.#. => #\n" +
                ".#.## => #\n" +
                ".##.. => #\n" +
                ".#### => #\n" +
                "#.#.# => #\n" +
                "#.### => #\n" +
                "##.#. => #\n" +
                "##.## => #\n" +
                "###.. => #\n" +
                "###.# => #\n" +
                "####. => #";

        final var result = solver.solveFirst(input);
        Assert.assertEquals(325, result);
    }

}
