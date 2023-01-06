package de.barryallenofearth.adventofcode2015.riddle.day6.lights.part2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum LightOperation {
	TURN_ON(brightness -> brightness + 1, "turn on"),

	TURN_OFF(brightness -> brightness == 0 ? 0 : brightness - 1, "turn off"),

	TOGGLE(brightness -> brightness + 2, "toggle");

	private final Function<Integer, Integer> switchingFunction;

	private final String key;

	public static LightOperation getByKey(String key) {
		for (LightOperation value : LightOperation.values()) {
			if (value.getKey().equals(key)) {
				return value;
			}
		}
		return null;
	}
}
