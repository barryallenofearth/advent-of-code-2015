package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

public class DirectMappingExpression extends SingleExpression {

	public DirectMappingExpression(String internalExpressionName) {
		super(internalExpressionName);
	}

	@Override
	public int evaluate() {
		return value;
	}
}
