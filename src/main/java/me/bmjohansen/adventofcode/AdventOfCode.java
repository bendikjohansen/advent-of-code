package me.bmjohansen.adventofcode;

import me.bmjohansen.adventofcode.solver.DayTwoSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bendik Mathias Johansen
 */
public class AdventOfCode {
    public static void main(String[] args) {
        var solver = new DayTwoSolver();
        var input = readFile("input2.txt");

        var firstResult = solver.solveFirst(input);
        var secondResult = solver.solveSecond(input);

        System.out.println(firstResult);
        System.out.println(secondResult);
    }

    private static String readFile(String fileName) {
        StringBuilder input = new StringBuilder();
        try {
            var classLoader = AdventOfCode.class.getClassLoader();
            var reader = new BufferedReader(new FileReader(classLoader.getResource(fileName).getFile()));
            reader.lines().forEach(input::append);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return input.toString();
    }
}
