package de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.uitl;

public class GridCalculator {

    public long calculate(long[][] grid, int targetX, int targetY) {

        int x = 0, y = 0;

        int maxY = y;
        int maxX = x;
        int previousX = x;
        int previousY = y;
        while (x != targetX || y != targetY) {
            x = (x + 1) % (maxY + 1);
            if (x > maxX) {
                maxY = x;
            }
            if (y > 0) {
                y--;
            } else {
                y = maxX + 1;
            }
            if (maxY < y) {
                maxY = y;
            }
            final long nextValue = grid[previousX][previousY] + 1;
            System.out.println(previousX + " " + previousY + " " + grid[previousX][previousY]);
            System.out.println(x + " " + y + " " + nextValue);
            System.out.println();
            grid[x][y] = nextValue;

            previousX = x;
            previousY = y;
        }

        return grid[x][y];
    }
}
