package de.barryallenofearth.adventofcode2015.riddle.day17.common.uitl;

import de.barryallenofearth.adventofcode2015.riddle.day17.common.model.Container;
import de.barryallenofearth.adventofcode2015.riddle.util.RiddleFileReader;

import java.util.ArrayList;
import java.util.List;

public class ContainerReader {

	public List<Container> read() {
		final List<String> lines = RiddleFileReader.readAllLines("riddle-17.txt");

		final List<Container> containerList = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			containerList.add(new Container(i, Integer.parseInt(line)));
		}
		return containerList;
	}
}
