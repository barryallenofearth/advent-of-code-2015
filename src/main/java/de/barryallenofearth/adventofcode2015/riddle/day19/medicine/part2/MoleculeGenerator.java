package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part2;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.Replacement;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl.SingleReplacementVariantFinder;

import java.util.*;
import java.util.stream.Collectors;

public class MoleculeGenerator {

    public static final String STARTING_MOLECULE = "e";

    public int determineMinimumNumberOfSteps(ReplacementsAndMolecule replacementsAndMolecule) {
        final List<Replacement> replacements = replacementsAndMolecule.getReplacements();
        final String targetMolecule = replacementsAndMolecule.getInitialMolecule();

        final SingleReplacementVariantFinder variantFinder = new SingleReplacementVariantFinder();
        //contains all currently or previously investigated molecule string

        int minNumberOfSteps = 0;

        Map<Integer, TreeSet<String>> openNodes = new HashMap<>();
        openNodes.put(minNumberOfSteps, new TreeSet<>() {{
            add(STARTING_MOLECULE);
        }});

        final int[] variantsEvaluated = {0};
        while (!openNodes.isEmpty()) {
            final String currentMolecule = openNodes.get(minNumberOfSteps).first();
            openNodes.get(minNumberOfSteps).remove(currentMolecule);

            final Set<String> potentialVariants = variantFinder.getVariants(new ReplacementsAndMolecule(replacements, currentMolecule));

            final int currentSteps = minNumberOfSteps;
            final Set<String> variants = potentialVariants
                    .stream()
                    .peek(variant -> {
                        variantsEvaluated[0] = variantsEvaluated[0] + 1;
                        if (variantsEvaluated[0] % 1_000_000 == 0) {
                            System.out.println(variantsEvaluated[0] + " were investigated.");
                            System.out.println(openNodes.values().stream().mapToInt(Set::size).sum() + " number of open nodes.");
                            System.out.println(openNodes.get(currentSteps).first() + " is the current top molecule after " + currentSteps);
                            System.out.println(targetMolecule);
                            System.out.println("replacements taken: number of encounters");
                            openNodes.forEach((key, value) -> System.out.println(key + ": " + value.size()));
                            System.out.println();

                        }
                    })
                    .collect(Collectors.toSet());

            for (String variant : variants) {
                if (targetMolecule.equals(variant)) {
                    return minNumberOfSteps;
                }
                if (variant.length() <= targetMolecule.length()) {
                    final int nextStep = minNumberOfSteps + 1;
                    if (!openNodes.containsKey(nextStep)) {
                        openNodes.put(nextStep, new TreeSet<>());
                    }
                    openNodes.get(nextStep).add(variant);
                }
            }
            if (openNodes.get(minNumberOfSteps).isEmpty()) {
                openNodes.remove(minNumberOfSteps);
                minNumberOfSteps++;
            }

        }
        return -1;
    }

}
