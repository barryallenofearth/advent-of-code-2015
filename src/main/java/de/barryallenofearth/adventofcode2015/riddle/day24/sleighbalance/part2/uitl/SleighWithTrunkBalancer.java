package de.barryallenofearth.adventofcode2015.riddle.day24.sleighbalance.part2.uitl;

import com.google.common.collect.Collections2;
import de.barryallenofearth.adventofcode2015.riddle.day24.sleighbalance.part2.model.SleighWithTrunk;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class SleighWithTrunkBalancer {

	public static final Comparator<SleighWithTrunk> SLEIGH_COMPARATOR = (sleigh1, sleigh2) -> {
		int diff = sleigh1.getCenter().size() - sleigh2.getCenter().size();
		if (diff == 0) {
			final long value = quantumEntanglement(sleigh1) - quantumEntanglement(sleigh2);
			return (int) (value / Math.abs(value));
		}
		return diff;
	};

	public SleighWithTrunk balance() {
		final List<Integer> allPackages = getAllPackages();

		final int sum = sumUp(allPackages);
		int weighGoalPerGroup = sum / 4;
		System.out.println("Each group has to weigh exactly " + weighGoalPerGroup + ". The total sum is " + sum);

		final Collection<List<Integer>> permutations = Collections2.orderedPermutations(allPackages, Comparator.comparingInt(Integer::intValue));

		System.out.println(permutations.size() + " will be investigated.");
		SleighWithTrunk minSleigh = null;
		int minSize = allPackages.size();
		long minQuantumEntanglement = Long.MAX_VALUE;
		int index = 0;
		final LocalDateTime startingTime = LocalDateTime.now();
		for (List<Integer> permutation : permutations) {

			final SleighWithTrunk sleigh = createSleigh(new ArrayList<>(permutation), weighGoalPerGroup);
			if (sleigh.getCenter().size() < minSize) {
				minSleigh = sleigh;
				minSize = sleigh.getCenter().size();
				minQuantumEntanglement = quantumEntanglement(sleigh);
				System.out.println(minSleigh);
				System.out.println(minSize);
				System.out.println(minQuantumEntanglement);
			} else if (sleigh.getCenter().size() == minSize && quantumEntanglement(sleigh) < minQuantumEntanglement) {
				minSleigh = sleigh;
				minQuantumEntanglement = quantumEntanglement(sleigh);
				System.out.println(minSleigh);
				System.out.println(minSize);
				System.out.println(minQuantumEntanglement);
			}
			index++;

			if (index % 10_000 == 0) {
				System.out.println(index / 1_000_000 + "x10^6 steps investigated");
				System.out.printf("%.02f%% processed.%n", ((double) index) / permutations.size() * 100.);
				System.out.printf("%.003f minutes.%n", Duration.between(startingTime, LocalDateTime.now()).getSeconds() / 60.);
				System.out.println(minQuantumEntanglement + " is current min quantum entanglement.");
				System.out.println();
				break;
			}
		}

		return minSleigh;
	}

	private List<Integer> getAllPackages() {
		final List<String> lines = RiddleFileReader.readAllLines("riddle-24.txt");
		final List<Integer> allPackages = new ArrayList<>();
		for (String line : lines) {
			allPackages.add(Integer.valueOf(line));
		}
		return allPackages;
	}

	private SleighWithTrunk createSleigh(List<Integer> allPackages, int weighGoalPerGroup) {
		final SleighWithTrunk sleigh = new SleighWithTrunk();
		fillCompartment(allPackages, weighGoalPerGroup, sleigh.getCenter());
		fillCompartment(allPackages, weighGoalPerGroup, sleigh.getLeft());
		fillCompartment(allPackages, weighGoalPerGroup, sleigh.getRight());
		sleigh.getTrunk().addAll(allPackages);
		return sleigh;
	}

	private void fillCompartment(List<Integer> allPackages, int weighGoalPerGroup, List<Integer> compartment) {
		for (int index = allPackages.size() - 1; index >= 0; index--) {
			final Integer weight = allPackages.get(index);
			final int sum = sumUp(compartment);
			if (sum + weight <= weighGoalPerGroup) {
				compartment.add(weight);
				allPackages.remove(index);
				if (sum + weight == weighGoalPerGroup) {
					break;
				}
			}

		}
	}

	private int sumUp(List<Integer> packagesInCompartment) {
		return packagesInCompartment.stream().mapToInt(Integer::intValue).sum();
	}

	public static long quantumEntanglement(SleighWithTrunk sleigh) {
		long quantumEntanglement = 1;
		for (Integer weigh : sleigh.getCenter()) {
			quantumEntanglement *= weigh;
		}
		return quantumEntanglement;
	}

	private boolean isSleighBalanced(SleighWithTrunk sleigh) {
		final int center = sumUp(sleigh.getCenter());
		final int right = sumUp(sleigh.getRight());
		final int left = sumUp(sleigh.getLeft());
		return right == center && left == center;
	}
}
