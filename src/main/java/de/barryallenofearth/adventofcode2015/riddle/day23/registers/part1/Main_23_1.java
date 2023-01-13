package de.barryallenofearth.adventofcode2015.riddle.day23.registers.part1;

import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.ProcessingModel;
import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.uitl.InstructionReader;
import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.uitl.Processor;

public class Main_23_1 {
    public static void main(String[] args) {
        final InstructionReader instructionReader = new InstructionReader();
        final ProcessingModel processingModel = instructionReader.read();

        System.out.println(processingModel);
        final Processor processor = new Processor();
        processor.process(processingModel);
        System.out.println("register a: " + processingModel.getRegisters().getA());
        System.out.println("register b: " + processingModel.getRegisters().getB());
    }
}
