package de.barryallenofearth.adventofcode2015.riddle.day14.part1;

import de.barryallenofearth.adventofcode2015.riddle.day14.common.model.Reindeer;
import de.barryallenofearth.adventofcode2015.riddle.day14.common.uitl.ReindeerParser;

import java.util.List;

public class Main_14_1 {
	public static void main(String[] args) {
		final List<Reindeer> reindeerList = new ReindeerParser().readReindeers();
		final Reindeer reindeer = new TimeLimitRaceProcessor().race(reindeerList, 2503);
		System.out.println("The winning reindeer is " + reindeer.getName() + " after travelling " + reindeer.getTravelledDistance() + " km");

	}
}
