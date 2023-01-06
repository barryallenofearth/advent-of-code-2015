package de.barryallenofearth.adventofcode2015.riddle.day3.findhouse.part2.common;

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

			int x1 = 1, x2 = 1;
			int y1 = 1, y2 = 1;
			housesWithPresents.put(new HouseCoordinates(x1, y1), 2);
			for (int index = 0; index < string.length(); index++) {
				if (string.charAt(index) == '<') {
					if (index % 2 == 0) {
						x1--;
					} else {
						x2--;
					}
				} else if (string.charAt(index) == '>') {
					if (index % 2 == 0) {
						x1++;
					} else {
						x2++;
					}
				} else if (string.charAt(index) == '^') {
					if (index % 2 == 0) {
						y1++;
					} else {
						y2++;
					}
				} else if (string.charAt(index) == 'v') {
					if (index % 2 == 0) {
						y1--;
					} else {
						y2--;
					}
				}

				final HouseCoordinates houseCoordinates;
				if (index % 2 == 0) {
					houseCoordinates = new HouseCoordinates(x1, y1);
				} else {
					houseCoordinates = new HouseCoordinates(x2, y2);
				}
				housesWithPresents.putIfAbsent(houseCoordinates, 0);
				housesWithPresents.put(houseCoordinates, housesWithPresents.get(houseCoordinates) + 1);
			}
			System.out.println(housesWithPresents.size() + " houses reached.");
		}
		return housesWithPresents;
	}
}
