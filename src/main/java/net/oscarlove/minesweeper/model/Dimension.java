package net.oscarlove.minesweeper.model;

import java.util.Iterator;

public record Dimension(int rows, int columns) {

    /**
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is negative.
     */
    public Dimension {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("rows or columns of Dimension can't be less than 0");
        }
    }


    public Iterable<Position> positionsUnderneath() {
        return this::positionIterator;
    }

    private Iterator<Position> positionIterator() {
        return new Iterator<>() {
            private int row = 0;
            private int column = 0;

            @Override
            public boolean hasNext() {
                return row < rows() && column < columns();
            }

            @Override
            public Position next() {
                Position position = new Position(row, column);
                column++;
                if (column == columns()) {
                    column = 0;
                    row++;
                }
                return position;
            }
        };
    }
}
