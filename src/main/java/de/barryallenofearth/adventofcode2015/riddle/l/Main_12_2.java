package de.barryallenofearth.adventofcode2015.riddle.l;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_12_2 {
	public static final String RED = "\"red\"";

	public static final Map<Character, Character> openingClosingPairs = Map.of('{', '}',
			'[', ']');

	public static final Map<Character, Character> closingOpeningPairs = Map.of('}', '{',
			']', '[');

	public static void main(String[] args) throws IOException, URISyntaxException {

		final List<String> strings = RiddleFileReader.readAllLines("riddle-12.txt");
		final List<Integer> allIntegers = new ArrayList<>();
		for (String string : strings) {
			int currentSum = 0;
			while (string.contains(RED)) {
				final int redIndex = string.indexOf(RED);
				int startIndexRect = leftBraceIndex(string, redIndex);
				int stopIndexRect = rightIndex(string, redIndex, startIndexRect);
				final String blockContainingRed = string.substring(startIndexRect, stopIndexRect + 1);
				System.out.println(blockContainingRed);
				if (!blockContainingRed.contains(RED)) {
					continue;
				}
				string = string.replace(blockContainingRed, "");
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

	private static int rightIndex(String string, int redIndex, int startIndexRect) {
		int timesRequired = 1;
		int stopIndexRect = Integer.MAX_VALUE;
		for (int index = startIndexRect + 1; index < string.length(); index++) {
			final char openingBraceType = string.charAt(startIndexRect);
			final Character requiredBraceType = openingClosingPairs.get(openingBraceType);
			final char currentChar = string.charAt(index);

			if (currentChar == openingBraceType) {
				timesRequired++;
			} else if (currentChar == requiredBraceType) {
				timesRequired--;
			}

			if (timesRequired == 0) {
				stopIndexRect = index;
				break;
			}

		}
		return stopIndexRect;
	}

	private static int leftBraceIndex(String string, int redIndex) {
		int startIndexRect = Integer.MIN_VALUE;
		for (int index = redIndex - 1; index >= 0; index--) {
			Map<Character, Integer> openingBracesRequiredCount = new HashMap<>();
			openingBracesRequiredCount.put('{', 1);
			openingBracesRequiredCount.put('[', 1);
			final char currentChar = string.charAt(index);
			Integer remainingCounts = openingBracesRequiredCount.get(currentChar);
			if (remainingCounts != null) {
				openingBracesRequiredCount.put(currentChar, --remainingCounts);
			} else if (closingOpeningPairs.get(currentChar) != null) {
				openingBracesRequiredCount.put(closingOpeningPairs.get(currentChar), openingBracesRequiredCount.get(closingOpeningPairs.get(currentChar)) + 1);
			}
			if (openingBracesRequiredCount.containsValue(0)) {
				startIndexRect = index;
				break;
			}
		}
		return startIndexRect;
	}
}
