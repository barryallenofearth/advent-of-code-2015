package de.barryallenofearth.adventofcode2015.riddle.day18.part1;

import de.barryallenofearth.adventofcode2015.riddle.day18.common.uitl.GridAnimator;
import de.barryallenofearth.adventofcode2015.riddle.day18.common.uitl.InitialStateReader;

import java.util.function.BiFunction;

public class Main_18_1 {

	public static final BiFunction<Integer, Integer, Integer> GRID_SWITCH_FUNCTION = (status, neighborCountWithLightsOn) -> {
		if (status == 1) {
			if (neighborCountWithLightsOn == 3 || neighborCountWithLightsOn == 2) {
				return 1;
			} else {
				return 0;
			}
		} else if (status == 0) {
			if (neighborCountWithLightsOn == 3) {
				return 1;
			} else {
				return 0;
			}
		} else {
			throw new IllegalStateException("Lights have to be turned on (1) or off (2) but cannot be " + status);
		}
	};

	public static void main(String[] args) {
		final InitialStateReader initialStateReader = new InitialStateReader();
		final int[][] grid = initialStateReader.read();
		final GridAnimator gridAnimator = new GridAnimator(GRID_SWITCH_FUNCTION);
		final int[][] finalGrid = gridAnimator.animate(grid, 100);
		final int numberOfLightsSwitchedOn = gridAnimator.displayGridAndCount(finalGrid);
		System.out.println(numberOfLightsSwitchedOn);

	}

}
