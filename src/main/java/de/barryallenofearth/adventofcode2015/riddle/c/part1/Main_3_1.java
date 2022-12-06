package de.barryallenofearth.adventofcode2015.riddle.c.part1;

import de.barryallenofearth.adventofcode2015.riddle.c.part1.common.DetermineReachedHouses;
import de.barryallenofearth.adventofcode2015.riddle.c.model.HouseCoordinates;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class Main_3_1 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final Map<HouseCoordinates, Integer> houseCoordinatesIntegerMap = DetermineReachedHouses.determineHousesWithPresents();
	}
}
