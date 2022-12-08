package de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions;

import lombok.Getter;

@Getter
public class NumberExpression extends Expression {

    public NumberExpression(String name, int number) {
        super(name);
        this.number = number;
    }

    private final int number;

    @Override
    public int evaluate() {
        return number;
    }
}
