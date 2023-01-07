package de.barryallenofearth.adventofcode2015.riddle.day13.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.Pair;
import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.PeopleAndPairs;
import de.barryallenofearth.adventofcode2015.riddle.day13.common.model.Person;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeopleHappinessParser {

	public static final Pattern HAPPINESS_RELATION_PATTERN = Pattern.compile("(\\w+) would (gain|lose) (\\d+) happiness units by sitting next to (\\w+).");

	public PeopleAndPairs read() {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-13.txt");
		PeopleAndPairs peopleAndPairs = new PeopleAndPairs();
		for (String line : strings) {
			final Matcher matcher = HAPPINESS_RELATION_PATTERN.matcher(line);
			if (matcher.matches()) {
				final Person person = new Person(matcher.group(1));
				peopleAndPairs.getPeople().add(person);
				int happinessModifier = Integer.parseInt(matcher.group(3));
				if (matcher.group(2).equals("lose")) {
					happinessModifier *= -1;
				}
				final Person neighbor = new Person(matcher.group(4));
				peopleAndPairs.getPeople().add(person);

				peopleAndPairs.getPairList().add(new Pair(person, neighbor, happinessModifier));
			}
		}
		return peopleAndPairs;
	}
}
