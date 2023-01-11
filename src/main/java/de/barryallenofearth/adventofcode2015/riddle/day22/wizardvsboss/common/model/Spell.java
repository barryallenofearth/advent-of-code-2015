package de.barryallenofearth.adventofcode2015.riddle.day22.wizardvsboss.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Spell {
    private final SpellType spellType;

    private int remainingRoundsActive;
}
