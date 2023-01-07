package de.barryallenofearth.adventofcode2015.riddle.day14.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day14.common.model.Reindeer;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReindeerParser {
	public static final Pattern REINDEER_PATTERN = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.");

	public List<Reindeer> readReindeers() {
		final List<Reindeer> reindeerList = new ArrayList<>();
		final List<String> strings = RiddleFileReader.readAllLines("riddle-14.txt");
		for (String string : strings) {
			final Matcher matcher = REINDEER_PATTERN.matcher(string);
			if (matcher.matches()) {
				String name = matcher.group(1);
				int speed = Integer.parseInt(matcher.group(2));
				int flyingTime = Integer.parseInt(matcher.group(3));
				int restingTime = Integer.parseInt(matcher.group(4));
				reindeerList.add(new Reindeer(name, speed, flyingTime, restingTime));
			}
		}

		return reindeerList;
	}
}
