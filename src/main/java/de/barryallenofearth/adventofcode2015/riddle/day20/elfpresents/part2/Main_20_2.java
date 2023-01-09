package de.barryallenofearth.adventofcode2015.riddle.day20.elfpresents.part2;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

public class Main_20_2 {
	public static void main(String[] args) {

		final int targetPresentCount = Integer.parseInt(RiddleFileReader.readAllLines("riddle-20.txt").get(0));
		final int house = new PresentDistributerHouseLimit().firstHouseToMatchNumberOfPresents(targetPresentCount);
		System.out.println(house + " is the first house with " + targetPresentCount + " presents.");

	}
}
