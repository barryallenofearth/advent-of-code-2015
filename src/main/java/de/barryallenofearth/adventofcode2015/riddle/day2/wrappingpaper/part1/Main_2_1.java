package de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.part1;

import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.common.BoxReaderServie;
import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.common.RectangleCalculator;
import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.model.Box;
import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.part1.common.WrappingPaperCalculator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main_2_1 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<int[]> boxDimensions = BoxReaderServie.readBoxDimensions();

		int totalWrappingPaper = 0;
		for (int[] boxDimension : boxDimensions) {
			final Box box = RectangleCalculator.calculateBox(boxDimension[0], boxDimension[1], boxDimension[2]);
			totalWrappingPaper += WrappingPaperCalculator.calculateWrappingPaper(box);
		}
		System.out.println(totalWrappingPaper + " square feet required.");
	}
}
