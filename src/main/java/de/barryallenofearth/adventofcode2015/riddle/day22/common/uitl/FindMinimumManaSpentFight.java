package de.barryallenofearth.adventofcode2015.riddle.day22.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.BattleState;
import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.Boss;
import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.SpellType;
import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.Wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindMinimumManaSpentFight {

	public BattleState fight() {

		List<BattleState> openStates = new ArrayList<>();
		openStates.add(new BattleState(0, 0, new Wizard(), new Boss(), new StringBuilder()));

		int numberOfBattlesFought = 0;
		while (!openStates.isEmpty()) {
			final BattleState battleState = openStates.get(0);
			openStates.remove(battleState);
			battleState.setCurrentRound(battleState.getCurrentRound() + 1);

			battleState.getWizard().handleOngoingSpells(battleState.getBoss());
			final List<SpellType> castableSpells = Arrays.stream(SpellType.values())
					.filter(spellType -> battleState.getWizard().getMana() >= spellType.getManaCost())
					.filter(spellType -> battleState.getWizard().getOngoingSpells().stream().noneMatch(spell -> spell.getSpellType() == spellType))
					.collect(Collectors.toList());
			//no spell can be cast => you lost
			if (castableSpells.isEmpty()) {
				numberOfBattlesFought++;
				printState(numberOfBattlesFought);
				continue;
			}

			for (SpellType castableSpell : castableSpells) {
				final BattleState next = new BattleState(battleState.getManaSpent(),
						battleState.getCurrentRound(),
						new Wizard(battleState.getWizard()),
						new Boss(battleState.getBoss()),
						new StringBuilder(battleState.getSpellCastInRound()));

				next.getWizard().castSpell(next.getBoss(), castableSpell);
				next.setManaSpent(next.getManaSpent() + castableSpell.getManaCost());
				next.getSpellCastInRound().append(next.getCurrentRound()).append(": ").append(castableSpell).append("\n");

				next.getWizard().handleOngoingSpells(next.getBoss());
				if (next.getBoss().getRemainingHealth() <= 0) {
					System.out.println(++numberOfBattlesFought + " battles were fought.");
					return next;
				}
				next.getBoss().dealDamage(next.getWizard());
				if (next.getWizard().getRemainingHealth() <= 0) {
					numberOfBattlesFought++;
					printState(numberOfBattlesFought);
					continue;
				}
				openStates.add(next);

			}

			openStates = openStates.stream()
					.distinct()
					.sorted(Comparator.comparingInt(BattleState::getManaSpent))
					.collect(Collectors.toList());
		}

		return null;
	}

	private void printState(int numberOfBattlesFought) {
		if (numberOfBattlesFought > 0 && numberOfBattlesFought % 1_000 == 0) {
			System.out.println(numberOfBattlesFought + " battles fought.");
		}
	}
}
