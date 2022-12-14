package de.barryallenofearth.adventofcode2015.riddle.day22.wizardvsboss.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Boss extends Fighter {

    public Boss() {
        super("Boss");
        this.remainingHealth = 58;
        this.damage = 9;
        //magic ignores armor
        this.armor = 0;
    }

    public Boss(Boss boss) {
        super(boss.getName());
        this.remainingHealth = boss.getRemainingHealth();
        this.damage = boss.getDamage();
        this.armor = boss.getArmor();
    }


}
