package de.barryallenofearth.adventofcode2015.riddle.day7.logicgates;

import de.barryallenofearth.adventofcode2015.riddle.day7.logicgates.expressions.*;
import de.barryallenofearth.adventofcode2015.riddle.g.logicgates.expressions.*;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public static void main(String[] args) throws IOException, URISyntaxException {
		final List<String> strings = RiddleFileReader.readAllLines("riddle-7.txt");

		final Map<String, Integer> resolvedNumber = new HashMap<>();
		for (int i = strings.size() - 1; i >= 0; i--) {
			String string = strings.get(i);
			final Matcher numberMatcher = NUMBER_PATTERN.matcher(string);
			if (numberMatcher.matches()) {
				resolvedNumber.put(numberMatcher.group(2), Integer.parseInt(numberMatcher.group(1)));
				strings.remove(string);
			}
		}

		final Map<String, Expression> resolvedExpression = new HashMap<>();

		for (int i = strings.size() - 1; i >= 0; i--) {
			String string = strings.get(i);
			determineExpression(string, resolvedExpression, resolvedNumber);
			strings.remove(string);
		}
		do {
			final List<String> itemsToRemove = new ArrayList<>();
			for (Map.Entry<String, Expression> entry : resolvedExpression.entrySet()) {
				deeperProcessExpressions(resolvedNumber, itemsToRemove, entry);
			}
			for (String item : itemsToRemove) {
				resolvedExpression.remove(item);
			}
		} while (!resolvedExpression.isEmpty());

		int actualValue = getUnsignedValue(resolvedNumber.get("a"));
		System.out.println(actualValue + " is the value of 'a'");
	}

	private static void deeperProcessExpressions(Map<String, Integer> resolvedNumber, List<String> itemsToRemove, Map.Entry<String, Expression> entry) {
		if (entry.getValue() instanceof SingleExpression) {
			final SingleExpression singleExpression = (SingleExpression) entry.getValue();
			final Integer number = resolvedNumber.get(singleExpression.getInternalExpressionName());
			if (singleExpression.getValue() == null && number != null) {
				singleExpression.setValue(number);
			}
			if (singleExpression.getValue() != null) {
				resolvedNumber.put(entry.getKey(), singleExpression.evaluate());
				itemsToRemove.add(entry.getKey());
			}
		} else if (entry.getValue() instanceof DoubleExpression) {
			final DoubleExpression doubleExpression = (DoubleExpression) entry.getValue();
			final Integer number1 = resolvedNumber.get(doubleExpression.getExpressionName1());
			final Integer number2 = resolvedNumber.get(doubleExpression.getExpressionName2());
			if (doubleExpression.getExpression1() == null && number1 != null) {
				doubleExpression.setExpression1(number1);
			}
			if (doubleExpression.getExpression2() == null && number2 != null) {
				doubleExpression.setExpression2(number2);
			}
			if (doubleExpression.getExpression1() != null && doubleExpression.getExpression2() != null) {
				resolvedNumber.put(entry.getKey(), doubleExpression.evaluate());
				itemsToRemove.add(entry.getKey());
			}

		}
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

	private static void determineExpression(String string, Map<String, Expression> resolvedExpression, Map<String, Integer> resolvedNumber) {
		final Matcher directMapping = DIRECT_MAPPING_PATTERN.matcher(string);
		if (directMapping.matches()) {
			final String parameter = directMapping.group(1);
			final String name = directMapping.group(2);
			final DirectMappingExpression expression = new DirectMappingExpression(parameter);
			if (resolveOrPut(resolvedExpression, resolvedNumber, parameter, name, expression)) {
				return;
			}
		}
		final Matcher notMatcher = NOT_PATTERN.matcher(string);
		if (notMatcher.matches()) {
			final String parameter = notMatcher.group(1);
			final String name = notMatcher.group(2);
			final BitwiseComlementExpression expression = new BitwiseComlementExpression(parameter);
			if (resolveOrPut(resolvedExpression, resolvedNumber, parameter, name, expression)) {
				return;
			}
		}
		final Matcher leftShiftMatcher = LSHIFT_PATTERN.matcher(string);
		if (leftShiftMatcher.matches()) {
			final String parameter = leftShiftMatcher.group(1);
			final String name = leftShiftMatcher.group(3);
			final LeftShiftExpression expression = new LeftShiftExpression(parameter, Integer.parseInt(leftShiftMatcher.group(2)));
			if (resolveOrPut(resolvedExpression, resolvedNumber, parameter, name, expression)) {
				return;
			}
		}
		final Matcher rightShiftMatcher = RSHIFT_PATTERN.matcher(string);
		if (rightShiftMatcher.matches()) {
			final String name = rightShiftMatcher.group(3);
			final String parameter = rightShiftMatcher.group(1);
			final RightShiftExpression expression = new RightShiftExpression(parameter, Integer.parseInt(rightShiftMatcher.group(2)));
			if (resolveOrPut(resolvedExpression, resolvedNumber, parameter, name, expression)) {
				return;
			}
		}
		final Matcher andMatcher = AND_PATTERN.matcher(string);
		if (andMatcher.matches()) {
			final String name = andMatcher.group(3);
			final String parameter1 = andMatcher.group(1);
			final String parameter2 = andMatcher.group(2);
			final AndExpression expression = new AndExpression(parameter1, parameter2);
			if (resolveOrSolveExpression(resolvedExpression, resolvedNumber, name, parameter1, parameter2, expression)) {
				return;
			}
		}
		final Matcher orMatcher = OR_PATTERN.matcher(string);
		if (orMatcher.matches()) {
			final String name = orMatcher.group(3);
			final String parameter1 = orMatcher.group(1);
			final String parameter2 = orMatcher.group(2);
			final OrExpression expression = new OrExpression(parameter1, parameter2);
			resolveOrSolveExpression(resolvedExpression, resolvedNumber, name, parameter1, parameter2, expression);
		}
	}

	private static boolean resolveOrSolveExpression(Map<String, Expression> resolvedExpression, Map<String, Integer> resolvedNumber, String name, String parameter1, String parameter2, DoubleExpression expression) {
		if (parameter1.matches("\\d+")) {
			expression.setExpression1(Integer.parseInt(parameter1));
		}
		if (parameter2.matches("\\d+")) {
			expression.setExpression2(Integer.parseInt(parameter2));
		}
		if (resolvedNumber.get(parameter1) != null) {
			expression.setExpression1(resolvedNumber.get(parameter1));
		}
		if (resolvedNumber.get(parameter2) != null) {
			expression.setExpression2(resolvedNumber.get(parameter2));
		}
		if (expression.getExpression1() != null && expression.getExpression2() != null) {
			resolvedNumber.put(name, expression.evaluate());
			return true;
		}
		resolvedExpression.put(name, expression);
		return false;
	}

	private static boolean resolveOrPut(Map<String, Expression> resolvedExpression, Map<String, Integer> resolvedNumber, String parameter, String name, SingleExpression expression) {
		if (resolvedNumber.get(parameter) != null) {
			expression.setValue(resolvedNumber.get(parameter));
			resolvedNumber.put(name, expression.evaluate());
			return true;
		}
		resolvedExpression.put(name, expression);
		return false;
	}

}
