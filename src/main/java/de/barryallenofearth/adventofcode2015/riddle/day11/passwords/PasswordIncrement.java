package de.barryallenofearth.adventofcode2015.riddle.day11.passwords;

public class PasswordIncrement {

	public static String incrementPassword(String oldPassword) {

		StringBuilder ending = new StringBuilder();
		for (int index = oldPassword.length() - 1; index >= 0; index--) {
			char currentChar = oldPassword.charAt(index);
			if (currentChar < 'z') {
				return oldPassword.substring(0, index) + ++currentChar + ending.toString();
			}
			ending.append("a");
		}

		return oldPassword.substring(0, oldPassword.length() - 1);
	}
}
