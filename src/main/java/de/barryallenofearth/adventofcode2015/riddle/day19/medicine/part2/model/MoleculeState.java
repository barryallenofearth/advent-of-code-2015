package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoleculeState {
    private int modificationSteps;

    private String molecule;
}
