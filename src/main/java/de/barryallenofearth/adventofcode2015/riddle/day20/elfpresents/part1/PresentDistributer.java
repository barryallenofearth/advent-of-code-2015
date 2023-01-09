package de.barryallenofearth.adventofcode2015.riddle.day20.elfpresents.part1;

import java.util.TreeSet;

public class PresentDistributer {
	public int firstHouseToMatchNumberOfPresents(int targetPresents) {

		int minDiff = Integer.MAX_VALUE;

		int minHouseNumber = (int) (-0.5 + Math.sqrt(0.25 + targetPresents / 5.));
		System.out.println((Math.sqrt(0.25 + targetPresents / 5.) - 0.5) + " is the minimum house number possible.");
		System.out.println(minHouseNumber + " is the minimum house number possible.");
		for (int house = minHouseNumber; house < targetPresents; house++) {
			int presentsAtHouse = factors(house).stream().mapToInt(Integer::intValue).sum() * 10;
			if (targetPresents <= presentsAtHouse) {
				return house;
			}
			if (house % 10_000 == 0) {
				System.out.println("house; " + house + ", presents: " + presentsAtHouse + ", diff: " + (targetPresents - presentsAtHouse));
			}
		}
		return -1;
	}

	public TreeSet<Integer> factors(int houseNumber) {
		TreeSet<Integer> factors = new TreeSet<>();
		factors.add(1);
		factors.add(houseNumber);
		for (int test = (int) Math.sqrt(houseNumber); test < houseNumber; test++)
			if (houseNumber % test == 0) {
				factors.add(test);
				factors.add(houseNumber / test);
			}
		return factors;
	}
}
