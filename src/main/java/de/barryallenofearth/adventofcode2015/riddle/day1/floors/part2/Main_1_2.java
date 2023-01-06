package de.barryallenofearth.adventofcode2015.riddle.day1.floors.part2;

import de.barryallenofearth.adventofcode2015.riddle.day1.floors.part2.util.MoverSantaUtil;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main_1_2 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-1.txt");
		System.out.println("Santa first enters floor minus 1 after " + MoverSantaUtil.determineNumberOfStepsToLevelMinus1(strings.get(0)) + " steps.");
	}
}
