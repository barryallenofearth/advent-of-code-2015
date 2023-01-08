package de.barryallenofearth.adventofcode2015.riddle.day17.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StateModel {
	public StateModel(List<Container> currentCombination, List<Container> unusedContainers) {
		this.currentCombination = new ArrayList<>(currentCombination);
		this.unusedContainers = new ArrayList<>(unusedContainers);
	}

	private final List<Container> currentCombination;

	private final List<Container> unusedContainers;
}
