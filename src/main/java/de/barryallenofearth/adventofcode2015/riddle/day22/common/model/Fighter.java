package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.Data;

@Data
public abstract class Fighter {

    protected final String name;

    protected int remainingHealth;

    protected int armor;

    protected int damage;

    public void receiveDamage(int damage) {
        remainingHealth -= Math.max(damage - armor, 1);
    }

    public void dealDamage(Fighter opponent) {
        opponent.receiveDamage(damage);
    }

}
