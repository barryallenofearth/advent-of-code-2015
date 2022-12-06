package de.barryallenofearth.adventofcode2015.riddle.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RiddleFileReader {

	public static List<String> readAllLines(String fileName) throws IOException, URISyntaxException {
		return FileUtils.readLines(new File(RiddleFileReader.class.getResource("/" + fileName).toURI()), StandardCharsets.UTF_8);
	}
}
