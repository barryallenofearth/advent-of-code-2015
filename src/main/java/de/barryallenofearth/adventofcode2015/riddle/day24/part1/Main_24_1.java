package de.barryallenofearth.adventofcode2015.riddle.day24.part1;

import de.barryallenofearth.adventofcode2015.riddle.day24.part1.model.Sleigh;
import de.barryallenofearth.adventofcode2015.riddle.day24.part1.uitl.SleighBalancer;

public class Main_24_1 {
	public static void main(String[] args) {
		final SleighBalancer sleighBalancer = new SleighBalancer();
		final Sleigh sleigh = sleighBalancer.balance();
		System.out.println(SleighBalancer.quantumEntanglement(sleigh));
	}
}
