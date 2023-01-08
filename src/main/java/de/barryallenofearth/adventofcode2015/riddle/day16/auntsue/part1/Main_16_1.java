package de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.part1;

import de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.model.AuntSue;
import de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.uitl.ForensicsReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main_16_1 {
	public static void main(String[] args) {

		final ForensicsReader forensicsReader = new ForensicsReader();
		final List<AuntSue> auntSueList = forensicsReader.readAuntSues();
		final Map<String, Integer> componentsAndAmount = forensicsReader.readForensicComponents();

		final List<AuntSue> allMatchingAunts = auntSueList.stream()
				.filter(auntSue -> auntSue.getComponentAndAmount().entrySet()
						.stream()
						.allMatch(entry -> componentsAndAmount.get(entry.getKey()) == entry.getValue()))
				.collect(Collectors.toList());

		if (allMatchingAunts.size() != 1) {
			throw new IllegalStateException("There can only be one aunt! You found " + allMatchingAunts.size());
		}
		System.out.println(allMatchingAunts.get(0).getNumber() + " is the correct aunt.");
	}
}
