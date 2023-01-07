package de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.part2;

import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.PeopleSorter;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model.Pair;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model.PairWithHappiness;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model.PeopleAndPairs;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model.Person;
import de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.uitl.PeopleHappinessParser;

import java.util.List;

public class Main_13_2 {
	public static void main(String[] args) {

		final PeopleAndPairs peopleAndPairs = new PeopleHappinessParser().read();
		System.out.println(peopleAndPairs);

		final Person me = new Person("Me");

		for (Person person : peopleAndPairs.getPeople()) {
			peopleAndPairs.getPairList().add(new PairWithHappiness(new Pair(me, person), 0));
			peopleAndPairs.getPairList().add(new PairWithHappiness(new Pair(person, me), 0));
		}
		peopleAndPairs.getPeople().add(me);
		peopleAndPairs.getPairList();
		final PeopleSorter peopleSorter = new PeopleSorter();
		final List<Person> sort = peopleSorter.sort(peopleAndPairs);
		final int happiness = peopleSorter.calculateHappiness(sort, peopleAndPairs.getPairList());

		for (Person person : sort) {
			System.out.println(person.getName());
		}
		System.out.println("Total happiness is " + happiness);

	}
}
