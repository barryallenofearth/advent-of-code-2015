package de.barryallenofearth.adventofcode2015.riddle.j.lookandsay;

public class Main_10_1 {

	public static void main(String[] args) {
		String sequence = "1113122113";

		for (int count = 0; count < 50; count++) {
			sequence = createLookAndSayString(sequence);
			System.out.println(sequence.length());
		}

	}

	public static String createLookAndSayString(String input) {
		char previousChar = input.charAt(0);
		StringBuilder sequence = new StringBuilder();
		int currentSequenceCounter = 0;
		for (int index = 0; index < input.length(); index++) {
			final char currentChar = input.charAt(index);
			if (previousChar == currentChar) {
				currentSequenceCounter++;
			} else {
				sequence.append(currentSequenceCounter).append(previousChar);
				currentSequenceCounter = 1;
			}
			previousChar = currentChar;
		}

		sequence.append(currentSequenceCounter).append(previousChar);
		return sequence.toString();
	}

}
