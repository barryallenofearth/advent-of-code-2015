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
        final Set<String> seenMolecules = new HashSet<>();
        seenMolecules.add(STARTING_MOLECULE);

        int minNumberOfSteps = 0;

        Map<Integer, List<String>> openNodes = new HashMap<>();
        openNodes.put(minNumberOfSteps, new ArrayList<>() {{
            add(STARTING_MOLECULE);
        }});

        int variantsEvaluated = 0;
        while (!openNodes.isEmpty()) {
            final String currentMolecule = openNodes.get(minNumberOfSteps).get(0);
            openNodes.get(minNumberOfSteps).remove(currentMolecule);

            final Set<String> variants = variantFinder.getVariants(new ReplacementsAndMolecule(replacements, currentMolecule))
                    .stream()
                    .filter(variant -> !seenMolecules.contains(variant))
                    .collect(Collectors.toSet());

            for (String variant : variants) {
                if (targetMolecule.equals(variant)) {
                    return minNumberOfSteps;
                }
                if (!seenMolecules.contains(variant) && variant.length() <= targetMolecule.length()) {
                    final int nextStep = minNumberOfSteps + 1;
                    if (!openNodes.containsKey(nextStep)) {
                        openNodes.put(nextStep, new ArrayList<>());
                    }
                    openNodes.get(nextStep).add(variant);
                }
                variantsEvaluated++;
                if (variantsEvaluated % 100_000 == 0) {
                    System.out.println(variantsEvaluated + " were investigated.");
                    System.out.println(seenMolecules.size() + " unique molecules were found.");
                    System.out.println(openNodes.values().stream().mapToInt(List::size).sum() + " number of open nodes.");
                    System.out.println(openNodes.get(minNumberOfSteps).get(0) + " is the current top molecule after " + minNumberOfSteps);
                    System.out.println(targetMolecule);
                    System.out.println("replacements taken: number of encounters");
                    openNodes.forEach((key, value) -> System.out.println(key + ": " + value.size()));
                    System.out.println();

                }
            }
            seenMolecules.addAll(variants);
            if (openNodes.get(minNumberOfSteps).isEmpty()) {
                openNodes.remove(minNumberOfSteps);
                minNumberOfSteps++;
            }

        }
        return -1;
    }

}
