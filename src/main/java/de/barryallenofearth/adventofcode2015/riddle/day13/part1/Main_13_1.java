package de.barryallenofearth.adventofcode2015.riddle.day13.part1;

import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.PeopleAndPairs;
import de.barryallenofearth.adventofcode2015.riddle.day13.common.uitl.PeopleHappinessParser;

public class Main_13_1 {
	public static void main(String[] args) {
		final PeopleAndPairs peopleAndPairs = new PeopleHappinessParser().read();
		System.out.println(peopleAndPairs);
	}
}
