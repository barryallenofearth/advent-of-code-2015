package de.barryallenofearth.adventofcode2015.riddle.c.part2;

import de.barryallenofearth.adventofcode2015.riddle.c.model.HouseCoordinates;
import de.barryallenofearth.adventofcode2015.riddle.c.part2.common.DetermineReachedHouses;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class Main_3_2 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final Map<HouseCoordinates, Integer> houseCoordinatesIntegerMap = DetermineReachedHouses.determineHousesWithPresents();
	}
}
