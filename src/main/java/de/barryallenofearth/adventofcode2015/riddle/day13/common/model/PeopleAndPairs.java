package de.barryallenofearth.adventofcode2015.riddle.day13.common.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PeopleAndPairs {
	private final Set<Person> people = new HashSet<>();

	private final Set<PairWithHappiness> pairList = new HashSet<>();

}
