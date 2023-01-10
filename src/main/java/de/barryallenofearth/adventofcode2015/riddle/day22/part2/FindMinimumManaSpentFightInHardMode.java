package de.barryallenofearth.adventofcode2015.riddle.day22.part2;

import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.BattleState;
import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.Boss;
import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.SpellType;
import de.barryallenofearth.adventofcode2015.riddle.day22.common.model.Wizard;
import jdk.jshell.spi.SPIResolutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FindMinimumManaSpentFightInHardMode {

	public static final int MISSILE_DAMAGE = 4;

	public static final int POISON_DAMAGE = 3;

	public BattleState fight() {

		List<BattleState> openStates = new ArrayList<>();
		openStates.add(new BattleState(0, 0, new Wizard(), new Boss(), new StringBuilder()));

		int numberOfBattlesFought = 0;
		while (!openStates.isEmpty()) {
			final BattleState battleState = openStates.get(0);
			openStates.remove(battleState);
			battleState.setCurrentRound(battleState.getCurrentRound() + 1);
			battleState.getWizard().setRemainingHealth(battleState.getWizard().getRemainingHealth() - 1);
			if (battleState.getWizard().getRemainingHealth() == 0) {
				numberOfBattlesFought++;
				printState(numberOfBattlesFought, openStates);
				continue;
			}

			battleState.getWizard().handleOngoingSpells(battleState.getBoss());
			final List<SpellType> castableSpells = Arrays.stream(SpellType.values())
					.filter(spellType -> battleState.getWizard().getMana() >= spellType.getManaCost())
					.filter(spellType -> battleState.getWizard().getOngoingSpells().stream().noneMatch(spell -> spell.getSpellType() == spellType))
					.filter(spellType -> needToSafeYourself(spellType, battleState))
					.filter(spellType -> isRechargeHelpful(spellType, battleState))
					.collect(Collectors.toList());
			//no spell can be cast => you lost
			if (castableSpells.isEmpty()) {
				numberOfBattlesFought++;
				printState(numberOfBattlesFought, openStates);
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
					printState(numberOfBattlesFought, openStates);
					continue;
				}
				openStates.add(next);

			}

			openStates = openStates.stream()
					.sorted(Comparator.comparingInt(battleState1 -> battleState.getBoss().getRemainingHealth()))
					//.sorted(Comparator.comparingInt(BattleState::getManaSpent))
					.collect(Collectors.toList());
		}

		return null;
	}

	private boolean needToSafeYourself(SpellType spellType, BattleState battleState) {
		if (battleState.getWizard().getRemainingHealth() + battleState.getWizard().getArmor() <= battleState.getBoss().getDamage()) {
			if (battleState.getBoss().getRemainingHealth() > 10) {
				return spellType == SpellType.SHIELD || spellType == SpellType.DRAIN;
			}
		}

		return true;
	}

	private boolean isRechargeHelpful(SpellType spellType, BattleState battleState) {
		if (spellType != SpellType.RECHARGE) {
			return true;
		}
		final Wizard wizard = battleState.getWizard();
		final Matcher matcher = Pattern.compile(SpellType.RECHARGE.toString()).matcher(battleState.getSpellCastInRound().toString());
		int numberOfTimesCastBefore = 0;
		while (matcher.find()) {
			numberOfTimesCastBefore++;
			if (numberOfTimesCastBefore >= 2) {
				return false;
			}
		}
		final Boss boss = battleState.getBoss();
		long maximumDamagePerRound = 10;//poison active (player turn [3], boss turn [3]) and missile [4]

		if (boss.getRemainingHealth() <= 2 * MISSILE_DAMAGE && wizard.getMana() > 2 * SpellType.MAGIC_MISSILE.getManaCost()) {
			return true;
		} else if (boss.getRemainingHealth() >= maximumDamagePerRound && wizard.getRemainingHealth() + wizard.getArmor() <= boss.getDamage()) {
			return false;
		}
		return true;
	}

	private void printState(int numberOfBattlesFought, List<BattleState> openNodes) {
		if (numberOfBattlesFought > 0 && numberOfBattlesFought % 1_000 == 0) {
			System.out.println(numberOfBattlesFought + " battles fought.");
			System.out.println(openNodes.size() + " further battles pending investigation");
			System.out.println();
		}
	}
}
