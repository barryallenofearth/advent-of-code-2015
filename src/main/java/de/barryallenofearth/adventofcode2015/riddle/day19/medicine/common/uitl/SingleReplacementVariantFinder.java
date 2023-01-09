package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.Replacement;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;

import java.util.HashSet;
import java.util.Set;

public class SingleReplacementVariantFinder {

	public Set<String> getVariants(ReplacementsAndMolecule replacementsAndMolecule) {

		final Set<String> variants = new HashSet<>();

		final String initialMolecule = replacementsAndMolecule.getInitialMolecule();
		for (Replacement replacement : replacementsAndMolecule.getReplacements()) {

			String remainingString = initialMolecule, startOfMolecule = "";
			while (remainingString.contains(replacement.getOriginal())) {
				final int indexOfReplacement = remainingString.indexOf(replacement.getOriginal());
				final String replaced = remainingString.replaceFirst(replacement.getOriginal(), replacement.getReplacement());
				final String variant = startOfMolecule + replaced;
				variants.add(variant);
				startOfMolecule = initialMolecule.substring(0, indexOfReplacement + startOfMolecule.length() + replacement.getOriginal().length());
				remainingString = initialMolecule.substring(startOfMolecule.length());
			}
		}

		return variants;
	}
}
