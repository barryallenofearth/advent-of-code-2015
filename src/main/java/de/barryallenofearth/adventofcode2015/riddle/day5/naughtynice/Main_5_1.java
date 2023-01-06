package de.barryallenofearth.adventofcode2015.riddle.day5.naughtynice;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main_5_1 {
	public static final List<String> VOWELS = List.of("a", "e", "i", "o", "u");

	public static final List<String> ILLEGAL_SEQUENCE = List.of("ab", "cd", "pq", "xy");

	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-5.txt");
		int niceCount = 0, naughtyCount = 0;
		for (String string : strings) {
			if (contains3Vowels(string) && contains2SymbolsInARow(string) && doesNotContainIllegalSequence(string)) {
				System.out.println("nice: " + string);
				niceCount++;
			} else {
				System.out.println("naughty: " + string);
				naughtyCount++;
			}
		}

		System.out.println("Santa's list contains " + niceCount + " nice strings and " + naughtyCount + " naughty strings.");

	}

	public static boolean contains3Vowels(String string) {

		int vowelCount = 0;
		for (int index = 0; index < string.length(); index++) {
			if (VOWELS.contains(string.substring(index, index + 1))) {
				vowelCount++;
			}
		}

		return vowelCount >= 3;
	}

	public static boolean contains2SymbolsInARow(String string) {
		char previousChar = string.charAt(0);
		for (int index = 1; index < string.length(); index++) {
			final char currentChar = string.charAt(index);
			if (currentChar == previousChar) {
				return true;
			}
			previousChar = currentChar;
		}
		return false;
	}

	public static boolean doesNotContainIllegalSequence(String string) {
		for (String illegal : ILLEGAL_SEQUENCE) {
			if (string.contains(illegal)) {
				return false;
			}
		}
		return true;
	}
}
