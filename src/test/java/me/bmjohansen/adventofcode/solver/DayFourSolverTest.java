package me.bmjohansen.adventofcode.solver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bendik Mathias Johansen
 */
public class DayFourSolverTest {

    private final Solver solver = new DayFourSolver();

    @Test
    public void firstExampleTest() {
        var input = "[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:25] wakes up\n" +
                "[1518-11-04 00:02] Guard #99 begins shift\n" +
                "[1518-11-03 00:29] wakes up\n" +
                "[1518-11-03 00:24] falls asleep\n" +
                "[1518-11-05 00:45] falls asleep\n" +
                "[1518-11-02 00:50] wakes up\n" +
                "[1518-11-01 00:55] wakes up\n" +
                "[1518-11-04 00:36] falls asleep\n" +
                "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-01 00:30] falls asleep\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-04 00:46] wakes up\n" +
                "[1518-11-03 00:05] Guard #10 begins shift\n" +
                "[1518-11-05 00:03] Guard #99 begins shift\n" +
                "[1518-11-05 00:55] wakes up";

        var result = solver.solveFirst(input);
        Assert.assertEquals(240, result);
    }

    @Test
    public void secondExampleTest() {
        var input = "[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:25] wakes up\n" +
                "[1518-11-04 00:02] Guard #99 begins shift\n" +
                "[1518-11-03 00:29] wakes up\n" +
                "[1518-11-03 00:24] falls asleep\n" +
                "[1518-11-05 00:45] falls asleep\n" +
                "[1518-11-02 00:50] wakes up\n" +
                "[1518-11-01 00:55] wakes up\n" +
                "[1518-11-04 00:36] falls asleep\n" +
                "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-01 00:30] falls asleep\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-04 00:46] wakes up\n" +
                "[1518-11-03 00:05] Guard #10 begins shift\n" +
                "[1518-11-05 00:03] Guard #99 begins shift\n" +
                "[1518-11-05 00:55] wakes up";

        var result = solver.solveSecond(input);
        Assert.assertEquals(4455, result);
    }

}