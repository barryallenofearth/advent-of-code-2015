package de.barryallenofearth.adventofcode2015.riddle.day14.part2;

import de.barryallenofearth.adventofcode2015.riddle.day14.common.model.Reindeer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EachSecondSystem {

	public Reindeer race(List<Reindeer> reindeerList, int numberOfSeconds) {

		Map<String, Integer> flyingTimeRemainingVsName = reindeerList.stream().collect(Collectors.toMap(Reindeer::getName, Reindeer::getFlyingTime));
		Map<String, Integer> restingTimeRemainingVsName = reindeerList.stream().collect(Collectors.toMap(Reindeer::getName, reindeer -> 0));

		for (int second = 0; second < numberOfSeconds; second++) {
			for (Reindeer reindeer : reindeerList) {
				final Integer remainingFlyingTime = flyingTimeRemainingVsName.get(reindeer.getName());
				final Integer remainingRestingTime = restingTimeRemainingVsName.get(reindeer.getName());

				if (remainingFlyingTime > 0) {
					final int reducedTime = remainingFlyingTime - 1;
					flyingTimeRemainingVsName.put(reindeer.getName(), reducedTime);
					reindeer.setTravelledDistance(reindeer.getTravelledDistance() + reindeer.getSpeed());
					if (reducedTime == 0) {
						restingTimeRemainingVsName.put(reindeer.getName(), reindeer.getRestingTime());
					}
				}
				if (remainingRestingTime > 0) {
					final int reducedTime = remainingRestingTime - 1;
					restingTimeRemainingVsName.put(reindeer.getName(), reducedTime);
					if (reducedTime == 0) {
						flyingTimeRemainingVsName.put(reindeer.getName(), reindeer.getFlyingTime());
					}
				}
			}
			reindeerList.sort(Comparator.comparingInt(Reindeer::getTravelledDistance).reversed());
			final int currentMaxDistance = reindeerList.get(0).getTravelledDistance();
			reindeerList.stream()
					.filter(reindeer -> reindeer.getTravelledDistance() == currentMaxDistance)
					.forEach(reindeer -> reindeer.setScore(reindeer.getScore() + 1));
		}

		reindeerList.sort(Comparator.comparingInt(Reindeer::getScore).reversed());
		return reindeerList.get(0);
	}
}
