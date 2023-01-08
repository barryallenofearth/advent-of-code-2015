package de.barryallenofearth.adventofcode2015.riddle.day18.part1;

public class Main_18_1 {

	public static void main(String[] args) {
		final InitialStateReader initialStateReader = new InitialStateReader();
		final int[][] grid = initialStateReader.read();
		final GridAnimator gridAnimator = new GridAnimator();
		final int[][] finalGrid = gridAnimator.animate(grid, 100);
		final int numberOfLightsSwitchedOn = gridAnimator.displayGridAndCount(finalGrid);
		System.out.println(numberOfLightsSwitchedOn);

	}

}
