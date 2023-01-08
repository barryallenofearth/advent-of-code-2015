package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.part1;

import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.Ingredient;
import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl.BestMixEvaluator;
import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl.IngredientReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl.BestMixEvaluator.NUMBER_OF_TEASPOONS;

public class Main_15_1 {
	public static void main(String[] args) {
		final List<Ingredient> ingredientList = new IngredientReader().read();
		final BestMixEvaluator bestMixEvaluator = new BestMixEvaluator();

		final int maxAmountAllowed = NUMBER_OF_TEASPOONS - (ingredientList.size() - 1);
		final Map<String, Integer> ingredientTeaSpoonAmount = new HashMap<>();

		/*
		sugar = 35
		sprinkles = 34
		candy = 10
		chocolate = 21
		 */
		final Ingredient sugar = ingredientList.stream().filter(ingredient -> ingredient.getName().equals("Sugar")).findFirst().get();
		final Ingredient sprinkles = ingredientList.stream().filter(ingredient -> ingredient.getName().equals("Sprinkles")).findFirst().get();
		final Ingredient candy = ingredientList.stream().filter(ingredient -> ingredient.getName().equals("Candy")).findFirst().get();
		final Ingredient chocolate = ingredientList.stream().filter(ingredient -> ingredient.getName().equals("Chocolate")).findFirst().get();
		ingredientTeaSpoonAmount.put(sugar.getName(), 35);
		ingredientTeaSpoonAmount.put(sprinkles.getName(), 34);
		ingredientTeaSpoonAmount.put(candy.getName(), 10);
		ingredientTeaSpoonAmount.put(chocolate.getName(), 21);

		final Map<String, Integer> bestMix = bestMixEvaluator.determineBestMixture(ingredientList, ingredientTeaSpoonAmount);

		for (Map.Entry<String, Integer> entry : bestMix.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println(bestMixEvaluator.calculateScore(bestMix) + " is the highest score.");
	}
}
