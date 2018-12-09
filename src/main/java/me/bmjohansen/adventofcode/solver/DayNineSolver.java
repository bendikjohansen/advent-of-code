package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;

public class DayNineSolver implements Solver<Integer, Integer> {
    private static final int SPECIAL_VALUE = 23;
    private static final int MARBLES_TO_LEFT = 7;

    @Override
    public Integer solveFirst(String input) {
        final int players = Integer.parseInt(input.split(" ")[0]);
        final int lastMarble = Integer.parseInt(input.split(" ")[6]);
        final var playerScores = playMarbleGame(players, lastMarble);

        return playerScores.stream().max(Integer::compareTo).orElse(-1);
    }

    @Override
    public Integer solveSecond(String input) {
        final int players = Integer.parseInt(input.split(" ")[0]);
        final int lastMarble = Integer.parseInt(input.split(" ")[6]);// * 100;
        final var playerScores = playMarbleGame(players, lastMarble);

        return playerScores.stream().max(Integer::compareTo).orElse(0);
    }

    private ArrayList<Integer> playMarbleGame(int players, int lastMarble) {
        final var circle = new ArrayList<Integer>();
        circle.add(0);
        final var playerScores = new ArrayList<Integer>(players);
        for (var p = 0; p < players; p++) playerScores.add(0);

        var player = 0;
        var position = 0;

        for (var marble = 1; marble <= lastMarble; marble++) {
            if (marble % SPECIAL_VALUE == 0) {
                final var score = playerScores.get(player);
                final var otherMarvel = position - MARBLES_TO_LEFT < 0 ?
                        circle.size() + position - MARBLES_TO_LEFT :
                        position - MARBLES_TO_LEFT;

                final var newScore = score + marble + circle.get(otherMarvel);

                playerScores.set(player, newScore);
                circle.remove(otherMarvel);
                position = otherMarvel;
            } else {
                final var nextPos = (position + 1) % circle.size() + 1;
                circle.add(nextPos, marble);

                position = nextPos;
            }
            player = (player + 1) % players;
        }
        return playerScores;
    }
}
