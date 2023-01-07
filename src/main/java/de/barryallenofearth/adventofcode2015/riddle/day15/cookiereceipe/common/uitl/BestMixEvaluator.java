package de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.DistributionState;
import de.barryallenofearth.adventofcode2015.riddle.day15.cookiereceipe.common.model.Ingredient;

import java.util.*;
import java.util.stream.Collectors;

public class BestMixEvaluator {

	public static final int NUMBER_OF_TEASPOONS = 100;

	private Map<String, Ingredient> nameIngredientCache;

	public Map<String, Integer> determineBestMixture(List<Ingredient> ingredientList) {
		nameIngredientCache = ingredientList.stream().collect(Collectors.toMap(Ingredient::getName, ingredient -> ingredient));
		final int maxAmountAllowed = NUMBER_OF_TEASPOONS - (ingredientList.size() - 1);

		final Map<String, Integer> ingredientTeaSpoonAmount = ingredientList.stream()
				.collect(Collectors.toMap(Ingredient::getName, ingredient -> 1));
		ingredientTeaSpoonAmount.put(ingredientList.get(0).getName(), maxAmountAllowed);

		Map<String, Integer> bestMixture = new HashMap<>(ingredientTeaSpoonAmount);
		long maxScore = calculateScore(bestMixture);

		Set<DistributionState> evaluatedStates = new HashSet<>();
		Stack<DistributionState> openNodes = new Stack<>();
		openNodes.add(new DistributionState(bestMixture.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));

		while (!openNodes.isEmpty()) {
			final DistributionState currentState = openNodes.pop();
			final long score = calculateScore(currentState.getIngredientsAndAmount());
			if (score > maxScore) {
				bestMixture = currentState.getIngredientsAndAmount();
				maxScore = score;
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

	private boolean ignoreBranch(Map<String, Integer> ingredientTeaSpoonAmount) {
		for (Map.Entry<String, Integer> entry : ingredientTeaSpoonAmount.entrySet()) {
			final Integer numberOfTeaSpoons = entry.getValue();
			final Ingredient ingredient = nameIngredientCache.get(entry.getKey());

		}

		return false;
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
