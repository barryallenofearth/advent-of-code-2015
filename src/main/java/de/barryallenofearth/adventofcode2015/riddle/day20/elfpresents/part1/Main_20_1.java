package de.barryallenofearth.adventofcode2015.riddle.day20.elfpresents.part1;

import de.barryallenofearth.adventofcode2015.riddle.day20.elfpresents.common.uitl.PresentDistributer;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

public class Main_20_1 {
    public static void main(String[] args) {

        final int targetPresentCount = Integer.parseInt(RiddleFileReader.readAllLines("riddle-20.txt").get(0));
        final int house = new PresentDistributer().firstHouseToMatchNumberOfPresents(targetPresentCount);
        System.out.println(house + " is the first house with " + targetPresentCount + " presents.");

    }
}
