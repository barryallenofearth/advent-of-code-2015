package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.Replacement;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacementAndMoleculeReader {

	public static final Pattern REPLACEMENT_PATTERN = Pattern.compile("(\\w+)\\s*=>\\s*(\\w+)");

	public ReplacementsAndMolecule read() {
		final List<String> lines = RiddleFileReader.readAllLines("riddle-19.txt");

		final List<Replacement> replacements = new ArrayList<>();
		String molecule = null;
		for (String line : lines) {
			final Matcher replacementMatcher = REPLACEMENT_PATTERN.matcher(line);
			if (replacementMatcher.matches()) {
				replacements.add(new Replacement(replacementMatcher.group(1), replacementMatcher.group(2)));
			} else if (!line.isBlank()) {
				molecule = line;
			}
		}
		return new ReplacementsAndMolecule(replacements, molecule);
	}
}
