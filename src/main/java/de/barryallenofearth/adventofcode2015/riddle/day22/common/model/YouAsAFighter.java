package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class YouAsAFighter extends Fighter {

    public YouAsAFighter() {
        super("You");
        this.remainingHealth = 50;
        this.mana = 500;
    }

    public YouAsAFighter(YouAsAFighter youAsAFighter) {
        super(youAsAFighter.getName());
        this.armor = youAsAFighter.getArmor();
        this.damage = youAsAFighter.getDamage();
        this.remainingHealth = youAsAFighter.getRemainingHealth();
        this.mana = youAsAFighter.getMana();
        for (Spell ongoingSpell : youAsAFighter.getOngoingSpells()) {
            ongoingSpells.add(new Spell(ongoingSpell.getSpellType(), ongoingSpell.getRemainingRoundsActive()));
        }
    }

    private int mana;

    private final List<Spell> ongoingSpells = new ArrayList<>();

    public void castSpell(Boss boss, SpellType spellType) {
        if (spellType.getNumberOfRounds() == 0) {
            spellType.getHandleSpellEffect().accept(boss, this);
        } else {
            ongoingSpells.add(new Spell(spellType, spellType.getNumberOfRounds()));
            this.mana -= spellType.getManaCost();
        }
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
