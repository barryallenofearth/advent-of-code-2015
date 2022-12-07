package de.barryallenofearth.adventofcode2015.riddle.f.lights.part2;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Command {
	private final LightOperation lightOperation;

	private final int columnStart;

	private final int rowStart;

	private final int columnEnd;

	private final int rowEnd;
}
