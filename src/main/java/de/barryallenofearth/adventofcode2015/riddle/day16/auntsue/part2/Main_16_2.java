package de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.part2;

import de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.model.AuntSue;
import de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.uitl.ForensicsReader;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main_16_2 {

	public static final String CATS = "cats";

	public static final String TREES = "trees";

	public static final String POMERENIANS = "pomeranians";

	public static final String GOLDFISH = "goldfish";

	public static void main(String[] args) {

		final ForensicsReader forensicsReader = new ForensicsReader();
		final List<AuntSue> auntSueList = forensicsReader.readAuntSues();
		final Map<String, Integer> componentsAndAmount = forensicsReader.readForensicComponents();

		final List<AuntSue> allMatchingAunts = auntSueList.stream()
				.filter(auntSue -> auntSue.getComponentAndAmount().entrySet()
						.stream()
						.allMatch(entry -> {
							final String key = entry.getKey();
							if (key.equals(CATS) || key.equals(TREES)) {
								return componentsAndAmount.get(key) != null && entry.getValue() > componentsAndAmount.get(key);
							} else if (key.equals(POMERENIANS) || key.equals(GOLDFISH)) {
								return componentsAndAmount.get(key) != null && entry.getValue() < componentsAndAmount.get(key);
							} else {
								return Objects.equals(componentsAndAmount.get(key), entry.getValue());
							}
						}))
				.collect(Collectors.toList());

		if (allMatchingAunts.size() != 1) {
			throw new IllegalStateException("There can only be one aunt! You found " + allMatchingAunts.size());
		}
		System.out.println(allMatchingAunts.get(0).getNumber() + " is the correct aunt.");
	}
}
