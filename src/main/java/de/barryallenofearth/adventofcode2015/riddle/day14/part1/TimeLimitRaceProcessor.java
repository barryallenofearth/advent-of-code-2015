package de.barryallenofearth.adventofcode2015.riddle.day14.part1;

import de.barryallenofearth.adventofcode2015.riddle.day14.common.model.Reindeer;

import java.util.List;

public class TimeLimitRaceProcessor {

	public Reindeer race(List<Reindeer> reindeerList, int numberOfSeconds) {

		Reindeer maxReindeer = null;
		for (Reindeer reindeer : reindeerList) {
			int cycleLength = reindeer.getFlyingTime() + reindeer.getRestingTime();
			int numberOfCompleteCycles = numberOfSeconds / cycleLength;

			int travelledKilometers = numberOfCompleteCycles * reindeer.getSpeed() * reindeer.getFlyingTime();
			final int remainingTime = numberOfSeconds - numberOfCompleteCycles * cycleLength;
			if (remainingTime >= reindeer.getFlyingTime()) {
				travelledKilometers += reindeer.getFlyingTime() * reindeer.getSpeed();
			} else {
				travelledKilometers += remainingTime * reindeer.getSpeed();
			}

			reindeer.setTravelledDistance(travelledKilometers);
			if (maxReindeer == null || reindeer.getTravelledDistance() > maxReindeer.getTravelledDistance()) {
				maxReindeer = reindeer;
			}
		}
		return maxReindeer;
	}
}
