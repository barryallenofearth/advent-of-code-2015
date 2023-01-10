package de.barryallenofearth.adventofcode2015.riddle.day21.part1;

import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.*;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl.BattleSimulator;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl.GenerateAllFighters;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl.ReadShopWithEquipment;

import java.util.List;

public class Main_21_1 {
    public static void main(String[] args) {
        ReadShopWithEquipment readShopWithEquipment = new ReadShopWithEquipment();

        final Shop shop = readShopWithEquipment.read();

        final Weapon bossWeapon = shop.getWeaponList().stream().filter(weapon -> weapon.getCost() == -1).findFirst().get();
        final Armor bossArmor = shop.getArmorList().stream().filter(armor -> armor.getCost() == -1).findFirst().get();
        shop.getWeaponList().remove(bossWeapon);
        shop.getArmorList().remove(bossArmor);
        Fighter boss = new Fighter("Boss", bossWeapon, bossArmor);

        final List<YouAsAFighter> allFighters = new GenerateAllFighters().generate(shop);

        final YouAsAFighter cheapestFighter = new BattleSimulator().getCheapestFighter(boss, allFighters);
        System.out.println(cheapestFighter);
        System.out.println("The cheapest fight costs " + cheapestFighter.getCost());
        System.out.println("It is the " + allFighters.indexOf(cheapestFighter) + "th fighter.");

    }
}
