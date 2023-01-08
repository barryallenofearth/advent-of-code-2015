package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model;

import lombok.Data;

import java.util.List;

@Data
public class ReplacementsAndMolecule {
	private final List<Replacement> replacements;

	private final String initialMolecule;
}
