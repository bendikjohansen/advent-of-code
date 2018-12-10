package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bendik Mathias Johansen
 */
public class DayTenSolver implements Solver<String, Integer> {

    private static final String POSITION_START = "position=<";
    private static final String POSITION_END = "> ";
    private static final String VELOCITY_START = "velocity=<";
    private static final String SEPARATOR = ", ";

    private class Point {
        private final int vx;
        private final int vy;
        private final int x;
        private final int y;

        private Point(int vx, int vy, int x, int y) {
            this.vx = vx;
            this.vy = vy;
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public String solveFirst(String input) {
        var points = getPoints(input);
        var bounds = getBounds(points);

        var shrinks = true;
        while (shrinks) {
            final var nextPoints = getNextFrame(points);
            final var nextBounds = getBounds(nextPoints);

            shrinks = boundsShrank(bounds, nextBounds);
            if (shrinks) {
                points = nextPoints;
                bounds = nextBounds;
            }
        }

        return toStringMap(points, bounds);
    }

    @Override
    public Integer solveSecond(String input) {
        var points = getPoints(input);
        var bounds = getBounds(points);

        var counter = 0;
        var shrinks = true;
        while (shrinks) {
            final var nextPoints = getNextFrame(points);
            final var nextBounds = getBounds(nextPoints);

            shrinks = boundsShrank(bounds, nextBounds);
            if (shrinks) {
                counter++;
                points = nextPoints;
                bounds = nextBounds;
            }
        }

        return counter;
    }

    private boolean boundsShrank(int[] bounds, int[] nextBounds) {
        final var minXBigger = bounds[0] < nextBounds[0];
        final var minYBigger = bounds[1] < nextBounds[1];
        final var maxXLess = bounds[2] > nextBounds[2];
        final var maxYLess = bounds[3] > nextBounds[3];

        return minXBigger && minYBigger && maxXLess && maxYLess;
    }

    private List<Point> getNextFrame(List<Point> points) {
        final var nextPoints = new ArrayList<Point>(points.size());

        for (final var point : points) {
            var next = new Point(
                    point.vx,
                    point.vy,
                    point.x + point.vx,
                    point.y + point.vy
            );
            nextPoints.add(next);
        }

        return nextPoints;
    }

    private List<Point> getPoints(String input) {
        final var points = new ArrayList<Point>(input.split("\n").length);

        input.lines().forEach(line -> {
            final var posStart = POSITION_START.length();
            final var posEnd = line.indexOf(POSITION_END);
            final var velStart = line.indexOf(VELOCITY_START) + VELOCITY_START.length();
            final var velEnd = line.length() - 1;

            final var position = line.substring(posStart, posEnd).split(SEPARATOR);
            final var velocity = line.substring(velStart, velEnd).split(SEPARATOR);

            points.add(new Point(
                    Integer.parseInt(velocity[0].trim()),
                    Integer.parseInt(velocity[1].trim()),
                    Integer.parseInt(position[0].trim()),
                    Integer.parseInt(position[1].trim())
            ));
        });

        return points;
    }

    private int[] getBounds(List<Point> points) {
        var minX = points.get(0).x;
        var minY = points.get(0).y;
        var maxX = points.get(0).x;
        var maxY = points.get(0).y;

        for (final var point : points) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
        }

        return new int[] {minX, minY, maxX, maxY};
    }

    private String toStringMap(List<Point> points, int[] bounds) {
        final var width = bounds[2] - bounds[0] + 1;
        final var height = bounds[3] - bounds[1] + 1;
        final var map = new boolean[height][width];
        final var mapBuilder = new StringBuilder(height * width);

        for (final var point : points) {
            map[point.y - bounds[1]][point.x - bounds[0]] = true;
        }

        for (final var column : map) {
            for (final var light : column) {
                if (light) {
                    mapBuilder.append('#');
                } else {
                    mapBuilder.append('.');
                }
            }
            mapBuilder.append('\n');
        }

        return mapBuilder.toString().trim();
    }

}
