package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DayFiveSolverTest {

    private final Solver solver = new DayFiveSolver();

    @Test
    public void firstExampleTest() {
        var input = "aA";

        var result = solver.solveFirst(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void secondExampleTest() {
        var input = "abBA";

        var result = solver.solveFirst(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void thirdExampleTest() {
        var input = "abAB";

        var result = solver.solveFirst(input);
        Assert.assertEquals(4, result);
    }

    @Test
    public void forthExampleTest() {
        var input = "aabAAB";

        var result = solver.solveFirst(input);
        Assert.assertEquals(6, result);
    }

    @Test
    public void fifthExampleTest() {
        var input = "dabAcCaCBAcCcaDA";

        var result = solver.solveSecond(input);
        Assert.assertEquals(4, result);
    }

}
