package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class SingleExpression implements Expression {

	public SingleExpression(String internalExpressionName) {
		this.internalExpressionName = internalExpressionName;
	}

	private final String internalExpressionName;

	protected Integer value;

}
