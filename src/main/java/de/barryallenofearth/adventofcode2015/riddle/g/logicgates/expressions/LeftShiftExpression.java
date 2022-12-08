package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;

@Getter
public class LeftShiftExpression extends SingleExpression {

	public LeftShiftExpression(String name, String internalExpressionName, int shiftCount) {
		super(name, internalExpressionName);
		this.shiftCount = shiftCount;
	}

	private final int shiftCount;

	@Override public int evaluate() {
		return internalExpression.evaluate() << shiftCount;
	}
}
