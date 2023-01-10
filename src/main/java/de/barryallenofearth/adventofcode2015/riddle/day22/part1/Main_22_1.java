package de.barryallenofearth.adventofcode2015.riddle.day22.part1;

import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.BattleState;

public class Main_22_1 {
    public static void main(String[] args) {
        final BattleState fight = new FindMinimumManaSpentFight().fight();

        //TODO 797 is too low
        System.out.println(fight.getManaSpent() + " is the minimum amount of mana spendable to defeat the boss.");
        System.out.println(fight.getSpellCastInRound());
    }
}
