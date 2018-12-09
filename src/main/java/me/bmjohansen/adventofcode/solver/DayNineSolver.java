package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;

public class DayNineSolver implements Solver<Long, Long> {
    private static final long SPECIAL_VALUE = 23;
    private static final long MARBLES_TO_LEFT = 7;

    private class Marble {
        private final long value;
        private Marble previous;
        private Marble next;

        public Marble(long value) {
            this.value = value;
            previous = this;
            next = this;
        }
    }

    @Override
    public Long solveFirst(String input) {
        final int players = Integer.parseInt(input.split(" ")[0]);
        final long lastMarble = Long.parseLong(input.split(" ")[6]);
        final var playerScores = playMarbleGame(players, lastMarble);

        return playerScores.stream().max(Long::compareTo).orElse(0l);
    }

    @Override
    public Long solveSecond(String input) {
        final int players = Integer.parseInt(input.split(" ")[0]);
        final long lastMarble = Long.parseLong(input.split(" ")[6]) * 100;
        final var playerScores = playMarbleGame(players, lastMarble);

        return playerScores.stream().max(Long::compareTo).orElse(0l);
    }

    private ArrayList<Long> playMarbleGame(int players, long lastMarble) {
        var current = new Marble(0);

        final var scores = new ArrayList<Long>();
        for (var p = 0; p < players; p++) scores.add(0l);

        var player = 0;

        for (var marble = 1; marble <= lastMarble; marble++) {
            if (marble % SPECIAL_VALUE == 0) {
                final var oldScore = scores.get(player);
                for (var i = 0; i < MARBLES_TO_LEFT; i++) {
                    current = current.previous;
                }
                final var newScore = current.value + marble + oldScore;

                scores.set(player, newScore);
                current = remove(current);
            } else {
                current = current.next;
                current = insert(current, new Marble(marble));
            }

            player = (player + 1) % players;
        }

        return scores;
    }

    private Marble remove(Marble marble) {
        marble.next.previous = marble.previous;
        marble.previous.next = marble.next;
        return marble.next;
    }

    private Marble insert(Marble current, Marble newNext) {
        newNext.next = current.next;
        current.next = newNext;
        newNext.next.previous = newNext;
        newNext.previous = current;

        return newNext;
    }
}
