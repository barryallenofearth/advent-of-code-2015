package de.barryallenofearth.adventofcode2015.riddle.b.common;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BoxReaderServie {

	public static List<int[]> readBoxDimensions() throws IOException, URISyntaxException {
		final List<String> allLines = RiddleFileReader.readAllLines("riddle-2.txt");
		final List<int[]> dimensions = new ArrayList<>();
		for (String line : allLines) {
			final String[] xes = line.split("x");
			final List<Integer> boxDimensions = new ArrayList<>();
			boxDimensions.add(Integer.parseInt(xes[0]));
			boxDimensions.add(Integer.parseInt(xes[1]));
			boxDimensions.add(Integer.parseInt(xes[2]));

			boxDimensions.sort(Comparator.comparingInt(value -> value));

			final int[] currentBox = { boxDimensions.get(0), boxDimensions.get(1), boxDimensions.get(2) };
			dimensions.add(currentBox);
		}
		return dimensions;
	}
}
