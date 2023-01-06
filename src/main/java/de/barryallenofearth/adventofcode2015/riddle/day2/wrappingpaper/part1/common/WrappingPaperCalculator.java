package de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.part1.common;

import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.model.Box;

public class WrappingPaperCalculator {

	public static int calculateWrappingPaper(Box box) {
		return box.getSide1() * 3 + 2 * box.getSide2() + 2 * box.getSide3();
	}
}
