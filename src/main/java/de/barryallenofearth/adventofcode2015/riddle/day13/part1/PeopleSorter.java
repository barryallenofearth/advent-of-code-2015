package de.barryallenofearth.adventofcode2015.riddle.day13.part1;

import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.Pair;
import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.PairWithHappiness;
import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.PeopleAndPairs;
import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PeopleSorter {

	public List<Person> sort(PeopleAndPairs peopleAndPairs) {
		return new ArrayList<>(peopleAndPairs.getPeople());

	}

	public int calculateHappiness(List<Person> peopleSorted, Set<PairWithHappiness> pairList) {
		int happiness = 0;

		for (int index = 1; index <= peopleSorted.size(); index++) {
			final Person person = peopleSorted.get(index % peopleSorted.size());
			final Person leftNeighbor = peopleSorted.get(index - 1);
			final Person rightNeighbor = peopleSorted.get((index + 1) % peopleSorted.size());

			final Integer happinessModifierLeft = pairList.stream()
					.filter(pairWithHappiness -> new Pair(person, leftNeighbor).equals(pairWithHappiness.getPair()))
					.map(PairWithHappiness::getHappinessModifier)
					.findFirst()
					.get();
			happiness += happinessModifierLeft;

			final Integer happinessModifierRight = pairList.stream()
					.filter(pairWithHappiness -> new Pair(person, rightNeighbor).equals(pairWithHappiness.getPair()))
					.map(PairWithHappiness::getHappinessModifier)
					.findFirst()
					.get();
			happiness += happinessModifierRight;

		}
		return happiness;
	}
}
