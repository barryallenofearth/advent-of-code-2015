package de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model;

import lombok.Data;

@Data
public class PairWithHappiness {
	private final Pair pair;

	private final int happinessModifier;
}
