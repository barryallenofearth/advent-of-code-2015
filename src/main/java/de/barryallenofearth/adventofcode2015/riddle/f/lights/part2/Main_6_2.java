package de.barryallenofearth.adventofcode2015.riddle.f.lights.part2;

import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_6_2 {

	public static final int GRID_SIZE = 1000;

	public static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^(.+) (\\d+),(\\d+) through (\\d+),(\\d+)$");

	public static void main(String[] args) throws IOException, URISyntaxException {
		final int[][] lightGrid = initLightGrid();

		List<Command> commands = readCommands();

		executeCommands(lightGrid, commands);

		int lightsOnCount = countLights(lightGrid);
		System.out.println(lightsOnCount + " is the total brightness");
	}

	private static void executeCommands(int[][] lightGrid, List<Command> commands) {
		for (Command command : commands) {
			for (int column = command.getColumnStart(); column <= command.getColumnEnd(); column++) {
				for (int row = command.getRowStart(); row <= command.getRowEnd(); row++) {
					final int newStatus = command.getLightOperation().getSwitchingFunction().apply(lightGrid[column][row]);
					lightGrid[column][row] = newStatus;
				}
			}
		}
	}

	private static List<Command> readCommands() throws IOException, URISyntaxException {
		List<Command> commands = new ArrayList<>();
		final List<String> strings = RiddleFileReader.readAllLines("riddle-6.txt");
		for (String string : strings) {
			final Matcher matcher = INSTRUCTION_PATTERN.matcher(string);
			if (matcher.matches()) {
				final LightOperation lightOperation = LightOperation.getByKey(matcher.group(1));
				final int columnStart = Integer.parseInt(matcher.group(2));
				final int rowStart = Integer.parseInt(matcher.group(3));
				final int columnnEnd = Integer.parseInt(matcher.group(4));
				final int rowEnd = Integer.parseInt(matcher.group(5));

				commands.add(new Command(lightOperation, columnStart, rowStart, columnnEnd, rowEnd));
			}
		}
		return commands;
	}

	private static int countLights(int[][] lightGrid) {
		int lightsOnCount = 0;
		for (int column = 0; column < GRID_SIZE; column++) {
			for (int row = 0; row < GRID_SIZE; row++) {
				lightsOnCount += lightGrid[column][row];
			}
		}
		return lightsOnCount;
	}

	private static int[][] initLightGrid() {
		final int[][] lightGrid = new int[GRID_SIZE][GRID_SIZE];
		for (int column = 0; column < GRID_SIZE; column++) {
			lightGrid[column] = new int[GRID_SIZE];
			for (int row = 0; row < GRID_SIZE; row++) {
				lightGrid[column][row] = 0;
			}
		}
		return lightGrid;
	}
}
