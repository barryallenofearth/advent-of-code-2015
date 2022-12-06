package de.barryallenofearth.adventofcode2015.riddle.a.floors.part1;

import de.barryallenofearth.adventofcode2015.riddle.a.floors.common.util.MoverSantaUtil;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main_1_1 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-1.txt");
		System.out.println("Santa ends up on the floor " + MoverSantaUtil.determineFinalFloor(strings.get(0)));
	}
}
