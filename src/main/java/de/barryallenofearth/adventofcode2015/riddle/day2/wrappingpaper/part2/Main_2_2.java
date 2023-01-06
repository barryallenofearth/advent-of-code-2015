package de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.part2;

import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.common.BoxReaderServie;
import de.barryallenofearth.adventofcode2015.riddle.day2.wrappingpaper.part2.common.RibbonCalculator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main_2_2 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<int[]> boxDimensions = BoxReaderServie.readBoxDimensions();

		int totalRibbonLength = 0;
		for (int[] boxDimension : boxDimensions) {
			final int ribbon = RibbonCalculator.calculateRequiredRibbon(boxDimension);
			totalRibbonLength += ribbon;
		}
		System.out.println(totalRibbonLength + " total ribbon length");
	}
}
