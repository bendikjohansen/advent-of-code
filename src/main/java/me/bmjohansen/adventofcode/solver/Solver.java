package me.bmjohansen.adventofcode.solver;

/**
 * @author Bendik Mathias Johansen
 *
 * @param <T>
 * @param <S>
 */
public interface Solver<T extends Comparable<T>, S extends Comparable<S>> {

    T solveFirst(String input);

    S solveSecond(String input);

}
