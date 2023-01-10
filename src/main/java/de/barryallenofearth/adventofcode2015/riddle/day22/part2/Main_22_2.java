package de.barryallenofearth.adventofcode2015.riddle.day22.part2;

import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.BattleState;
import de.barryallenofearth.adventofcode2015.riddle.day22.part1.FindMinimumManaSpentFight;

public class Main_22_2 {
	public static void main(String[] args) {
		final BattleState fight = new FindMinimumManaSpentFightInHardMode().fight();

		//TODO 797 is too low
		System.out.println(fight.getManaSpent() + " is the minimum amount of mana spendable to defeat the boss.");
		System.out.println(fight.getSpellCastInRound());
	}
}
