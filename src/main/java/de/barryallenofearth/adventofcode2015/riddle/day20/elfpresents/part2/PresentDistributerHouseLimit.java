package de.barryallenofearth.adventofcode2015.riddle.day20.elfpresents.part2;

public class PresentDistributerHouseLimit {

    public static final int HOUSES_VISITED_PER_ELF = 50;
    public static final int PRESENTS_PER_HOUSE = 11;

    public int firstHouseToMatchNumberOfPresents(int targetPresents) {

        final int bruteForceLowerLimit = 770_000;
        int minHouseNumber = Math.max((int) (-0.5 + Math.sqrt(0.25 + targetPresents / 5.5)), bruteForceLowerLimit);
        System.out.println(minHouseNumber + " is the minimum house number possible.");
        final int maxHouseNumber = targetPresents / PRESENTS_PER_HOUSE + targetPresents % PRESENTS_PER_HOUSE;
        System.out.println(maxHouseNumber + " is the maximum house number possible.");
        for (int house = minHouseNumber; house < maxHouseNumber; house++) {
            int presentsAtHouse = sumOfElves(house) * PRESENTS_PER_HOUSE;
            if (targetPresents <= presentsAtHouse) {
                return house;
            }
            if (house % 10_000 == 0) {
                System.out.println("house: " + house + ", presents: " + presentsAtHouse + ", diff: " + (targetPresents - presentsAtHouse));
            }
        }
        return -1;
    }

    public int sumOfElves(int houseNumber) {
        int sum = 0;
//        houses below 50 will not lead to the required result => skip
//        if (houseNumber <= HOUSES_VISITED_PER_ELF) {
//            sum++;
//        }
        sum += houseNumber;
        //every elf >= houseNumber / HOUSES_VISITED_PER_ELF will exceed the 50 houses limit. So either take sqrt(houseNumber) from factors function
        final int start = (int) Math.max(Math.sqrt(houseNumber), houseNumber / HOUSES_VISITED_PER_ELF);
        for (int elf = start; elf < houseNumber; elf++) {

            if (houseNumber % elf == 0) {
                sum += elf;
                final int secondElf = houseNumber / elf;
                sum += secondElf;
            }
        }
        return sum;
    }
}
