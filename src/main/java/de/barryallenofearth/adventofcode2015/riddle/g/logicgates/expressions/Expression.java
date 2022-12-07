package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class Expression {

	private final String name;

	public abstract int evaluate();
}
