package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Ring extends Equipment {
    public Ring(String name, int cost, int damage, int armor) {
        super(name, cost);
        this.damage = damage;
        this.armor = armor;
    }

    private final int damage;

    private final int armor;
}
