package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part2;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.Replacement;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl.SingleReplacementVariantFinder;

import java.util.*;
import java.util.stream.Collectors;

public class MoleculeGenerator {

	public static final String STARTING_MOLECULE = "e";

	public int determineMinimumNumberOfSteps(ReplacementsAndMolecule replacementsAndMolecule) {
		final List<Replacement> replacements = replacementsAndMolecule.getReplacements().stream()
				.map(replacement -> new Replacement(replacement.getReplacement(), replacement.getOriginal()))
				.sorted(Comparator.comparing(replacement -> replacement.getOriginal().length()))
				.collect(Collectors.toList());

		final String targetMolecule = replacementsAndMolecule.getInitialMolecule();

		final SingleReplacementVariantFinder variantFinder = new SingleReplacementVariantFinder();

		String currentMolecule = targetMolecule;
		int currentSteps = 0;
		while (!currentMolecule.equals(STARTING_MOLECULE)) {

			final List<String> potentialVariants = new ArrayList<>(variantFinder.getVariants(new ReplacementsAndMolecule(replacements, currentMolecule)));
			if (potentialVariants.size() == 0) {
				currentMolecule = targetMolecule;
				currentSteps = 0;

				System.out.println("No more options... Restart.");
				continue;
			}
			currentMolecule = potentialVariants.get((int) (Math.random() * potentialVariants.size()));

			currentSteps++;
			if (currentSteps % 1_000_000 == 0) {
				System.out.println(currentSteps + " were investigated.");
				System.out.println(currentMolecule);
				System.out.println(targetMolecule);
				System.out.println();

			}
		}
		System.out.println(currentMolecule);
		return currentSteps;
	}

}
