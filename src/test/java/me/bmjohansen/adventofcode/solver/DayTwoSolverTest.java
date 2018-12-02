package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DayTwoSolverTest {

    private final Solver solver = new DayTwoSolver();

    @Test
    public void firstExampleTest() {
        var input = String.join("\n", new String[]{
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"
        });

        var result = solver.solveFirst(input);
        Assert.assertEquals(12, result  );
    }

    @Test
    public void secondExampleTest() {
        var input = String.join("\n", new String[]{
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
        });

        var result = solver.solveSecond(input);
        Assert.assertEquals("fgij", result);
    }
}
