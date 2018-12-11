package me.bmjohansen.adventofcode.solver;

/**
 * @author Bendik Mathias Johansen
 */
public class DayElevenSolver implements Solver<String, String> {

    private static final int GRID_SIZE = 300;
    private static final int SQUARE_SIZE = 3;

    @Override
    public String solveFirst(String input) {
        final var serial = Integer.parseInt(input);
        final var grid = getValueGrid(serial);

        var maxValue = grid[0][0];
        var maxX = 0;
        var maxY = 0;
        for (var x = 0; x < GRID_SIZE - SQUARE_SIZE; x++) {
            for (var y = 0; y < GRID_SIZE - SQUARE_SIZE; y++) {
                final var squareSum = findSquareValue(x, y, SQUARE_SIZE, grid);
                if (maxValue < squareSum) {
                    maxValue = squareSum;
                    maxX = x;
                    maxY = y;
                }
            }
        }

        return maxX + "," + maxY;
    }

    @Override
    public String solveSecond(String input) {
        final var serial = Integer.parseInt(input);
        final var grid = getValueGrid(serial);

        var maxSize = 0;
        var maxValue = grid[0][0];
        var maxX = 0;
        var maxY = 0;
        for (var x = 0; x < GRID_SIZE; x++) {
            for (var y = 0; y < GRID_SIZE; y++) {
                var previousValue = 0;
                for (var s = 0; s < GRID_SIZE - x && s < GRID_SIZE - y; s++) {
                    var currentValue = previousValue;
                    currentValue += findValueAt(x, y, x + s, y + s, grid);
                    if (maxValue < currentValue) {
                        maxSize = s;
                        maxX = x;
                        maxY = y;
                        maxValue = currentValue;
                    }
                    previousValue = currentValue;
                }
            }
        }

        return String.format("%d,%d,%d", maxX, maxY, maxSize + 1);
    }

    private int[][] getValueGrid(int serial) {
        final var grid = new int[GRID_SIZE][GRID_SIZE];

        for (var x = 0; x < GRID_SIZE; x++) {
            for (var y = 0; y < GRID_SIZE; y++) {
                grid[x][y] = findGridPower(serial, x, y);
            }
        }

        return grid;
    }

    private int findGridPower(int serial, int x, int y) {
        final var rackId = x + 10;
        final var initialPower = rackId * y;
        final var serialPower = initialPower + serial;
        final var rackPower = serialPower * rackId;
        final var hundredth = (rackPower % 1000 - rackPower % 100) / 100;

        return hundredth - 5;
    }

    private int findSquareValue(int xPos, int yPos, int size, int[][] grid) {
        var sum = 0;
        for (var x = xPos; x < xPos + size; x++) {
            assert x < GRID_SIZE;
            for (var y = yPos; y < yPos + size; y++) {
                assert y < GRID_SIZE;

                sum += grid[x][y];
            }
        }
        return sum;
    }

    private int findValueAt(int fromX, int fromY, int toX, int toY, int[][] grid) {
        var sum = 0;

        for (var x = fromX; x <= toX; x++) {
            sum += grid[x][toY];
        }
        for (var y = fromY; y < toY; y++) {
            sum += grid[toX][y];
        }

        return sum;
    }
}
