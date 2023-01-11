package de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.part1;

import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.Fighter;
import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.model.YouAsAFighter;
import de.barryallenofearth.adventofcode2015.riddle.day21.fightervsboss.common.uitl.SingleBattleSimulator;

import java.util.List;

public class WinningBattleSimulator {

    public YouAsAFighter getCheapestFighter(Fighter boss, List<YouAsAFighter> allFighters) {
        final SingleBattleSimulator singleBattleSimulator = new SingleBattleSimulator();
        for (YouAsAFighter fighter : allFighters) {
            if (singleBattleSimulator.didYouWin(boss, fighter)) {
                return fighter;
            }
            boss.setRemainingHealth(100);
        }
        return null;
    }

}
