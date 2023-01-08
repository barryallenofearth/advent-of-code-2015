package de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.Container;
import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.StateModel;

import java.util.*;

public class CombinationsCounter {

	public static final int PRINT_STEPS = 10_000_000;

	public Set<Set<Container>> countCombinations(List<Container> containerList, int requiredAmount) {

		Set<Set<Container>> foundCombinations = new HashSet<>();
		Set<Set<Container>> closedNodes = new HashSet<>();
		List<StateModel> openNodes = new ArrayList<>();
		openNodes.add(new StateModel(new ArrayList<>(), containerList));
		int combinationsEvaluated = 0;
		while (!openNodes.isEmpty()) {
			final StateModel stateModel = openNodes.get(0);
			openNodes.remove(0);
			if (closedNodes.contains(stateModel.getCurrentCombination())) {
				continue;
			}

			closedNodes.add(stateModel.getCurrentCombination());
			final ArrayList<Container> unusedContainers = new ArrayList<>(stateModel.getUnusedContainers());
			for (Container unusedContainer : unusedContainers) {

				final List<Container> nextCombination = new ArrayList<>(stateModel.getCurrentCombination());
				nextCombination.add(unusedContainer);
				final List<Container> nextUnusedContainers = new ArrayList<>(stateModel.getUnusedContainers());
				nextUnusedContainers.remove(unusedContainer);
				final StateModel next = new StateModel(nextCombination, nextUnusedContainers);
				final int nextVolume = getTotalVolume(next);
				combinationsEvaluated++;
				if (combinationsEvaluated % PRINT_STEPS == 0) {
					System.out.println(combinationsEvaluated / PRINT_STEPS + "x10^7 combinations evaluated.");
					System.out.println(foundCombinations.size() + " matching combinations found.");

				}
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
			openNodes.sort(Comparator.comparingInt(this::getTotalVolume).reversed());
		}

		return foundCombinations;

	}

	private int getTotalVolume(StateModel stateModel) {
		return stateModel.getCurrentCombination().stream().mapToInt(Container::getVolume).sum();
	}

}
