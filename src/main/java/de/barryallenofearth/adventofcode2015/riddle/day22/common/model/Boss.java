package de.barryallenofearth.adventofcode2015.riddle.day22.common.model;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Boss extends Fighter {

    public Boss(String name) {
        super(name);
        this.remainingHealth = 58;
        this.damage = 9;
        //magic ignores armor
        this.armor = 0;
    }




}
