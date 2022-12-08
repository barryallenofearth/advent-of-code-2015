package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;

@Getter
public class BitwiseComlementExpression extends SingleExpression {

	public BitwiseComlementExpression(String name, String internalExpressionName) {
		super(name, internalExpressionName);
	}

	@Override public int evaluate() {
		return ~internalExpression.evaluate();
	}
}
