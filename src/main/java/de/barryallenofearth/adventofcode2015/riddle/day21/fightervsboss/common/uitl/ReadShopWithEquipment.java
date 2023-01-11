package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.Armor;
import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.Ring;
import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.Shop;
import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.Weapon;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadShopWithEquipment {

    public static final Pattern EQUIPMENT_PATTERN = Pattern.compile("([\\w+\\s]+)\\s+(-?\\d+)\\s+(-?\\d+)\\s+(-?\\d+)");
    public static final Pattern HEADLINE_PATTERN = Pattern.compile("(\\w+):\\s+Cost\\s+Damage\\s+Armor");

    public static final String WEAPONS = "Weapons";

    public static final String ARMOR = "Armor";

    public static final String RINGS = "Rings";

    public Shop read() {
        final Shop shop = new Shop();
        final List<String> strings = RiddleFileReader.readAllLines("riddle-21.txt");
        String currentCategory = "";
        for (String string : strings) {
            final Matcher matcher = HEADLINE_PATTERN.matcher(string);
            if (matcher.matches()) {
                currentCategory = matcher.group(1).trim();
                continue;
            }
            final Matcher equipmentMatcher = EQUIPMENT_PATTERN.matcher(string);
            if (equipmentMatcher.matches()) {
                final String name = equipmentMatcher.group(1).trim();
                final int cost = Integer.parseInt(equipmentMatcher.group(2));
                final int damage = Integer.parseInt(equipmentMatcher.group(3));
                final int armor = Integer.parseInt(equipmentMatcher.group(4));
                switch (currentCategory) {
                    case WEAPONS:
                        shop.getWeaponList().add(new Weapon(name, cost, damage));
                        break;
                    case ARMOR:
                        shop.getArmorList().add(new Armor(name, cost, armor));
                        break;
                    case RINGS:
                        shop.getRingList().add(new Ring(name, cost, damage, armor));
                        break;
                }
            }
        }


        return shop;
    }
}
