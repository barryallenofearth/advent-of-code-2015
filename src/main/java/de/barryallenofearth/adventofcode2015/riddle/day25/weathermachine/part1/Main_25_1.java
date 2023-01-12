package de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.part1;

import de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.model.GridAndTarget;
import de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.uitl.GridInitializer;

public class Main_25_1 {
    public static void main(String[] args) {
        final GridInitializer gridInitializer = new GridInitializer();
        final GridAndTarget grid = gridInitializer.getGrid();
        System.out.println("The base grid has a length of " + grid.getGrid().length + "x" + grid.getGrid().length);
    }
}
