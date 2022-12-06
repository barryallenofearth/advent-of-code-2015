package de.barryallenofearth.adventofcode2015.riddle.b.common;

import de.barryallenofearth.adventofcode2015.riddle.b.model.Box;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RectangleCalculator {

	public static Box calculateBox(int length, int width, int height) {
		List<Integer> boxAreaDimension = new ArrayList<>();
		boxAreaDimension.add(length * width);
		boxAreaDimension.add(length * height);
		boxAreaDimension.add(width * height);

		boxAreaDimension.sort(Comparator.comparingInt(area -> area));

		return new Box(boxAreaDimension.get(0), boxAreaDimension.get(1), boxAreaDimension.get(2));
	}
}
