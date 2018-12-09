package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

public class DayNineSolverTest {

    private final Solver solver = new DayNineSolver();

    @Test
    public void firstExampleTest() {
        final var input = "9 players; last marble is worth 25 points";

        final var result = 32;
        Assert.assertEquals(result, solver.solveFirst(input));
    }

    @Test
    public void secondExampleTest() {
        final var input = "10 players; last marble is worth 1618 points";

        final var result = 8317;
        Assert.assertEquals(result, solver.solveFirst(input));
    }

    @Test
    public void thirdExampleTest() {
        final var input = "13 players; last marble is worth 7999 points";

        final var result = 146373;
        Assert.assertEquals(result, solver.solveFirst(input));
    }

    @Test
    public void fourthExampleTest() {
        final var input = "17 players; last marble is worth 1104 points";

        final var result = 2764;
        Assert.assertEquals(result, solver.solveFirst(input));
    }

    @Test
    public void fifthExampleTest() {
        final var input = "21 players; last marble is worth 6111 points";

        final var result = 54718;
        Assert.assertEquals(result, solver.solveFirst(input));
    }

    @Test
    public void sixthExampleTest() {
        final var input = "30 players; last marble is worth 5807 points";

        final var result = 37305;
        Assert.assertEquals(result, solver.solveFirst(input));
    }

}
