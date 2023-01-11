package de.barryallenofearth.adventofcode2015.riddle.day23.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class ProcessingModel {
    private Optional<Instruction> nextInstruction;

    private final List<Instruction> allInstructions;

    private final Registers registers;
}
