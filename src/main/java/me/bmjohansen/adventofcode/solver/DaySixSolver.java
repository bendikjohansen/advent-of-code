package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bendik Mathias Johansen
 */
public class DaySixSolver implements Solver<Integer, Integer> {
    @FunctionalInterface
    private interface HandlePoint {
        void apply(Point current);
    }

    private class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance(Point target) {
            return Math.abs(target.x - this.x) + Math.abs(target.y - this.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            var point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    @Override
    public Integer solveFirst(String input) {
        var closestPoints = new ArrayList<Point>();
        var points = getPoints(input);
        var relevantPoints = new ArrayList<>(points);

        var minMax = getMinMax(points);

        foreachPoint(minMax, current -> {
            var onEdge = current.x == minMax[0] || current.x == minMax[2]
                        || current.y == minMax[1] || current.y == minMax[3];


            var closest = getClosest(current, points);
            if (onEdge) {
                if (relevantPoints.contains(closest)) {
                    relevantPoints.remove(closest);
                }
            } else {
                closestPoints.add(closest);
            }
        });

        var areas = new HashMap<Point, Integer>();
        for (var point : closestPoints) {
            areas.put(point, areas.getOrDefault(point, 0) + 1);
        }

        var biggestArea = 0;
        for (var point : relevantPoints) {
            if (areas.getOrDefault(point, 0) > biggestArea) {
                biggestArea = areas.get(point);
            }
        }

        return biggestArea;
    }

    @Override
    public Integer solveSecond(String input) {
        final var maxDistance = 10000;
        var points = getPoints(input);
        var pointsWithinLimit = new AtomicInteger();
        var minMax = getMinMax(points);

        foreachPoint(minMax, current -> {
            int totalDistance = 0;
            for (int i = 0; i < points.size() && totalDistance < maxDistance; i++) {
                totalDistance += current.distance(points.get(i));
            }
            if (totalDistance < maxDistance) {
                pointsWithinLimit.getAndIncrement();
            }
        });

        return pointsWithinLimit.get();
    }

    private Point getClosest(Point point, List<Point> points) {
        var minDistance = point.distance(points.get(0));
        var closest = points.get(0);

        for (var i = 1; i < points.size(); i++) {
            var distance = point.distance(points.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closest = points.get(i);
            } else if (distance == minDistance) {
                closest = null;
            }
        }

        return closest;
    }

    private void foreachPoint(int[] minMax, HandlePoint handle) {
        for (int x = minMax[0]; x < minMax[2]; x++) {
            for (int y = minMax[1]; y < minMax[3]; y++) {
                handle.apply(new Point(x, y));
            }
        }
    }

    private int[] getMinMax(List<Point> points) {
        var minMax = new int[] {
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                Integer.MIN_VALUE
        };

        for (var point : points) {
            minMax[0] = Math.min(minMax[0], point.x);
            minMax[1] = Math.min(minMax[1], point.y);
            minMax[2] = Math.max(minMax[2], point.x);
            minMax[3] = Math.max(minMax[3], point.y);
        }

        return minMax;
    }

    private ArrayList<Point> getPoints(String input) {
        var lines = input.split("\n");
        var points = new ArrayList<Point>(lines.length);
        for (var line : lines) {
            var coordinates = line.split(", ");
            var point = new Point(
                    Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1])
            );
            points.add(point);
        }

        return points;
    }
}
