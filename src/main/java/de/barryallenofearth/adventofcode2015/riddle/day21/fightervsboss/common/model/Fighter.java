package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Fighter {

    public Fighter(String name, Weapon weapon, Armor armor) {
        this.name = name;
        this.weapon = weapon;
        this.armorEquipped = Optional.ofNullable(armor);

        this.remainingHealth = 100;
    }

    protected final String name;

    protected final Weapon weapon;

    protected final Optional<Armor> armorEquipped;

    protected int remainingHealth;

    public void dealDamageToFighter(Fighter fighter) {
        fighter.receiveDamage(weapon.getDamage());
    }

    public void receiveDamage(int damage) {
        if (armorEquipped.isPresent()) {
            damage -= armorEquipped.get().getArmor();
        }
        this.remainingHealth -= Math.max(damage, 1);
    }
}
