package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

public class DirectMappingExpression extends SingleExpression {

    public DirectMappingExpression(String name, String internalExpressionName) {
        super(name, internalExpressionName);
    }

    @Override
    public int evaluate() {
        return internalExpression.evaluate();
    }
}
