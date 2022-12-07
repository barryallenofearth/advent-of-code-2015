package de.barryallenofearth.adventofcode2015.riddle.d.hash.common;

import org.apache.commons.codec.digest.DigestUtils;

public class AdventCoinsMiner {

	public static int getLowestNumberForRequiredHash() {
		String input = "ckczppom";

		int currentNumber = 1;
		byte[] bytes = DigestUtils.md5(input + currentNumber);
		while (!(String.format("%02X", bytes[0]).equals("00") && String.format("%02X", bytes[1]).equals("00") && String.format("%02X", bytes[2]).matches("0\\d"))) {
			currentNumber++;
			bytes = DigestUtils.md5(input + currentNumber);
		}
		System.out.println(input + currentNumber);
		for (byte aByte : bytes) {
			System.out.print(String.format("%02X", aByte));

		}
		System.out.println("");
		return currentNumber;
	}
}
