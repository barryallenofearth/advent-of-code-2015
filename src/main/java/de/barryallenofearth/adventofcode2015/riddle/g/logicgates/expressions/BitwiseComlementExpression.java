package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BitwiseComlementExpression extends SingleExpression {

	public BitwiseComlementExpression(String name, String internalExpressionName) {
		super(name, internalExpressionName);
	}

	@Override public int evaluate() {
		//TODO prevent negative numbers
		return ~internalExpression.evaluate();
	}
}
