package de.barryallenofearth.adventofcode2015.riddle.day21.part1;

import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Armor;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Fighter;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Shop;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Weapon;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl.ReadShopWithEquipment;

public class Main_21_1 {
    public static void main(String[] args) {
        ReadShopWithEquipment readShopWithEquipment = new ReadShopWithEquipment();

        final Shop shop = readShopWithEquipment.read();


        final Weapon bossWeapon = shop.getWeaponList().stream().filter(weapon -> weapon.getCost() == -1).findFirst().get();
        final Armor bossArmor = shop.getArmorList().stream().filter(armor -> armor.getCost() == -1).findFirst().get();
        shop.getWeaponList().remove(bossWeapon);
        shop.getArmorList().remove(bossArmor);
        Fighter boss = new Fighter("Boss", bossWeapon, bossArmor);
        System.out.println(boss);

    }
}
