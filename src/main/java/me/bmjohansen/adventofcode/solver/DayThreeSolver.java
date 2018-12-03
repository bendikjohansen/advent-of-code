package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bendik Mathias Johansen
 */
public class DayThreeSolver implements Solver<Integer, Integer> {

    private class Area {
        private final int id;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Area(int id, int x, int y, int width, int height) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public Integer solveFirst(String input) {
        var areas = readAreas(input);
        var squareCount = getAreaOverlaps(areas);

        var overlaps = 0;
        for (var column : squareCount) {
            for (var cell : column) {
                if (cell > 1)
                    overlaps++;
            }
        }

        return overlaps;
    }

    @Override
    public Integer solveSecond(String input) {
        var areas = readAreas(input);
        var squareCount = getAreaOverlaps(areas);

        for (var area : areas) {
            if (!areaHasOverlap(area, squareCount)) {
                return area.id;
            }
        }

        return -1;
    }

    private boolean areaHasOverlap(Area area, int[][] squareCount) {
        for (int x = area.x; x < area.x + area.width; x++) {
            for (int y = area.y; y < area.y + area.height; y++) {
                if (squareCount[x][y] != 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private int[][] getAreaOverlaps(List<Area> areas) {
        var maxWidth = areas.stream()
                .mapToInt(area -> area.x + area.width)
                .max()
                .getAsInt();
        var maxHeight = areas.stream()
                .mapToInt(area -> area.y + area.height)
                .max()
                .getAsInt();

        var squareCount = new int[maxWidth][maxHeight];
        for (var area : areas) {
            fillArea(area, squareCount);
        }

        return squareCount;
    }

    private void fillArea(Area area, int[][] grid) {
        for(var x = 0; x < area.width; x++) {
            for(var y = 0; y < area.height; y++) {
                grid[x + area.x][y + area.y] += 1;
            }
        }
    }

    private List<Area> readAreas(String list) {
        var areas = new ArrayList<Area>();

        for (var area : list.split("\n")) {
            var id = Integer.parseInt(area.substring(1, area.indexOf(" @")));
            var x = Integer.parseInt(area.substring(area.indexOf("@ ") + 2, area.indexOf(',')));
            var y = Integer.parseInt(area.substring(area.indexOf(',') + 1, area.indexOf(':')));
            var width = Integer.parseInt(area.substring(area.indexOf(": ") + 2, area.indexOf('x')));
            var height = Integer.parseInt(area.substring(area.indexOf('x') + 1));

            areas.add(new Area(id, x, y, width, height));
        }

        return areas;
    }
}
