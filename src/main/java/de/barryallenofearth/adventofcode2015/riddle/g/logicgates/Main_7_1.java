package de.barryallenofearth.adventofcode2015.riddle.g.logicgates;

import de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions.*;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_7_1 {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d+) -> (\\w+)$");

    private static final Pattern AND_PATTERN = Pattern.compile("^(\\w+) AND (\\w+) -> (\\w+)$");

    private static final Pattern OR_PATTERN = Pattern.compile("^(\\w+) OR (\\w+) -> (\\w+)$");

    private static final Pattern LSHIFT_PATTERN = Pattern.compile("^(\\w+) LSHIFT (\\d+) -> (\\w+)$");

    private static final Pattern RSHIFT_PATTERN = Pattern.compile("^(\\w+) RSHIFT (\\d+) -> (\\w+)$");

    private static final Pattern NOT_PATTERN = Pattern.compile("^NOT (\\w+) -> (\\w+)$");
    private static final Pattern DIRECT_MAPPING_PATTERN = Pattern.compile("^(\\w+) -> (\\w+)$");

    private static int expressionUsedCounter = 0;

    public static void main(String[] args) throws IOException, URISyntaxException {
        final List<String> strings = RiddleFileReader.readAllLines("riddle-7.txt");
        final List<Expression> allExpressions = new ArrayList<>();
        final Expression aExpression = findExpressionChain("a", strings, allExpressions);
        System.out.println(expressionUsedCounter);
        final int evaluate = aExpression.evaluate();
        int actualValue = getUnsignedValue(evaluate);
        System.out.println(actualValue + " is the value of 'a'");
    }

    private static int getUnsignedValue(int evaluate) {
        String binaryString = Integer.toBinaryString(evaluate);
        if (binaryString.length() > 16) {
            binaryString = binaryString.substring(binaryString.length() - 16);
        }
        int actualValue = 0;
        for (int bit = binaryString.length() - 1; bit >= 0; bit--) {
            if (binaryString.charAt(bit) == '1') {
                actualValue += Math.pow(2, binaryString.length() - 1 - bit);
            }
        }
        return actualValue;
    }

    private static Expression findExpressionChain(String name, List<String> allInstructions, List<Expression> allExpressions) {
        expressionUsedCounter++;
        final Expression expression = findExpressionByName(name, allInstructions, allExpressions);
        if (expression instanceof SingleExpression && ((SingleExpression) expression).getInternalExpression() != null) {
            return expression;

        } else if (expression instanceof DoubleExpression && (((DoubleExpression) expression).getExpression1() != null && ((DoubleExpression) expression).getExpression2() != null)) {
            return expression;
        }


        if (expression instanceof SingleExpression) {
            final Expression internalExpression = findExpressionChain(((SingleExpression) expression).getInternalExpressionName(), allInstructions, allExpressions);
            ((SingleExpression) expression).setInternalExpression(internalExpression);
        } else if (expression instanceof DoubleExpression) {
            if (((DoubleExpression) expression).getExpression1() == null) {
                ((DoubleExpression) expression).setExpression1(findExpressionChain(((DoubleExpression) expression).getExpressionName1(), allInstructions, allExpressions));
            }
            if (((DoubleExpression) expression).getExpression2() == null) {
                ((DoubleExpression) expression).setExpression2(findExpressionChain(((DoubleExpression) expression).getExpressionName2(), allInstructions, allExpressions));
            }
        }
        return expression;
    }

    private static Expression findExpressionByName(String name, List<String> allInstructions, List<Expression> allExpressions) {
        if (name.matches("\\d+")) {
            return new NumberExpression("no_name", Integer.parseInt(name));
        }

        final Optional<Expression> existingExpression = allExpressions.stream()
                .filter(expression -> expression.getName().equals(name))
                .findFirst();
        if (existingExpression.isPresent()) {
            final Expression expression = existingExpression.get();
            if (expression instanceof SingleExpression && ((SingleExpression) expression).getInternalExpression() != null) {
                if (((SingleExpression) expression).getInternalExpression() instanceof NumberExpression) {
                    final NumberExpression numberExpression = new NumberExpression(expression.getName(), expression.evaluate());
                    allExpressions.add(numberExpression);
                    allExpressions.remove(expression);
                    return numberExpression;
                }
                return expression;

            } else if (expression instanceof DoubleExpression && (((DoubleExpression) expression).getExpression1() != null && ((DoubleExpression) expression).getExpression2() != null)) {

                if (((DoubleExpression) expression).getExpression1() instanceof NumberExpression && ((DoubleExpression) expression).getExpression2() instanceof NumberExpression) {
                    final NumberExpression numberExpression = new NumberExpression(expression.getName(), expression.evaluate());
                    allExpressions.add(numberExpression);
                    allExpressions.remove(expression);
                    return numberExpression;
                }
                return expression;
            }
            return expression;
        }
        final Optional<String> first = allInstructions.stream()
                .filter(instruction -> instruction.split("-> ")[1].trim().equals(name))
                .findFirst();
        final String matchingInstruction = first.get();
        allInstructions.remove(matchingInstruction);
        final Expression expression = determineExpression(matchingInstruction);
        allExpressions.add(expression);
        return expression;
    }

    private static Expression determineExpression(String string) {
        final Matcher numberMatcher = NUMBER_PATTERN.matcher(string);
        if (numberMatcher.matches()) {
            return new NumberExpression(numberMatcher.group(2), Integer.parseInt(numberMatcher.group(1)));
        }
        final Matcher directMApping = DIRECT_MAPPING_PATTERN.matcher(string);
        if (directMApping.matches()) {
            return new DirectMappingExpression(directMApping.group(2), directMApping.group(1));
        }
        final Matcher notMatcher = NOT_PATTERN.matcher(string);
        if (notMatcher.matches()) {
            return new BitwiseComlementExpression(notMatcher.group(2), notMatcher.group(1));
        }
        final Matcher leftShiftMatcher = LSHIFT_PATTERN.matcher(string);
        if (leftShiftMatcher.matches()) {
            return new LeftShiftExpression(leftShiftMatcher.group(3), leftShiftMatcher.group(1), Integer.parseInt(leftShiftMatcher.group(2)));
        }
        final Matcher rightShiftMatcher = RSHIFT_PATTERN.matcher(string);
        if (rightShiftMatcher.matches()) {
            return new RightShiftExpression(rightShiftMatcher.group(3), rightShiftMatcher.group(1), Integer.parseInt(rightShiftMatcher.group(2)));
        }
        final Matcher andMatcher = AND_PATTERN.matcher(string);
        if (andMatcher.matches()) {
            return new AndExpression(andMatcher.group(3), andMatcher.group(1), andMatcher.group(2));
        }
        final Matcher orMatcher = OR_PATTERN.matcher(string);
        if (orMatcher.matches()) {
            return new OrExpression(orMatcher.group(3), orMatcher.group(1), orMatcher.group(2));
        }
        return null;
    }

    private static Expression getExpression(List<Expression> allExpressions, String expressionName) {
        final Expression internalExpression = allExpressions.stream()
                .filter(currentExpression -> currentExpression.getName().equals(expressionName))
                .findAny()
                .get();
        return internalExpression;
    }
}
