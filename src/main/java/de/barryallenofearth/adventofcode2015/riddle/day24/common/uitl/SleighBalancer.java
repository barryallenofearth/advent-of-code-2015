package de.barryallenofearth.adventofcode2015.riddle.day24.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day24.common.model.Sleigh;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SleighBalancer {

	public static final Comparator<Sleigh> SLEIGH_COMPARATOR = (sleigh1, sleigh2) -> {
		int diff = sleigh1.getCenter().size() - sleigh2.getCenter().size();
		if (diff == 0) {
			final long value = quantumEntanglement(sleigh1) - quantumEntanglement(sleigh2);
			return (int) (value / Math.abs(value));
		}
		return diff;
	};

	public Sleigh balance() {
		final List<Integer> allPackages = getAllPackages();

		final int sum = sumUp(allPackages);
		int weighGoalPerGroup = sum / 3;
		System.out.println("Each group has to weigh exactly " + weighGoalPerGroup + ". The total sum is " + sum);

		final List<Sleigh> balancedSleighs = new ArrayList<>();
		final Sleigh start = createStartingSleigh(allPackages, weighGoalPerGroup);

		final List<Sleigh> openNodes = new ArrayList<>();
		openNodes.add(start);
		while (!openNodes.isEmpty()) {
			final Sleigh currentSleigh = openNodes.get(0);
			openNodes.remove(currentSleigh);

			//TODO rearrange packages

			if (isSleighBalanced(currentSleigh)) {
				balancedSleighs.add(currentSleigh);
			}
			openNodes.sort(SLEIGH_COMPARATOR);
		}

		return balancedSleighs.stream()
				.sorted(SLEIGH_COMPARATOR)
				.findFirst()
				.get();
	}

	private List<Integer> getAllPackages() {
		final List<String> lines = RiddleFileReader.readAllLines("riddle-24.txt");
		final List<Integer> allPackages = new ArrayList<>();
		for (String line : lines) {
			allPackages.add(Integer.valueOf(line));
		}
		return allPackages;
	}

	private Sleigh createStartingSleigh(List<Integer> allPackages, int weighGoalPerGroup) {
		final Sleigh sleigh = new Sleigh(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		fillCompartment(allPackages, weighGoalPerGroup, sleigh.getCenter());
		fillCompartment(allPackages, weighGoalPerGroup, sleigh.getLeft());
		sleigh.getRight().addAll(allPackages);
		return sleigh;
	}

	private void fillCompartment(List<Integer> allPackages, int weighGoalPerGroup, List<Integer> compartment) {
		for (int index = allPackages.size() - 1; index >= 0; index--) {
			final Integer weight = allPackages.get(index);
			if (sumUp(compartment) + weight <= weighGoalPerGroup) {
				compartment.add(weight);
				allPackages.remove(index);
			}
		}
	}

	private int sumUp(List<Integer> packagesInCompartment) {
		return packagesInCompartment.stream().mapToInt(Integer::intValue).sum();
	}

	public static long quantumEntanglement(Sleigh sleigh) {
		long quantumEntanglement = 1;
		for (Integer weigh : sleigh.getCenter()) {
			quantumEntanglement *= weigh;
		}
		return quantumEntanglement;
	}

	private boolean isSleighBalanced(Sleigh sleigh) {
		final int center = sumUp(sleigh.getCenter());
		final int right = sumUp(sleigh.getRight());
		final int left = sumUp(sleigh.getLeft());
		return right == center && left == center;
	}
}
