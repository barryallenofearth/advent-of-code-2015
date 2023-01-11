package de.barryallenofearth.adventofcode2015.riddle.day23.common.model;

import lombok.Data;

import java.util.function.Consumer;

@Data
public class Instruction {

    private final int index;

    private final String originalInstruction;

    private final Consumer<ProcessingModel> processAndNext;

}
