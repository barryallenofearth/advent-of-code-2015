package de.barryallenofearth.adventofcode2015.riddle.day11.passwords;

public class Main_10_1 {

	public static void main(String[] args) {
		final String startPassword = "hxbxxyzz";
		String updatedPassword = startPassword;
		do {
			updatedPassword = PasswordIncrement.incrementPassword(updatedPassword);
		} while (!PasswordValidator.isValidPassword(updatedPassword));
		System.out.println(updatedPassword);
	}

}
