package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.*;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class DoubleExpression extends Expression {

	public DoubleExpression(String name, String expressionName1, String expressionName2) {
		super(name);
		this.expressionName1 = expressionName1;
		this.expressionName2 = expressionName2;
	}

	private final String expressionName1;

	private final String expressionName2;

	protected Expression expression1;

	protected Expression expression2;
}
