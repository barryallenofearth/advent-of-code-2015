package de.barryallenofearth.adventofcode2015.riddle.day5.naughtynice;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main_5_2 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-5.txt");
		int niceCount = 0, naughtyCount = 0;
		for (String string : strings) {
			if (containsDoubleSequence(string) && contains2SymbolsInASkipRow(string)) {
				System.out.println("nice: " + string);
				niceCount++;
			} else {
				System.out.println("naughty: " + string);
				naughtyCount++;
			}
		}

		System.out.println("Santa's list contains " + niceCount + " nice strings and " + naughtyCount + " naughty strings.");

	}

	public static boolean containsDoubleSequence(String string) {
		for (int index = 0; index < string.length() - 2; index++) {
			String currentSequence = string.substring(index, index + 2);
			String remainder = string.substring(index + 2);
			if (remainder.contains(currentSequence)) {
				return true;
			}
		}

		return false;
	}

	public static boolean contains2SymbolsInASkipRow(String string) {
		for (int index = 0; index < string.length() - 2; index++) {
			if (string.charAt(index) == string.charAt(index + 2)) {
				return true;
			}
		}
		return false;
	}

}
