package de.barryallenofearth.adventofcode2015.riddle.g.logicgates;

import de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions.*;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_7_1 {
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d+) -> (\\w+)$");

	private static final Pattern AND_PATTERN = Pattern.compile("^(\\w+) AND (\\w+) -> (\\w+)$");

	private static final Pattern OR_PATTERN = Pattern.compile("^(\\w+) OR (\\w+) -> (\\w+)$");

	private static final Pattern LSHIFT_PATTERN = Pattern.compile("^(\\w+) LSHIFT (\\d+) -> (\\w+)$");

	private static final Pattern RSHIFT_PATTERN = Pattern.compile("^(\\w+) LSHIFT (\\d+) -> (\\w+)$");

	private static final Pattern NOT_PATTERN = Pattern.compile("^NOT (\\w+) -> (\\w+)$");

	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-7.txt");

		List<Expression> allExpressions = new ArrayList<>();
		int index = strings.size() - 1;
		for (String string : strings) {
			final Matcher numberMatcher = NUMBER_PATTERN.matcher(string);
			if (numberMatcher.matches()) {
				allExpressions.add(new NumberExpression(numberMatcher.group(2), Integer.parseInt(numberMatcher.group(1))));
				continue;
			}
			final Matcher notMatcher = NOT_PATTERN.matcher(string);
			if (notMatcher.matches()) {
				allExpressions.add(new BitwiseComlementExpression(notMatcher.group(2), notMatcher.group(1)));
				continue;
			}
			final Matcher leftShiftMatcher = LSHIFT_PATTERN.matcher(string);
			if (leftShiftMatcher.matches()) {
				allExpressions.add(new LeftShiftExpression(leftShiftMatcher.group(3), leftShiftMatcher.group(1), Integer.parseInt(leftShiftMatcher.group(2))));
				continue;
			}
			final Matcher rightShiftMatcher = RSHIFT_PATTERN.matcher(string);
			if (rightShiftMatcher.matches()) {
				allExpressions.add(new LeftShiftExpression(rightShiftMatcher.group(3), rightShiftMatcher.group(1), Integer.parseInt(rightShiftMatcher.group(2))));
				continue;
			}
			final Matcher andMatcher = AND_PATTERN.matcher(string);
			if (andMatcher.matches()) {
				allExpressions.add(new AndExpression(andMatcher.group(3), andMatcher.group(1), andMatcher.group(2)));
				continue;
			}
			final Matcher orMatcher = OR_PATTERN.matcher(string);
			if (orMatcher.matches()) {
				allExpressions.add(new OrExpression(orMatcher.group(3), orMatcher.group(1), orMatcher.group(2)));
			}
		}

		for (Expression expression : allExpressions) {
			if (expression instanceof SingleExpression) {
				final Expression internalExpression = getExpression(allExpressions, ((SingleExpression) expression).getInternalExpressionName());
				((SingleExpression) expression).setInternalExpression(internalExpression);
			} else if (expression instanceof DoubleExpression) {
				((DoubleExpression) expression).setExpression1(getExpression(allExpressions, ((DoubleExpression) expression).getExpressionName1()));
				((DoubleExpression) expression).setExpression2(getExpression(allExpressions, ((DoubleExpression) expression).getExpressionName2()));
			}
		}
		for (Expression ex : allExpressions) {
			System.out.println(ex.getName() + ": " + ex.evaluate());
		}
	}

	private static Expression getExpression(List<Expression> allExpressions, String expressionName) {
		final Expression internalExpression = allExpressions.stream()
				.filter(currentExpression -> currentExpression.getName().equals(expressionName))
				.findAny()
				.get();
		return internalExpression;
	}
}
