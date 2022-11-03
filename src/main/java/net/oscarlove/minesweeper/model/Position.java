package net.oscarlove.minesweeper.model;

public record Position(int row, int column) {

    /**
     *
     * @param row Row position
     * @param column Column position
     *
     * @throws IllegalArgumentException if the {@code row} or {@code column} are negative numbers
     */
    public Position {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("row and column must be positive numbers");
        }
    }
}
