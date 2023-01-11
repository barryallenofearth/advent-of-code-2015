package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Armor extends Equipment {
    public Armor(String name, int cost, int armor) {
        super(name, cost);
        this.armor = armor;
    }

    private final int armor;
}
