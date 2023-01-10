package de.barryallenofearth.adventofcode2015.riddle.day21.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class YouAsAFighter extends Fighter {

    public YouAsAFighter(Weapon weapon, Armor armor, Set<Ring> rings) {
        super("You", weapon, armor);
        this.rings = rings;
        this.cost = weapon.getCost() + (armorEquipped.map(Equipment::getCost).orElse(0)) + rings.stream().mapToInt(Ring::getCost).sum();
    }

    private final int cost;

    private final Set<Ring> rings;

    @Override
    public void receiveDamage(int damage) {
        for (Ring ring : rings) {
            damage -= ring.getArmor();
        }
        super.receiveDamage(damage);
    }

    @Override
    public void dealDamageToFighter(Fighter fighter) {
        int damage = weapon.getDamage();
        for (Ring ring : rings) {
            damage += ring.getDamage();
        }
        fighter.receiveDamage(damage);
    }

}
