package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BattleState {

    private int manaSpent;

    private int currentRound = 0;

    private final YouAsAFighter youAsAFighter;

    private final Boss boss;

    private final StringBuilder spellCastInRound;
}
