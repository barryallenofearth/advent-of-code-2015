package de.barryallenofearth.adventofcode2015.riddle.day25.weathermachine.common.model;

import lombok.Data;

@Data
public class GridAndTarget {
    private final long[][] grid;

    private final int targetX;

    private final int targetY;
}
