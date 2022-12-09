package de.barryallenofearth.adventofcode2015.riddle.h;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

public class Main_8_1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        final List<String> strings = RiddleFileReader.readAllLines("riddle-8.txt");
        int totalProgramLength = 0;
        int totalCharLength = 0;
        for (String string : strings) {
            totalProgramLength += string.length();
            string = string.toLowerCase(Locale.ROOT)
                    .replaceAll("^\"", "")
                    .replaceAll("\"$", "")
                    .replace("\\\\", "1")
                    .replace("\\\"", "1")
                    .replaceAll("\\\\x[\\da-f]{2}", "1");
            System.out.println(string + " " + string.length());

            totalCharLength += string.length();

        }
        System.out.println(totalProgramLength - totalCharLength);
    }
}
