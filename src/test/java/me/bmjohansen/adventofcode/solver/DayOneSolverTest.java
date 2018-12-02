package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DayOneSolverTest {

    private final Solver solver = new DayOneSolver();

    @Test
    public void firstExampleTest() {
        var input = "+1\n+1\n+1";

        var result = solver.solveFirst(input);
        Assert.assertEquals(3, result);
    }

    @Test
    public void secondExampleTest() {
        var input = "+1\n+1\n-2";

        var result = solver.solveFirst(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void thirdExampleTest() {
        var input = "-1\n-2\n-3";

        var result = solver.solveFirst(input);
        Assert.assertEquals(-6, result);
    }

    @Test
    public void fourthExampleTest() {
        var input = "+1\n-1";

        var result = solver.solveSecond(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void fifthExampleTest() {
        var input = "+3\n+3\n+4\n-2\n-4";

        var result = solver.solveSecond(input);
        Assert.assertEquals(10, result);
    }

    @Test
    public void sixthExampleTest() {
        var input = "-6\n+3\n+8\n+5\n-6";

        var result = solver.solveSecond(input);
        Assert.assertEquals(5, result);
    }

    @Test
    public void seventhExampleTest() {
        var input = "+7\n+7\n-2\n-7\n-4";

        var result = solver.solveSecond(input);
        Assert.assertEquals(14, result);
    }

}
