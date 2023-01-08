package de.barryallenofearth.adventofcode2015.riddle.day18.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.List;

public class InitialStateReader {

	public int[][] read() {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-18.txt");
		final int[][] grid = new int[strings.get(0).length() + 2][strings.size() + 2];
		for (int y = 0; y < strings.size() + 2; y++) {

			String line = null;
			if (y != 0 && y != strings.size() + 1) {
				line = strings.get(y - 1);
			}
			for (int x = 0; x < strings.get(0).length() + 2; x++) {
				if (x == 0 || y == 0 || x == strings.get(0).length() + 1 || y == strings.size() + 1) {
					grid[x][y] = 0;
				} else {
					grid[x][y] = line.charAt(x - 1) == '#' ? 1 : 0;
				}
			}

		}
		return grid;
	}
}
