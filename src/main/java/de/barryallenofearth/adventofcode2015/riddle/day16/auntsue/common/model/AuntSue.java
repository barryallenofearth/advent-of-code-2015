package de.barryallenofearth.adventofcode2015.riddle.day16.auntsue.common.model;

import lombok.Data;

import java.util.Map;

@Data
public class AuntSue {
	private final int number;

	private final Map<String, Integer> componentAndAmount;
}
