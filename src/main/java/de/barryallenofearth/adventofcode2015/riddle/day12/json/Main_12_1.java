package de.barryallenofearth.adventofcode2015.riddle.day12.json;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main_12_1 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-12.json");
		final List<Integer> allIntegers = new ArrayList<>();
		for (String string : strings) {
			int currentSum = 0;
			final String[] split = string.replaceAll("[^\\d\\-]+", "\n").split("\n");
			for (String number : split) {
				if (number.matches("-?\\d+")) {
					final int integer = Integer.parseInt(number);
					currentSum += integer;
					allIntegers.add(integer);
				}
			}
			System.out.println(currentSum);
		}
		System.out.println(allIntegers.stream().mapToInt(Integer::intValue).sum() + " is the total sum");
	}
}
