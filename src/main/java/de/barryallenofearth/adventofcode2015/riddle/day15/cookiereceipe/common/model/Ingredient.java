package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model;

import lombok.Data;

@Data
public class Ingredient {

	private final String name;

	private final int capacity;

	private final int durability;

	private final int flavor;

	private final int texture;

	private final int calories;
}
