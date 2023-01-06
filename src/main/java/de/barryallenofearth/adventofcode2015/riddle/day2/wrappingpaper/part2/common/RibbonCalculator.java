package de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.part2.common;

public class RibbonCalculator {

	public static int calculateRequiredRibbon(int[] boxDimensions) {
		return boxDimensions[0] * 2 + boxDimensions[1] * 2 + boxDimensions[0] * boxDimensions[1] * boxDimensions[2];
	}
}
