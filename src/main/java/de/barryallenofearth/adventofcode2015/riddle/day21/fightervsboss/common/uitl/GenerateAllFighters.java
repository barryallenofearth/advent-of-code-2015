package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateAllFighters {

    public static final String NO_ARMOR = "NO_ARMOR";
    public static final String NO_RING_1 = "NO_RING_1";
    public static final String NO_RING_2 = "NO_RING_2";

    public List<YouAsAFighter> generate(Shop shop) {
        final List<YouAsAFighter> fighters = new ArrayList<>();
        final Armor noArmor = new Armor(NO_ARMOR, 0, 0);
        final Ring noRing1 = new Ring(NO_RING_1, 0, 0, 0);
        final Ring noRing2 = new Ring(NO_RING_2, 0, 0, 0);

        shop.getArmorList().add(noArmor);
        shop.getRingList().add(noRing1);
        shop.getRingList().add(noRing2);
        for (Weapon weapon : shop.getWeaponList()) {
            for (Armor armor : shop.getArmorList()) {
                for (Ring ring1 : shop.getRingList()) {
                    for (Ring ring2 : shop.getRingList()) {
                        final YouAsAFighter youAsAFighter = new YouAsAFighter(weapon,
                                armor.getName().equals(NO_ARMOR) ? null : armor,
                                Stream.of(ring1, ring2)
                                        .filter(ring -> !ring.getName().equals(NO_RING_1) && !ring.getName().equals(NO_RING_2)).collect(Collectors.toSet()));

                        fighters.add(youAsAFighter);
                    }
                }
            }
        }

        shop.getArmorList().remove(noArmor);
        shop.getRingList().remove(noRing1);
        shop.getRingList().remove(noRing2);
        return fighters.stream()
                .distinct()
                .sorted(Comparator.comparingInt(YouAsAFighter::getCost))
                .collect(Collectors.toList());
    }
}
