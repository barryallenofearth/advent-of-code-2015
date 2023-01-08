package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part1;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl.ReplacementAndMoleculeReader;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl.SingleReplacementVariantFinder;

import java.util.Set;

public class Main_19_1 {
	public static void main(String[] args) {
		final ReplacementsAndMolecule replacementsAndMolecule = new ReplacementAndMoleculeReader().read();
		final SingleReplacementVariantFinder singleReplacementVariantFinder = new SingleReplacementVariantFinder();
		final Set<String> variants = singleReplacementVariantFinder.getVariants(replacementsAndMolecule);
		System.out.println(variants.size() + " could be found.");
	}
}
