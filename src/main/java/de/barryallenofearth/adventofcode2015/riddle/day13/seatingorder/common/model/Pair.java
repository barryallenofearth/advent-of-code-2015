package de.barryallenofearth.adventofcode2015.riddle.day13.seatingorder.common.model;

import lombok.Data;

@Data
public class Pair {
	private final Person person;

	private final Person neighbor;

}
