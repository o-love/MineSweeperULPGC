package net.oscarlove.minesweeper.model;

public record Dimension(int rows, int columns) {

    /**
     *
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is negative.
     */
    public Dimension {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("rows or columns of Dimension can't be less than 0");
        }
    }
}
