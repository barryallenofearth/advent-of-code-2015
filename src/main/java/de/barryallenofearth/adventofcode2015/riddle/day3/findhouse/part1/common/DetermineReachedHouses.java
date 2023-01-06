package de.barryallenofearth.adventofcode2015.riddle.day3.findhouse.part1.common;

import de.barryallenofearth.adventofcode2015.riddle.day3.findhouse.model.HouseCoordinates;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetermineReachedHouses {

	public static Map<HouseCoordinates, Integer> determineHousesWithPresents() throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-3.txt");

		Map<HouseCoordinates, Integer> housesWithPresents = new HashMap<>();
		for (String string : strings) {
			housesWithPresents = new HashMap<>();

			int x = 1;
			int y = 1;
			housesWithPresents.put(new HouseCoordinates(x, y), 1);
			for (int index = 0; index < string.length(); index++) {
				if (string.charAt(index) == '<') {
					x--;
				} else if (string.charAt(index) == '>') {
					x++;
				} else if (string.charAt(index) == '^') {
					y++;
				} else if (string.charAt(index) == 'v') {
					y--;
				}

				final HouseCoordinates houseCoordinates = new HouseCoordinates(x, y);
				housesWithPresents.putIfAbsent(houseCoordinates, 0);
				housesWithPresents.put(houseCoordinates, housesWithPresents.get(houseCoordinates) + 1);
			}
			System.out.println(housesWithPresents.size() + " houses reached.");
		}
		return housesWithPresents;
	}
}
