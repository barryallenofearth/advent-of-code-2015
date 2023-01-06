package de.barryallenofearth.adventofcode2015.riddle.day1.floors.part2.util;

public class MoverSantaUtil {

	public static int determineNumberOfStepsToLevelMinus1(String sequence) {
		int floor = 0;
		for (int index = 0; index < sequence.length(); index++) {
			if (sequence.charAt(index) == '(') {
				floor++;
			} else if (sequence.charAt(index) == ')') {
				floor--;
			}
			if (floor == -1) {
				return index + 1;
			}
		}
		return -1;
	}
}
