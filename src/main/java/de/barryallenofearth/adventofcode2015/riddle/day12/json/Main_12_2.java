package de.barryallenofearth.adventofcode2015.riddle.day12.json;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main_12_2 {
	public static final String RED = "\"red\"";

	public static final String GREEN = "\"green\"";

	public static void main(String[] args) throws IOException, URISyntaxException {

		final List<String> strings = RiddleFileReader.readAllLines("riddle-12.json");
		final List<Integer> allIntegers = new ArrayList<>();
		for (String string : strings) {
			int currentSum = 0;
			while (string.contains(RED)) {
				final int redIndex = string.indexOf(RED);
				int startIndexRect = findOpeningBraceIndex(string, redIndex);
				if (startIndexRect == -1) {
					string = string.replaceFirst(RED, GREEN);
					continue;
				}
				int stopIndexRect = findClosingBraceIndex(string, redIndex);
				final String blockContainingRed = string.substring(startIndexRect, stopIndexRect + 1);
				System.out.println(blockContainingRed);
				if (!blockContainingRed.contains(RED)) {
					continue;
				}
				string = string.replace(blockContainingRed, "");
				System.out.println();
			}

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

	public static int findOpeningBraceIndex(String json, int redStartingIndex) {
		int openingIndex = 0;
		int curlyBracesNeeded = 1;
		int rectBracesNeeded = 1;
		int currentIndex = redStartingIndex - 1;
		while (curlyBracesNeeded > 0 && rectBracesNeeded > 0) {
			if (json.charAt(currentIndex) == '[') {
				rectBracesNeeded--;
				openingIndex = currentIndex;
			} else if (json.charAt(currentIndex) == ']') {
				rectBracesNeeded++;
			}
			if (json.charAt(currentIndex) == '{') {
				curlyBracesNeeded--;
				openingIndex = currentIndex;
			} else if (json.charAt(currentIndex) == '}') {
				curlyBracesNeeded++;
			}
			currentIndex--;
		}
		return rectBracesNeeded == 0 ? -1 : openingIndex;
	}

	public static int findClosingBraceIndex(String json, int redStartingIndex) {
		int closingIndex = 0;
		int curlyBracesNeeded = 1;
		int rectBracesNeeded = 1;
		int currentIndex = redStartingIndex + 1;
		while (curlyBracesNeeded > 0 && rectBracesNeeded > 0) {
			if (json.charAt(currentIndex) == ']') {
				rectBracesNeeded--;
				closingIndex = currentIndex;
			} else if (json.charAt(currentIndex) == '[') {
				rectBracesNeeded++;
			}
			if (json.charAt(currentIndex) == '}') {
				curlyBracesNeeded--;
				closingIndex = currentIndex;
			} else if (json.charAt(currentIndex) == '{') {
				curlyBracesNeeded++;
			}
			currentIndex++;
		}
		return closingIndex;
	}

}
