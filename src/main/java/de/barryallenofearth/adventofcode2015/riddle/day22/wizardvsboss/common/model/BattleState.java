package de.barryallenofearth.adventofcode2015.riddle.day22.wizardvsboss.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BattleState {

    private int manaSpent;

    private int currentRound = 0;

    private final Wizard wizard;

    private final Boss boss;

    private final StringBuilder spellCastInRound;
}
