package de.barryallenofearth.adventofcode2015.riddle.day21.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Weapon extends Equipment {
    public Weapon(String name, int cost, int damage) {
        super(name, cost);
        this.damage = damage;
    }

    private final int damage;

}
