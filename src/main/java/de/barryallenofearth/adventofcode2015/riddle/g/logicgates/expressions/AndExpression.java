package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;

@Getter
public class AndExpression extends DoubleExpression {

	public AndExpression(String expressionName1, String expressionName2) {
		super(expressionName1, expressionName2);
	}

	@Override public int evaluate() {
		return expression1 & expression2;
	}
}
