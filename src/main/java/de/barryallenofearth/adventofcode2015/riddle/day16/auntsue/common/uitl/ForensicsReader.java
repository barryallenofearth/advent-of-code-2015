package de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.model.AuntSue;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForensicsReader {

	public static final Pattern SUE_PATTERN = Pattern.compile("Sue (\\d+): (!?(\\w+:\\s*\\d+(!?,\\s*)?)+)");

	public List<AuntSue> readAuntSues() {
		final List<AuntSue> auntSueList = new ArrayList<>();

		final List<String> lines = RiddleFileReader.readAllLines("riddle-16.txt");
		for (String line : lines) {
			final Matcher matcher = SUE_PATTERN.matcher(line);
			if (matcher.matches()) {
				final AuntSue auntSue = new AuntSue(Integer.parseInt(matcher.group(1)), parseComponents(Arrays.asList(matcher.group(2).split(",\\s+"))));
				auntSueList.add(auntSue);
			}
		}

		return auntSueList;
	}

	public Map<String, Integer> readForensicComponents() {
		final List<String> components = RiddleFileReader.readAllLines("riddle-16_components.txt");
		return parseComponents(components);
	}

	private Map<String, Integer> parseComponents(List<String> components) {
		Map<String, Integer> componentsAndAmount = new HashMap<>();
		for (String component : components) {
			final String[] split = component.split(":\\s+");
			componentsAndAmount.put(split[0], Integer.valueOf(split[1]));
		}
		return componentsAndAmount;
	}
}
