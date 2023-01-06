package de.barryallenofearth.adventofcode2015.riddle.day4.hash.common;

import org.apache.commons.codec.digest.DigestUtils;

public class AdventCoinsMiner {

	public static int getLowestNumberForRequiredHash() {
		String input = "ckczppom";

		int currentNumber = 1;
		String bytes = DigestUtils.md5Hex(input + currentNumber);
		while (!bytes.startsWith("00000")) {
			currentNumber++;
			bytes = DigestUtils.md5Hex(input + currentNumber);
		}
		System.out.println(input + currentNumber);
		System.out.println(bytes);
		System.out.println("");
		return currentNumber;
	}
}
