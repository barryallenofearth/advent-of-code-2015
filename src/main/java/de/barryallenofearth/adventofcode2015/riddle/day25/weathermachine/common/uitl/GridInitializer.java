package de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.model.GridAndTarget;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridInitializer {
	public GridAndTarget getGrid() {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-25.txt");
		long startingValue = Integer.parseInt(strings.get(0));
		final String targetDescription = strings.get(1);
		final Matcher matcher = Pattern.compile("To continue, please consult the code grid in the manual.  Enter the code at row (\\d+), column (\\d+).").matcher(targetDescription);

		int targetX = 0;
		int targetY = 0;

		if (matcher.matches()) {
			//yes, the numbers are correct
			targetX = Integer.parseInt(matcher.group(2)) - 1;
			targetY = Integer.parseInt(matcher.group(1)) - 1;
		}

		int gridDimension = targetX + targetY + 1;

		final long[][] grid = new long[gridDimension][gridDimension];
		grid[0][0] = startingValue;
		return new GridAndTarget(grid, targetX, targetY);
	}
}
