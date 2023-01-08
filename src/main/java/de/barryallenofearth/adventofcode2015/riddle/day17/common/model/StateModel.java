package de.barryallenofearth.adventofcode2015.riddle.day17.common.model;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class StateModel {
	public StateModel(List<Container> currentCombination, List<Container> unusedContainers) {
		this.currentCombination = new HashSet<>(currentCombination);
		this.unusedContainers = new HashSet<>(unusedContainers);
	}

	private final Set<Container> currentCombination;

	private final Set<Container> unusedContainers;
}
