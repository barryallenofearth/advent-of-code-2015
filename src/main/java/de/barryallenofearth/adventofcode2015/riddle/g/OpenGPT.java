package de.barryallenofearth.adventofcode2015.riddle.g;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenGPT {

    // Parse the input and return a map from wire identifier to signal
    public static Map<String, Integer> parseInput(List<String> input) {
        // Initialize the map of wire signals
        Map<String, Integer> signals = new HashMap<>();


        // Split the input into individual instructions
        String[] instructions = input.toArray(new String[0]);


        // Process each instruction in turn
        for (String instruction : instructions) {
            // Split the instruction into its components
            String[] parts = instruction.split(" ");


            // If the instruction is of the form "123 -> x", set the signal on wire x to 123
            if (parts.length == 3 && parts[1].equals("->")) {
                signals.put(parts[2], Integer.parseInt(parts[0]));
            }


            // If the instruction is of the form "x AND y -> z", set the signal on wire z
            // to the bitwise AND of the signals on wires x and y
            else if (parts.length == 5 && parts[1].equals("AND")) {
                signals.put(parts[4], signals.get(parts[0]) & signals.get(parts[2]));
            }


            // If the instruction is of the form "x OR y -> z", set the signal on wire z
            // to the bitwise OR of the signals on wires x and y
            else if (parts.length == 4 && parts[1].equals("OR")) {
                signals.put(parts[3], signals.get(parts[0]) | signals.get(parts[2]));
            }


            // If the instruction is of the form "x LSHIFT 2 -> y", set the signal on wire y
            // to the value from wire x left-shifted by 2
            else if (parts.length == 5 && parts[1].equals("LSHIFT")) {
                signals.put(parts[4], signals.get(parts[0]) << Integer.parseInt(parts[2]));
            }


            // If the instruction is of the form "x RSHIFT 2 -> y", set the signal on wire y
            // to the value from wire x right-shifted by 2
            else if (parts.length == 5 && parts[1].equals("RSHIFT")) {
                signals.put(parts[4], signals.get(parts[0]) >> Integer.parseInt(parts[2]));
            }


            // If the instruction is of the form "NOT x -> y", set the signal on wire y
            // to the bitwise complement of the value from wire x
            else if (parts.length == 4 && parts[0].equals("NOT")) {
                signals.put(parts[3], ~signals.get(parts[1]));
            }
        }


        // Return the map of wire signals
        return signals;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        // Parse the input
        Map<String, Integer> signals = parseInput(RiddleFileReader.readAllLines("riddle-7.txt"));


        // Print the signal on wire a
        System.out.println(signals.get("a"));
    }
}
