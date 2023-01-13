package de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.Instruction;
import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.ProcessingModel;
import de.barryallenofearth.adventofcode2015.riddle.day23.registers.common.model.Registers;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionReader {
    public static final Pattern HALF = Pattern.compile("^hlf\\s+(\\w)$");
    public static final Pattern TRIPPLE = Pattern.compile("^tpl\\s+(\\w)$");
    public static final Pattern INCREMENT = Pattern.compile("^inc\\s+(\\w)$");
    public static final Pattern JUMP = Pattern.compile("^jmp\\s+([+-]\\d+)$");
    public static final Pattern JUMP_IF_EVEN = Pattern.compile("^jie (\\w),\\s+([+-]\\d+)$");
    public static final Pattern JUMP_IF_ONE = Pattern.compile("^jio (\\w),\\s+([+-]\\d+)$");

    public ProcessingModel read() {
        final List<Instruction> allInstructions = new ArrayList<>();
        final List<String> strings = RiddleFileReader.readAllLines("riddle-23.txt");
        for (int index = 0; index < strings.size(); index++) {
            String string = strings.get(index);
            final Matcher halfMatcher = HALF.matcher(string);
            if (halfMatcher.matches()) {
                half(allInstructions, index, halfMatcher);
                continue;
            }
            final Matcher trippleMatcher = TRIPPLE.matcher(string);
            if (trippleMatcher.matches()) {
                tripple(allInstructions, index, trippleMatcher);
                continue;
            }
            final Matcher incrementMatcher = INCREMENT.matcher(string);
            if (incrementMatcher.matches()) {
                increment(allInstructions, index, incrementMatcher);
                continue;
            }
            final Matcher jumpMatcher = JUMP.matcher(string);
            if (jumpMatcher.matches()) {
                jump(allInstructions, index, jumpMatcher);
                continue;
            }
            final Matcher jumpIfEvenMatcher = JUMP_IF_EVEN.matcher(string);
            if (jumpIfEvenMatcher.matches()) {
                jumpIfEven(allInstructions, index, jumpIfEvenMatcher);
                continue;
            }
            final Matcher jumpIfOneMatcher = JUMP_IF_ONE.matcher(string);
            if (jumpIfOneMatcher.matches()) {
                jumpIfOne(allInstructions, index, jumpIfOneMatcher);
                continue;
            }
            System.out.println(string);
        }
        return new ProcessingModel(Optional.of(allInstructions.get(0)), allInstructions, new Registers());
    }

    private void half(List<Instruction> allInstructions, int instructionIndex, Matcher matcher) {
        final Instruction instruction = new Instruction(instructionIndex, matcher.group(0), (processingModel) -> {
            if (matcher.group(1).equals("a")) {
                processingModel.getRegisters().setA(processingModel.getRegisters().getA() / 2);
            } else {
                processingModel.getRegisters().setB(processingModel.getRegisters().getB() / 2);
            }
            goToNext(processingModel);
        });
        allInstructions.add(instruction);
    }

    private void tripple(List<Instruction> allInstructions, int instructionIndex, Matcher matcher) {
        final Instruction instruction = new Instruction(instructionIndex, matcher.group(0), (processingModel) -> {
            if (matcher.group(1).equals("a")) {
                processingModel.getRegisters().setA(processingModel.getRegisters().getA() * 3);
            } else {
                processingModel.getRegisters().setB(processingModel.getRegisters().getB() * 3);
            }
            goToNext(processingModel);
        });
        allInstructions.add(instruction);
    }

    private void increment(List<Instruction> allInstructions, int instructionIndex, Matcher matcher) {
        final Instruction instruction = new Instruction(instructionIndex, matcher.group(0), (processingModel) -> {
            if (matcher.group(1).equals("a")) {
                processingModel.getRegisters().setA(processingModel.getRegisters().getA() + 1);
            } else {
                processingModel.getRegisters().setB(processingModel.getRegisters().getB() + 1);
            }
            goToNext(processingModel);
        });
        allInstructions.add(instruction);
    }

    private void goToNext(ProcessingModel processingModel) {
        final int index = processingModel.getNextInstruction().get().getIndex();
        if (index < processingModel.getAllInstructions().size() - 1) {
            processingModel.setNextInstruction(Optional.of(processingModel.getAllInstructions().get(index + 1)));
            return;
        }
        processingModel.setNextInstruction(Optional.empty());
    }

    private void jump(List<Instruction> allInstructions, int instructionIndex, Matcher matcher) {
        final Instruction instruction = new Instruction(instructionIndex, matcher.group(0), (processingModel) -> {
            int currentIndex = processingModel.getNextInstruction().get().getIndex();
            final int nextIndex = currentIndex + Integer.parseInt(matcher.group(1));
            if (nextIndex > 0 && nextIndex < processingModel.getAllInstructions().size()) {
                processingModel.setNextInstruction(Optional.of(processingModel.getAllInstructions().get(nextIndex)));
                return;
            }
            processingModel.setNextInstruction(Optional.empty());
        });
        allInstructions.add(instruction);
    }

    private void jumpIfEven(List<Instruction> allInstructions, int instructionIndex, Matcher matcher) {
        final Instruction instruction = new Instruction(instructionIndex, matcher.group(0), (processingModel) -> {
            boolean isEven = false;
            if (matcher.group(1).equals("a")) {
                isEven = processingModel.getRegisters().getA() % 2 == 0;
            } else {
                isEven = processingModel.getRegisters().getB() % 2 == 0;
            }
            if (jumpOnCondition(processingModel, isEven, matcher)) {
                return;
            }
            goToNext(processingModel);
        });
        allInstructions.add(instruction);
    }

    private void jumpIfOne(List<Instruction> allInstructions, int instructionIndex, Matcher matcher) {
        final Instruction instruction = new Instruction(instructionIndex, matcher.group(0), (processingModel) -> {
            boolean isOne = false;
            if (matcher.group(1).equals("a")) {
                isOne = processingModel.getRegisters().getA() == 1;
            } else {
                isOne = processingModel.getRegisters().getB() == 1;
            }
            if (jumpOnCondition(processingModel, isOne, matcher)) {
                return;
            }
            goToNext(processingModel);
        });
        allInstructions.add(instruction);
    }

    private boolean jumpOnCondition(ProcessingModel processingModel, boolean isConditionMet, Matcher matcher) {
        int currentIndex = processingModel.getNextInstruction().get().getIndex();
        if (isConditionMet) {
            final int nextIndex = currentIndex + Integer.parseInt(matcher.group(2));
            if (nextIndex > 0 && nextIndex < processingModel.getAllInstructions().size()) {
                processingModel.setNextInstruction(Optional.of(processingModel.getAllInstructions().get(nextIndex)));
                return true;
            }
            processingModel.setNextInstruction(Optional.empty());
            return true;
        }
        return false;
    }
}
