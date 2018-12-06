package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DaySixSolverTest {

    private final Solver solver = new DaySixSolver();

    @Test
    public void firstExampleTest() {
        var input = "1, 1\n" +
                    "1, 6\n" +
                    "8, 3\n" +
                    "3, 4\n" +
                    "5, 5\n" +
                    "8, 9";

        var result = solver.solveFirst(input);
        Assert.assertEquals(17, result);
    }

    @Test
    @Ignore("dependent on a second input")
    public void secondExampleTest() {
        var input = "1, 1\n" +
                    "1, 6\n" +
                    "8, 3\n" +
                    "3, 4\n" +
                    "5, 5\n" +
                    "8, 9";

        var result = solver.solveSecond(input);
        Assert.assertEquals(30, result);
    }

}
