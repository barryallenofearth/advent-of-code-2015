package de.barryallenofearth.adventofcode2015.riddle.day20.common.uitl;

public class PresentDistributer {
    public int firstHouseToMatchNumberOfPresents(int targetPresents) {
        int house = 1;

        while (true) {
            int presentsAtHouse = 0;
            for (int elf = 1; elf <= house; elf++) {
                if (house % elf == 0) {
                    presentsAtHouse += elf * 10;
                }
            }
            if (house % 10_000 == 0) {
                System.out.println("house; " + house + ", presents: " + presentsAtHouse + ", diff: " + (targetPresents - presentsAtHouse));
            }
            if (targetPresents == presentsAtHouse) {
                return house;
            }

            house++;
        }
    }
}
