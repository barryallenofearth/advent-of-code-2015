package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.Ingredient;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestMixEvaluator {

	public static final int NUMBER_OF_TEASPOONS = 100;

	public Map<Ingredient, Integer> determineBestMixture(List<Ingredient> ingredientList) {
		final Map<Ingredient, Integer> ingredientTeaSpoonAmount = ingredientList.stream()
				.collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 0));
		ingredientTeaSpoonAmount.put(ingredientList.get(0), NUMBER_OF_TEASPOONS);

		final Map<Ingredient, Integer> bestMixture = new HashMap<>(ingredientTeaSpoonAmount);
		long maxScore = calculateScore(bestMixture);



		return bestMixture;
	}

	public long calculateScore(Map<Ingredient, Integer> ingredientTeaSpoonAmount) {
		int capacity = 0, durability = 0, flavor = 0, texture = 0;

		for (Map.Entry<Ingredient, Integer> entry : ingredientTeaSpoonAmount.entrySet()) {
			final Integer numberOfTeaSpoons = entry.getValue();
			final Ingredient ingredient = entry.getKey();
			capacity += ingredient.getCapacity() * numberOfTeaSpoons;
			durability += ingredient.getDurability() * numberOfTeaSpoons;
			flavor += ingredient.getFlavor() * numberOfTeaSpoons;
			texture += ingredient.getTexture() * numberOfTeaSpoons;
		}
		return Math.max(capacity * durability * flavor * texture, 0);
	}
}
