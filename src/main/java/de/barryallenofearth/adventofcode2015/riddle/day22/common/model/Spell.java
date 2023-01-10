package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Spell {
    private final SpellType spellType;

    private int remainingRoundsActive;
}
