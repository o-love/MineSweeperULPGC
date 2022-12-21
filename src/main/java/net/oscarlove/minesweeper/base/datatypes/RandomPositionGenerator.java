package net.oscarlove.minesweeper.base.datatypes;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPositionGenerator {
    private final Dimension dimension;
    private int minimum;
    private int maximum;

    public static RandomPositionGenerator create(Dimension dimension) {
        Objects.requireNonNull(dimension);

        return new RandomPositionGenerator(dimension);
    }

    private RandomPositionGenerator(Dimension dimension) {
        this.dimension = dimension;
        this.minimum = 0;
        this.maximum = dimension.rows() * dimension.columns() / 2 + 1;
    }

    public RandomPositionGenerator withMinimum(int minimum) {
        this.minimum = minimum;

        return this;
    }

    public RandomPositionGenerator withMaximum(int maximum) {
        this.maximum = maximum;

        return this;
    }

    public Set<Position> generate() {
        int numberOfPositionsToGenerate = ThreadLocalRandom.current().nextInt(minimum, maximum);
        Set<Position> toReturn = new HashSet<>();

        for (int i = 0; i <= numberOfPositionsToGenerate; i++) {
            while (!toReturn.add(randomPosition())) {
            }
        }

        return toReturn;
    }

    private Position randomPosition() {
        return new Position(
                ThreadLocalRandom.current().nextInt(0, this.dimension.rows()),
                ThreadLocalRandom.current().nextInt(0, this.dimension.columns())
        );
    }
}

