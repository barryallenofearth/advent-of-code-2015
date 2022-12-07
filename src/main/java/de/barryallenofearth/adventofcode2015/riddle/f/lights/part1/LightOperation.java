package de.barryallenofearth.adventofcode2015.riddle.f.lights.part1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum LightOperation {
	TURN_ON(status -> Status.ON, "turn on"),
	TURN_OFF(status -> Status.OFF, "turn off"),
	TOGGLE(status -> status == Status.ON ? Status.OFF : Status.ON, "toggle");

	private final Function<Status, Status> switchingFunction;

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
