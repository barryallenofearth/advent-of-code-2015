package de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Fighter;

public class SingleBattleSimulator {

    public boolean didYouWin(Fighter boss, Fighter you) {
        while (boss.getRemainingHealth() > 0 && you.getRemainingHealth() > 0) {
            boss.dealDamageToFighter(you);
            if (boss.getRemainingHealth() <= 0) {
                break;
            }
            you.dealDamageToFighter(boss);
        }
        return boss.getRemainingHealth() <= 0;
    }
}
