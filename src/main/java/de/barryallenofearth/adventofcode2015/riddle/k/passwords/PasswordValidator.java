package de.barryallenofearth.adventofcode2015.riddle.k.passwords;

import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {
	public static final Pattern PASSWORD_PATTERN = Pattern.compile("[a-z]{8}");

	public static final List<String> ILLEGAL_CHARS = List.of("i", "l", "o");

	public static boolean isValidPassword(String password) {
		if (!PASSWORD_PATTERN.matcher(password).matches()) {
			return false;
		}
		if (!containsIncreasingSequence(password)) {
			return false;
		}
		if (!containsNoIllegalChars(password)) {
			return false;
		}

		if (!contains2DoubleChars(password)) {
			return false;
		}

		return true;
	}

	public static boolean containsIncreasingSequence(String password) {

		char secondPreviousChar = password.charAt(0);
		char previousChar = password.charAt(1);
		for (int index = 2; index < password.length(); index++) {
			if (secondPreviousChar + 1 == previousChar && previousChar + 1 == password.charAt(index)) {
				return true;
			}
			secondPreviousChar = password.charAt(index - 1);
			previousChar = password.charAt(index);
		}

		return false;
	}

	public static boolean containsNoIllegalChars(String password) {
		for (String illegalChar : ILLEGAL_CHARS) {
			if (password.contains(illegalChar)) {
				return false;
			}
		}
		return true;
	}

	public static boolean contains2DoubleChars(String password) {
		int doubleSequenceFound = 0;
		for (int index = 1; index < password.length(); index++) {
			if (password.charAt(index) == password.charAt(index - 1)) {
				doubleSequenceFound++;
				if (doubleSequenceFound >= 2) {
					return true;
				}
				index++;

			}
		}
		return false;
	}
}
