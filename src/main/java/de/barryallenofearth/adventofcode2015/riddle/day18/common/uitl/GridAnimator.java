package de.barryallenofearth.adventofcode2015.riddle.day18.common.uitl;

import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class GridAnimator {

	/**
	 * first input: status of light (1 is on, 0 is off)
	 * second input: number of neighbors with lights turned on
	 * return value: new status of light (1 is on, 0 is off
	 */
	private final BiFunction<Integer, Integer, Integer> switchLightFunction;

	public int[][] animate(int[][] initialGrid, int numberOfIterations) {

		int[][] previousGrid = initialGrid;
		for (int round = 0; round < numberOfIterations; round++) {
			int[][] nextGrid = new int[initialGrid.length][initialGrid[0].length];
			for (int y = 1; y < initialGrid[0].length - 1; y++) {
				for (int x = 1; x < initialGrid.length - 1; x++) {
					final int numberOfNeighborsSwitchOn = countNeighborsWithLightsOn(previousGrid, x, y);
					nextGrid[x][y] = switchLightFunction.apply(previousGrid[x][y], numberOfNeighborsSwitchOn);
				}
			}
			previousGrid = nextGrid;
		}
		return previousGrid;
	}

	private int countNeighborsWithLightsOn(int[][] previousGrid, int x, int y) {
		return previousGrid[x - 1][y - 1] + previousGrid[x][y - 1] + previousGrid[x + 1][y - 1]
				+ previousGrid[x - 1][y] + previousGrid[x + 1][y]
				+ previousGrid[x - 1][y + 1] + previousGrid[x][y + 1] + previousGrid[x + 1][y + 1];
	}

	public int displayGridAndCount(int[][] grid) {
		int allLights = 0;
		for (int y = 0; y < grid[0].length; y++) {
			for (int x = 0; x < grid.length; x++) {
				final int light = grid[x][y];
				allLights += light;
				System.out.print(light == 1 ? "#" : ".");
			}
			System.out.println();
		}
		System.out.println();
		return allLights;
	}
}
