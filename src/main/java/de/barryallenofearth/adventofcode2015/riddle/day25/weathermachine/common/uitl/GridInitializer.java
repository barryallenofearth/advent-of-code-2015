package de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.model.GridAndTarget;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridInitializer {
    public GridAndTarget getGrid() {
        final String string = RiddleFileReader.readAllLines("riddle-25.txt").get(0);
        final Matcher matcher = Pattern.compile("To continue, please consult the code grid in the manual.  Enter the code at row (\\d+), column (\\d+).").matcher(string);


        int targetX = 0;
        int targetY = 0;

        if (matcher.matches()) {
            //yes, the numbers are correct
            targetX = Integer.parseInt(matcher.group(2));
            targetY = Integer.parseInt(matcher.group(1));
        }

        int gridDimension = targetX + targetY - 1;

        return new GridAndTarget(new long[gridDimension][gridDimension], targetX, targetY);
    }
}
