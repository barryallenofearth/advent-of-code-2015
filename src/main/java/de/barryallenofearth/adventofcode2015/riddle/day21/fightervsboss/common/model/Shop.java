package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Shop {

    private final List<Weapon> weaponList = new ArrayList<>();

    private final List<Armor> armorList = new ArrayList<>();

    private final List<Ring> ringList = new ArrayList<>();
}
