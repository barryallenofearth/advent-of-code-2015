package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part2;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl.ReplacementAndMoleculeReader;

public class Main_19_2 {
	public static void main(String[] args) {
		final ReplacementsAndMolecule replacementsAndMolecule = new ReplacementAndMoleculeReader().read();
		final MoleculeGenerator moleculeGenerator = new MoleculeGenerator();
		final int numberOfSteps = moleculeGenerator.determineMinimumNumberOfSteps(replacementsAndMolecule);
		System.out.println(numberOfSteps + " is the minimum number of steps needed to generate the molecule.");
	}
}
