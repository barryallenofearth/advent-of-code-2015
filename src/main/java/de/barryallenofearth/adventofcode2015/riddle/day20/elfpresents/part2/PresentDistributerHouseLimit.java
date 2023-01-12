package de.barryallenofearth.adventofcode2015.riddle.day20.elfpresents.part2;

public class PresentDistributerHouseLimit {

	public static final int HOUSES_VISITED_PER_ELF = 50;

	public int firstHouseToMatchNumberOfPresents(int targetPresents) {

		int minHouseNumber = Math.max((int) (-0.5 + Math.sqrt(0.25 + targetPresents / 5.5)), 500_000);
		System.out.println(minHouseNumber + " is the minimum house number possible.");
		final int maxHouseNumber = targetPresents / 11 + targetPresents % 11;
		System.out.println(maxHouseNumber + " is the maximum house number possible.");
		for (int house = minHouseNumber; house < maxHouseNumber; house++) {
			int presentsAtHouse = sumOfElves(house) * 11;
			if (targetPresents <= presentsAtHouse) {
				return house;
			}
			if (house % 10_000 == 0) {
				System.out.println("house; " + house + ", presents: " + presentsAtHouse + ", diff: " + (targetPresents - presentsAtHouse));
			}
		}
		return -1;
	}

	public int sumOfElves(int houseNumber) {
		int sum = 0;
		if (houseNumber <= HOUSES_VISITED_PER_ELF) {
			sum++;
		}
		sum += houseNumber;
		for (int elf = (int) Math.sqrt(houseNumber); elf < houseNumber; elf++)

			if (houseNumber % elf == 0) {
				if (elf * HOUSES_VISITED_PER_ELF <= houseNumber) {
					sum += elf;
				}
				final int factor = houseNumber / elf;
				if (factor * HOUSES_VISITED_PER_ELF <= houseNumber) {
					sum += factor;
				}
			}
		return sum;
	}
}
