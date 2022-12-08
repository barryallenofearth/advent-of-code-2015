package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class DoubleExpression implements Expression {

	public DoubleExpression(String expressionName1, String expressionName2) {
		this.expressionName1 = expressionName1;
		this.expressionName2 = expressionName2;
	}

	private final String expressionName1;

	private final String expressionName2;

	protected Integer expression1;

	protected Integer expression2;
}
