package de.barryallenofearth.adventofcode2015.riddle.day24.part1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Sleigh {

	private final List<Integer> left = new ArrayList<>();

	private final List<Integer> center = new ArrayList<>();

	private final List<Integer> right = new ArrayList<>();

}
