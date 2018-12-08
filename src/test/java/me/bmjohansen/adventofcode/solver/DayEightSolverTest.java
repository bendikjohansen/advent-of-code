package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DayEightSolverTest {

    private final Solver solver = new DayEightSolver();

    @Test
    public void firstExampleTest() {
        final var input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

        final var result = solver.solveFirst(input);
        Assert.assertEquals(138, result);
    }

    @Test
    public void secondExampleTest() {
        final var input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

        final var result = solver.solveSecond(input);
        Assert.assertEquals(66, result);
    }

}
