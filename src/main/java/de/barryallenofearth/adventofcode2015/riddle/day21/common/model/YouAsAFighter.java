package de.barryallenofearth.adventofcode2015.riddle.day21.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class YouAsAFighter extends Fighter {

    public YouAsAFighter(String name, Weapon weapon, Armor armor) {
        super(name, weapon, armor);
    }

    private final List<Ring> rings = new ArrayList<>();

    @Override
    public int receiveDamage(int damage) {
        if (armorEquipped.isPresent()) {
            damage -= armorEquipped.get().getArmor();
        }
        for (Ring ring : rings) {
            damage -= ring.getArmor();
        }
        return super.receiveDamage(damage);
    }

    @Override
    public int dealDamageToFighter(Fighter fighter, int damage) {
        damage += damage;
        damage += weapon.getDamage();
        for (Ring ring : rings) {
            damage += ring.getDamage();
        }
        return super.dealDamageToFighter(fighter, damage);
    }
}
