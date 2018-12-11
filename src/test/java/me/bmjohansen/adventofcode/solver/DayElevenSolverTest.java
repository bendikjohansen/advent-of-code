package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DayElevenSolverTest {

    private final Solver solver = new DayElevenSolver();

    @Test
    public void firstExampleTest() {
        final var input = "18";

        final var result = solver.solveFirst(input);
        Assert.assertEquals("33,45", result);
    }

    @Test
    public void secondExampleTest() {
        final var input = "42";

        final var result = solver.solveFirst(input);
        Assert.assertEquals("21,61", result);
    }

    @Test
    public void thirdExampleTest() {
        final var input = "18";

        final var result = solver.solveSecond(input);
        Assert.assertEquals("90,269,16", result);
    }

    @Test
    public void fourthExampleTest() {
        final var input = "42";

        final var result = solver.solveSecond(input);
        Assert.assertEquals("232,251,12", result);
    }
}
