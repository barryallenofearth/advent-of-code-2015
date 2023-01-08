package de.barryallenofearth.adventofcode2015.riddle.day17.part2;

import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.Container;
import de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl.CombinationsCounter;
import de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl.ContainerReader;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Main_17_2 {
	public static void main(String[] args) {
		final ContainerReader containerReader = new ContainerReader();
		final List<Container> containerList = containerReader.read();

		final int requiredAmount = 150;
		final Set<Set<Container>> combinations = new CombinationsCounter().countCombinations(containerList, requiredAmount);
		final List<Set<Container>> min = combinations.stream().sorted(Comparator.comparingInt(Set::size)).collect(Collectors.toList());
		final int minimumNumberOfContainers = min.get(0).size();
		final long count = combinations.stream().filter(set -> set.size() == minimumNumberOfContainers).count();
		System.out.println(count + " is amount of combinations with the smallest list.");
	}
}
