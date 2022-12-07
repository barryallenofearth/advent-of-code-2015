package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class SingleExpression extends Expression {

	public SingleExpression(String name, String internalExpressionName) {
		super(name);
		this.internalExpressionName = internalExpressionName;
	}

	private final String internalExpressionName;

	protected Expression internalExpression;
}
