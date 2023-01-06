package de.barryallenofearth.adventofcode2015.riddle.day3.findhouse.part1;

import de.barryallenofearth.adventofcode2015.riddle.day3.findhouse.model.HouseCoordinates;
import de.barryallenofearth.adventofcode2015.riddle.day3.findhouse.part1.common.DetermineReachedHouses;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class Main_3_1 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final Map<HouseCoordinates, Integer> houseCoordinatesIntegerMap = DetermineReachedHouses.determineHousesWithPresents();
	}
}
