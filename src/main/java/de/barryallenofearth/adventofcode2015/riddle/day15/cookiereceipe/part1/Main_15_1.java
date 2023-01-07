package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.part1;

import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.Ingredient;
import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl.BestMixEvaluator;
import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl.IngredientReader;

import java.util.List;
import java.util.Map;

public class Main_15_1 {
	public static void main(String[] args) {
		final List<Ingredient> ingredientList = new IngredientReader().read();
		final BestMixEvaluator bestMixEvaluator = new BestMixEvaluator();
		final Map<String, Integer> bestMix = bestMixEvaluator.determineBestMixture(ingredientList);

		for (Map.Entry<String, Integer> entry : bestMix.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println(bestMixEvaluator.calculateScore(bestMix) + " is the highest score.");
	}
}
