package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;

@Getter
public class RightShiftExpression extends SingleExpression {

	public RightShiftExpression(String internalExpressionName, int shiftCount) {
		super(internalExpressionName);
		this.shiftCount = shiftCount;
	}

	private final int shiftCount;

	@Override public int evaluate() {
		return value >>> shiftCount;
	}
}
