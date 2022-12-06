package de.barryallenofearth.adventofcode2015.riddle.a.floors.common.util;

public class MoverSantaUtil {

	public static int determineFinalFloor(String sequence) {
		int floor = 0;
		for (int index = 0; index < sequence.length(); index++) {
			if (sequence.charAt(index) == '(') {
				floor++;
			} else if (sequence.charAt(index) == ')') {
				floor--;
			}
		}
		return floor;
	}
}
