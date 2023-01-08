package de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.Container;
import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.StateModel;

import java.util.*;

public class CombinationsCounter {

	public int countCombinations(List<Container> containerList, int requiredAmount) {

		Set<Set<Container>> foundCombinations = new HashSet<>();
		List<StateModel> openNodes = new ArrayList<>();
		openNodes.add(new StateModel(new ArrayList<>(), containerList));
		int combinationsEvaluated = 0;
		while (!openNodes.isEmpty()) {
			final StateModel stateModel = openNodes.get(0);
			openNodes.remove(0);
			final ArrayList<Container> unusedContainers = new ArrayList<>(stateModel.getUnusedContainers());
			for (Container unusedContainer : unusedContainers) {

				final List<Container> nextCombination = new ArrayList<>(stateModel.getCurrentCombination());
				nextCombination.add(unusedContainer);
				final List<Container> nextUnusedContainers = new ArrayList<>(stateModel.getUnusedContainers());
				nextUnusedContainers.remove(unusedContainer);
				final StateModel next = new StateModel(nextCombination, nextUnusedContainers);
				final int nextVolume = getTotalVolume(next);
				combinationsEvaluated++;
				if (nextVolume == requiredAmount) {
					foundCombinations.add(new HashSet<>(next.getCurrentCombination()));
					continue;
				} else if (nextVolume > requiredAmount) {
					continue;
				}
				if (next.getUnusedContainers().isEmpty()) {
					continue;
				}
				openNodes.add(next);
			}
			if (combinationsEvaluated % 1_000_000 == 0) {
				System.out.println(combinationsEvaluated + " combinations evaluated.");
				System.out.println(foundCombinations.size() + " matching combinations found.");

			}
			openNodes.sort(Comparator.comparingInt(this::getTotalVolume).reversed());
		}

		return foundCombinations.size();

	}

	private int getTotalVolume(StateModel stateModel) {
		return stateModel.getCurrentCombination().stream().mapToInt(Container::getVolume).sum();
	}

}
