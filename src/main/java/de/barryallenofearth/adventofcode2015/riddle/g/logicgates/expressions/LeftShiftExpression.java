package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LeftShiftExpression extends SingleExpression {

	public LeftShiftExpression(String name, String internalExpressionName, int shiftCount) {
		super(name, internalExpressionName);
		this.shiftCount = shiftCount;
	}

	private final int shiftCount;

	@Override public int evaluate() {
		return internalExpression.evaluate() << shiftCount;
	}
}
