package de.barryallenofearth.adventofcode2015.riddle.day21.part2;

import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.Fighter;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.model.YouAsAFighter;
import de.barryallenofearth.adventofcode2015.riddle.day21.common.uitl.SingleBattleSimulator;

import java.util.Comparator;
import java.util.List;

public class LosingBattleSimulator {

    public YouAsAFighter getCheapestFighter(Fighter boss, List<YouAsAFighter> allFighters) {
        allFighters.sort(Comparator.comparingInt(YouAsAFighter::getCost).reversed());
        final SingleBattleSimulator singleBattleSimulator = new SingleBattleSimulator();
        for (YouAsAFighter fighter : allFighters) {
            if (!singleBattleSimulator.didYouWin(boss, fighter)) {
                return fighter;
            }
            boss.setRemainingHealth(100);
        }
        return null;
    }

}
