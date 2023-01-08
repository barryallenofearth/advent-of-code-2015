package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.DistributionState;
import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.Ingredient;
import org.checkerframework.framework.qual.LiteralKind;

import java.util.*;
import java.util.stream.Collectors;

public class BestMixEvaluator {

	public static final int NUMBER_OF_TEASPOONS = 100;

	public static final String SUGAR = "Sugar";

	public static final String SPRINKLES = "Sprinkles";

	public static final String CANDY = "Candy";

	public static final String CHOCOLATE = "Chocolate";

	private Map<String, Ingredient> nameIngredientCache;

	public Map<String, Integer> determineBestMixture(List<Ingredient> ingredientList, final Map<String, Integer> ingredientTeaSpoonAmount) {
		nameIngredientCache = ingredientList.stream().collect(Collectors.toMap(Ingredient::getName, ingredient -> ingredient));
		final int maxAmountAllowed = NUMBER_OF_TEASPOONS - (ingredientList.size() - 1);

		Map<String, Integer> bestMixture = new HashMap<>(ingredientTeaSpoonAmount);
		long maxScore = calculateScore(bestMixture);
		System.out.println("Current maximum is " + maxScore);

		Set<DistributionState> evaluatedStates = new HashSet<>();
		Stack<DistributionState> openNodes = new Stack<>();
		openNodes.add(new DistributionState(new HashMap<>(bestMixture)));

		while (!openNodes.isEmpty()) {
			final DistributionState currentState = openNodes.pop();
			final long score = calculateScore(currentState.getIngredientsAndAmount());
			if (score > maxScore && useBranch(currentState.getIngredientsAndAmount())) {
				bestMixture = currentState.getIngredientsAndAmount();
				maxScore = score;
				System.out.println("new max found: " + maxScore);
			}
			for (Ingredient from : ingredientList) {
				if (currentState.getIngredientsAndAmount().get(from.getName()) == 1) {
					continue;
				}
				for (Ingredient to : ingredientList) {
					if (from == to || currentState.getIngredientsAndAmount().get(to.getName()) == maxAmountAllowed) {
						continue;
					}
					final HashMap<String, Integer> next = new HashMap<>(currentState.getIngredientsAndAmount());
					next.put(from.getName(), currentState.getIngredientsAndAmount().get(from.getName()) - 1);
					next.put(to.getName(), currentState.getIngredientsAndAmount().get(to.getName()) + 1);
					DistributionState nextState = new DistributionState(next);
					if (!evaluatedStates.contains(nextState)) {
						openNodes.add(nextState);
					}
				}
			}
			if (evaluatedStates.size() % 10_000 == 0) {
				System.out.println(evaluatedStates.size() + " states evaluated");
			}
			evaluatedStates.add(currentState);
		}

		return bestMixture;
	}

	private boolean useBranch(Map<String, Integer> ingredientTeaSpoonAmount) {
		final Integer sugar = ingredientTeaSpoonAmount.get(SUGAR);
		final Integer sprinkles = ingredientTeaSpoonAmount.get(SPRINKLES);
		final Integer candy = ingredientTeaSpoonAmount.get(CANDY);
		final Integer chocolate = ingredientTeaSpoonAmount.get(CHOCOLATE);

		int calories = 0;
		for (Map.Entry<String, Integer> entry : ingredientTeaSpoonAmount.entrySet()) {
			calories += nameIngredientCache.get(entry.getKey()).getCalories() * entry.getValue();
		}
		return calories == 500;
	}

	public long calculateScore(Map<String, Integer> ingredientTeaSpoonAmount) {
		int capacity = 0, durability = 0, flavor = 0, texture = 0;

		for (Map.Entry<String, Integer> entry : ingredientTeaSpoonAmount.entrySet()) {
			final Integer numberOfTeaSpoons = entry.getValue();
			final Ingredient ingredient = nameIngredientCache.get(entry.getKey());
			capacity += ingredient.getCapacity() * numberOfTeaSpoons;
			durability += ingredient.getDurability() * numberOfTeaSpoons;
			flavor += ingredient.getFlavor() * numberOfTeaSpoons;
			texture += ingredient.getTexture() * numberOfTeaSpoons;
		}
		return Math.max(capacity, 0) * Math.max(durability, 0) * Math.max(flavor, 0) * Math.max(texture, 0);
	}
}
