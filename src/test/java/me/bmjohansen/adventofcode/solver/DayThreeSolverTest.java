package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

public class DayThreeSolverTest {

    private final Solver solver = new DayThreeSolver();

    @Test
    public void firstExampleTest() {
        var input = "#1 @ 1,3: 4x4\n" +
                    "#2 @ 3,1: 4x4\n" +
                    "#3 @ 5,5: 2x2";

        var result = solver.solveFirst(input);
        Assert.assertEquals(4, result);
    }

    @Test
    public void secondExampleTest() {
        var input = "#1 @ 1,3: 4x4\n" +
                    "#2 @ 3,1: 4x4\n" +
                    "#3 @ 5,5: 2x2";

        var result = solver.solveSecond(input);
        Assert.assertEquals(3, result);
    }

}
