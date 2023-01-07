package de.barryallenofearth.adventofcode2015.riddle.day14.common.model;

import lombok.Data;

@Data
public class Reindeer {

	private final String name;

	//in km/s
	private final int speed;

	//in seconds
	private final int flyingTime;

	//in seconds
	private final int restingTime;

	private int travelledDistance = 0;

	private int score;
}
