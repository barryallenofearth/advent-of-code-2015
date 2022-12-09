package de.barryallenofearth.adventofcode2015.riddle.i;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_9_1 {

    public static final Pattern DISTANCE_PATTERN = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");

    public static void main(String[] args) throws IOException, URISyntaxException {
        Map<String, Map<String, Integer>> startWithAllDestinations = getStringMapMap();


        int minimumDistance = Integer.MAX_VALUE;

        for (String startingKey : startWithAllDestinations.keySet()) {
            int totalDistance = 0;

            Map<String, Map<String, Integer>> deletableItems = getStringMapMap();
            Map<String, Integer> nextStep = deletableItems.get(startingKey);
            String nextStepKey = startingKey;
            System.out.println(nextStepKey);
            do {
                final Map<String, Integer> start = nextStep;
                if (start.isEmpty()) {
                    break;
                }
                final Optional<Map.Entry<String, Integer>> minimumDestination = start.entrySet().stream()
                        .min(Comparator.comparingInt(Map.Entry::getValue));
//                System.out.println(minimumDestination.get().getKey() + " " + minimumDestination.get().getValue());
                totalDistance += minimumDestination.get().getValue();

                deletableItems.values()
                        .forEach(value -> {
                            value.remove(startingKey);
                            value.remove(minimumDestination.get().getKey());
                        });

                deletableItems.remove(nextStepKey);
                nextStepKey = minimumDestination.get().getKey();
                nextStep = deletableItems.get(nextStepKey);

            } while (!deletableItems.isEmpty());
            if(totalDistance < minimumDistance){
                minimumDistance = totalDistance;
            }
        }
        System.out.println(minimumDistance);
    }

    private static Map<String, Map<String, Integer>> getStringMapMap() throws IOException, URISyntaxException {
        final List<String> strings = RiddleFileReader.readAllLines("riddle-9.txt");
        Map<String, Map<String, Integer>> startWithAllDestinations = new HashMap<>();
        for (String line : strings) {
            final Matcher matcher = DISTANCE_PATTERN.matcher(line);
            if (matcher.matches()) {
                final String start = matcher.group(1);
                final String destination = matcher.group(2);
                final int distance = Integer.parseInt(matcher.group(3));
                startWithAllDestinations.putIfAbsent(start, new HashMap<>());
                startWithAllDestinations.putIfAbsent(destination, new HashMap<>());

                startWithAllDestinations.get(start).put(destination, distance);
                startWithAllDestinations.get(destination).put(start, distance);

            }
        }
        return startWithAllDestinations;
    }
}