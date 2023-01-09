package de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part2;

import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.Replacement;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.model.ReplacementsAndMolecule;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.common.uitl.SingleReplacementVariantFinder;
import de.barryallenofearth.adventofcode2015.riddle.day19.medicine.part2.model.MoleculeState;

import java.util.*;
import java.util.stream.Collectors;

public class MoleculeGenerator {

    public static final String STARTING_MOLECULE = "e";

    public int determineMinimumNumberOfSteps(ReplacementsAndMolecule replacementsAndMolecule) {
        final List<Replacement> replacements = replacementsAndMolecule.getReplacements();
        final String targetMolecule = replacementsAndMolecule.getInitialMolecule();

        final SingleReplacementVariantFinder variantFinder = new SingleReplacementVariantFinder();
        List<MoleculeState> openNodes = new ArrayList<>();
        openNodes.add(new MoleculeState(0, STARTING_MOLECULE));
        //contains all currently or previously investigated molecule string
        final Set<String> seenMolecules = new HashSet<>();
        seenMolecules.add(STARTING_MOLECULE);

        int numberOfSteps = 0;

        while (!openNodes.isEmpty()) {
            final MoleculeState currentState = openNodes.get(0);
            openNodes.remove(currentState);
            currentState.setModificationSteps(currentState.getModificationSteps() + 1);

            final Set<String> variants = variantFinder.getVariants(new ReplacementsAndMolecule(replacements, currentState.getMolecule()))
                    .stream()
                    .filter(variant -> !seenMolecules.contains(variant))
                    .collect(Collectors.toSet());

            for (String variant : variants) {
                if (targetMolecule.equals(variant)) {
                    return currentState.getModificationSteps();
                }
                if (!seenMolecules.contains(variant) && variant.length() <= targetMolecule.length()) {
                    openNodes.add(new MoleculeState(currentState.getModificationSteps(), variant));
                }
                numberOfSteps++;
                if (numberOfSteps % 100_000 == 0) {
                    System.out.println(numberOfSteps + " were investigated.");
                    System.out.println(seenMolecules.size() + " unique molecules were found.");
                    System.out.println(openNodes.size() + " number of open nodes.");
                    System.out.println(openNodes.get(0).getMolecule() + " is the current top molecule after " + openNodes.get(0).getModificationSteps());
                    System.out.println(targetMolecule);
                    System.out.println("molecule length: number of encounters");
                    openNodes.stream().collect(Collectors.groupingBy(moleculeState -> moleculeState.getMolecule().length()))
                            .forEach((key, value) -> System.out.println(key + ": " + value.size()));
                    System.out.println();

                }
            }
            seenMolecules.addAll(variants);
            openNodes.sort(Comparator.comparingInt(MoleculeState::getModificationSteps));

        }
        return -1;
    }

}
