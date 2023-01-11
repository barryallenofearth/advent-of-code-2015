package de.barryallenofearth.adventofcode2015.riddle.day24.part2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SleighWithTrunk {

	private final List<Integer> left = new ArrayList<>();

	private final List<Integer> center = new ArrayList<>();

	private final List<Integer> right = new ArrayList<>();

	private final List<Integer> trunk = new ArrayList<>();

}
