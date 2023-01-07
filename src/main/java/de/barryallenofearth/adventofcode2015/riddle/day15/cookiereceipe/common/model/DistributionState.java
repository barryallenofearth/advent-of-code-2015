package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model;

import lombok.Data;

import java.util.Map;

@Data
public class DistributionState {

	private final Map<String, Integer> ingredientsAndAmount;
}
