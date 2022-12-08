package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class SingleExpression extends Expression {

	public SingleExpression(String name, String internalExpressionName) {
		super(name);
		this.internalExpressionName = internalExpressionName;
	}

	private final String internalExpressionName;

	protected Expression internalExpression;
}
