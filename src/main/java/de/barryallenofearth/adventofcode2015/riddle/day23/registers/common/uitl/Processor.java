package de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.Instruction;
import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.ProcessingModel;
import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.Registers;

public class Processor {
    public Registers process(ProcessingModel processingModel) {

        int steps = 0;
        while (processingModel.getNextInstruction().isPresent()) {
            if (steps % 100_000_000 == 0) {
                System.out.println("register a: " + processingModel.getRegisters().getA());
                System.out.println("register b: " + processingModel.getRegisters().getB());
                final Instruction next = processingModel.getNextInstruction().get();
                System.out.println("index of next instruction: " + next.getIndex() + "; " + next.getOriginalInstruction());
                System.out.println();
            }
            final Instruction instruction = processingModel.getNextInstruction().get();
            instruction.getProcessAndNext().accept(processingModel);
            steps++;

        }
        return processingModel.getRegisters();
    }
}
