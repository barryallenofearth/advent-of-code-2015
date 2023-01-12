package de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.uitl;

public class GridCalculator {

	public long calculate(long[][] grid, int targetX, int targetY) {

		int x = 0, y = 0;

		int maxY = y;
		int previousX = 0;
		int previousY = 0;

		int multiplier = 252533;
		int divisor = 33554393;
		while (x != targetX || y != targetY) {
			x = (x + 1) % (maxY + 1);
			if (y > 0) {
				y--;
			} else {
				y = ++maxY;
			}
			final long nextValue = (grid[previousX][previousY] * multiplier) % divisor;
/*			System.out.println(previousX + " " + previousY + " " + grid[previousX][previousY]);
			System.out.println(x + " " + y + " " + nextValue);
			System.out.println();
			*/
			grid[x][y] = nextValue;

			previousX = x;
			previousY = y;
		}

		return grid[x][y];
	}
}
