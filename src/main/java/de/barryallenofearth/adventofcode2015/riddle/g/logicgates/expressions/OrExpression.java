package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrExpression extends DoubleExpression {

	public OrExpression(String name, String expressionName1, String expressionName2) {
		super(name, expressionName1, expressionName2);
	}

	@Override public int evaluate() {
		return expression1.evaluate() | expression2.evaluate();
	}
}
