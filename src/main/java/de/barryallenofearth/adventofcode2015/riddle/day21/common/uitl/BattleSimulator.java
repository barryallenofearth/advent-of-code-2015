package de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Fighter;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.YouAsAFighter;

import java.util.List;

public class BattleSimulator {

    public YouAsAFighter getCheapestFighter(Fighter boss, List<YouAsAFighter> allFighters) {
        for (YouAsAFighter fighter : allFighters) {
            if (didYouWin(boss, fighter)) {
                return fighter;
            }
            boss.setRemainingHealth(100);
        }
        return null;
    }

    public boolean didYouWin(Fighter boss, Fighter you) {
        while (boss.getRemainingHealth() > 0 && you.getRemainingHealth() > 0) {
            boss.dealDamageToFighter(you);
            if (boss.getRemainingHealth() <= 0) {
                break;
            }
            you.dealDamageToFighter(boss);
            System.out.println("boss health: " + boss.getRemainingHealth());
            System.out.println("fighter health: " + you.getRemainingHealth());
            System.out.println();
        }
        return boss.getRemainingHealth() <= 0;
    }
}
