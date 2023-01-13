package de.barryallenofearth.adventofcode2015.riddle.day24.sleighbalance.part2;

import de.barryallenofearth.adventofcode2015.riddle.day24.sleighbalance.part2.model.SleighWithTrunk;
import de.barryallenofearth.adventofcode2015.riddle.day24.sleighbalance.part2.uitl.SleighWithTrunkBalancer;

public class Main_24_2 {
	public static void main(String[] args) {

		final SleighWithTrunkBalancer sleighBalancer = new SleighWithTrunkBalancer();
		final SleighWithTrunk sleigh = sleighBalancer.balance();
		System.out.println(SleighWithTrunkBalancer.quantumEntanglement(sleigh));
	}
}
