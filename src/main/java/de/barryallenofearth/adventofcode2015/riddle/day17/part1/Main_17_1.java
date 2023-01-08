package de.barryallenofearth.adventofcode2015.riddle.day17.part1;

import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.Container;
import de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl.CombinationsCounter;
import de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl.ContainerReader;

import java.util.List;

public class Main_17_1 {
	public static void main(String[] args) {
		final ContainerReader containerReader = new ContainerReader();
		final List<Container> containerList = containerReader.read();

		final int requiredAmount = 150;
		final int combinations = new CombinationsCounter().countCombinations(containerList, requiredAmount).size();
		System.out.println(combinations + " combinations found.");
	}
}
