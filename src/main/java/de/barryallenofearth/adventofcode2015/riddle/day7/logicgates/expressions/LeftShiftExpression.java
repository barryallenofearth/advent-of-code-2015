package de.barryallenofearth.adventofcode2015.riddle.day7.logicgates.expressions;

import lombok.Getter;

@Getter
public class LeftShiftExpression extends SingleExpression {

	public LeftShiftExpression(String internalExpressionName, int shiftCount) {
		super(internalExpressionName);
		this.shiftCount = shiftCount;
	}

	private final int shiftCount;

	@Override public int evaluate() {
		return value << shiftCount;
	}
}
