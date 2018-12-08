package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DaySevenSolverTest {

    private final Solver solver = new DaySevenSolver();

    @Test
    public void firstExampleTest() {
        final var input = "Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";

        final var result = solver.solveFirst(input);
        Assert.assertEquals("CABDFE", result);
    }

    @Test
    @Ignore("dependent on a second input")
    public void secondExampleTest() {
        final var input = "Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin.";

        final var result = solver.solveSecond(input);
        Assert.assertEquals(315, result);
    }

}
