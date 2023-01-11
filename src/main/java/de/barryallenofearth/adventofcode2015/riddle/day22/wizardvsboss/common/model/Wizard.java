package de.barryallenofearth.adventofcode2015.riddle.day22.wizardvsboss.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Wizard extends Fighter {

	public Wizard() {
		super("You");
		this.remainingHealth = 50;
		this.mana = 500;
	}

	public Wizard(Wizard wizard) {
		super(wizard.getName());
		this.armor = wizard.getArmor();
		this.damage = wizard.getDamage();
		this.remainingHealth = wizard.getRemainingHealth();
		this.mana = wizard.getMana();
		for (Spell ongoingSpell : wizard.getOngoingSpells()) {
			this.ongoingSpells.add(new Spell(ongoingSpell.getSpellType(), ongoingSpell.getRemainingRoundsActive()));
		}
	}

	private int mana;

	private final List<Spell> ongoingSpells = new ArrayList<>();

	public void castSpell(Boss boss, SpellType spellType) {
		if (spellType.getNumberOfRounds() == 0) {
			spellType.getHandleSpellEffect().accept(boss, this);
		} else {
			ongoingSpells.add(new Spell(spellType, spellType.getNumberOfRounds()));
		}
		this.mana -= spellType.getManaCost();
	}

	public void handleOngoingSpells(Boss boss) {
		for (int index = ongoingSpells.size() - 1; index >= 0; index--) {
			Spell spell = ongoingSpells.get(index);
			spell.getSpellType().getHandleSpellEffect().accept(boss, this);
			spell.setRemainingRoundsActive(spell.getRemainingRoundsActive() - 1);
			if (spell.getRemainingRoundsActive() == 0) {
				spell.getSpellType().getEndOfSpellAction().accept(this);
				ongoingSpells.remove(spell);
			}
		}
	}

}
