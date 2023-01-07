package de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.part1;

import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.PeopleSorter;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model.PeopleAndPairs;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model.Person;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.uitl.PeopleHappinessParser;

import java.util.List;

public class Main_13_1 {
	public static void main(String[] args) {
		final PeopleAndPairs peopleAndPairs = new PeopleHappinessParser().read();
		System.out.println(peopleAndPairs);

		final PeopleSorter peopleSorter = new PeopleSorter();
		final List<Person> sort = peopleSorter.sort(peopleAndPairs);
		final int happiness = peopleSorter.calculateHappiness(sort, peopleAndPairs.getPairList());

		for (Person person : sort) {
			System.out.println(person.getName());
		}
		System.out.println("Total happiness is " + happiness);

	}
}
