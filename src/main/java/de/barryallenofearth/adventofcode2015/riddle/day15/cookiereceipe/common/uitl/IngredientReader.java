package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.Ingredient;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IngredientReader {

	public static final Pattern INGREDIENT_PATTERN = Pattern.compile("(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");

	public List<Ingredient> read() {
		final List<Ingredient> ingredientList = new ArrayList<>();

		final List<String> strings = RiddleFileReader.readAllLines("riddle-15.txt");
		for (String line : strings) {
			final Matcher matcher = INGREDIENT_PATTERN.matcher(line);
			if (matcher.matches()) {
				final String name = matcher.group(1);
				final int capacity = Integer.parseInt(matcher.group(2));
				final int durability = Integer.parseInt(matcher.group(3));
				final int flavor = Integer.parseInt(matcher.group(4));
				final int texture = Integer.parseInt(matcher.group(5));
				final int calories = Integer.parseInt(matcher.group(6));

				ingredientList.add(new Ingredient(name, capacity, durability, flavor, texture, calories));
			}

		}
		return ingredientList;
	}
}
