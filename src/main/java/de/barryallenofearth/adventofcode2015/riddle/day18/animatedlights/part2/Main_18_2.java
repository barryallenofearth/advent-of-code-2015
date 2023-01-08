package de.barryallenofearth.adventofcode2015.riddle.day18.animatedlights.part2;

public class Main_18_2 {
	public static void main(String[] args) {

		final InitialStateReaderWithCornersOn initialStateReader = new InitialStateReaderWithCornersOn();
		final int[][] grid = initialStateReader.read();
		final GridAnimatorWithCornersOn gridAnimator = new GridAnimatorWithCornersOn();
		final int[][] finalGrid = gridAnimator.animate(grid, 100);
		final int numberOfLightsSwitchedOn = gridAnimator.displayGridAndCount(finalGrid);
		System.out.println(numberOfLightsSwitchedOn);
	}
}
