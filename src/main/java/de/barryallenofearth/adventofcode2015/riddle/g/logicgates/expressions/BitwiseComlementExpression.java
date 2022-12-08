package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;

@Getter
public class BitwiseComlementExpression extends SingleExpression {

	public BitwiseComlementExpression(String internalExpressionName) {
		super(internalExpressionName);
	}

	@Override public int evaluate() {
		return ~value;
	}
}
