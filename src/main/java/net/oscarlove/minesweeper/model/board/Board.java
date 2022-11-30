package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface Board {

    Cell getCell(Position position);

    int getCellValue(Position position);

    Dimension produceDimension();


    /**
     * @throws NullPointerException     If any of the arguments are {@code null}.
     * @throws IllegalArgumentException If {@code cells} is not rectangular.
     */
    static Board create(List<List<Cell>> cells, Collection<Position> minePositions) {
        checkArguments(cells, minePositions);

        return new Board() {
            public Cell getCell(Position position) {
                return cells.get(position.row()).get(position.column());
            }

            public int getCellValue(Position position) {
                if (isAMine(position)) {
                    return -1;
                }

                return (int) minePositions.stream()
                        .filter(minePos -> isAdjacent(minePos, position))
                        .count();
            }

            public Dimension produceDimension() {
                return new Dimension(cells.size(), cells.get(0).size());
            }

            private boolean isAMine(Position position) {
                return minePositions.contains(position);
            }

            private boolean isAdjacent(Position pos1, Position pos2) {
                return isAdjacent(pos1.row(), pos2.row()) && isAdjacent(pos1.column(), pos2.column());
            }

            private boolean isAdjacent(int int1, int int2) {
                return Math.abs(int1 - int2) <= 1;
            }
        };
    }

    /**
     * @throws NullPointerException If {@code boardCellGenerator} is {@code null}.
     */
    static Board create(BoardCellGenerator boardCellGenerator) {
        Objects.requireNonNull(boardCellGenerator, "boardCellGenerator can not be null.");

        return create(boardCellGenerator.get(), boardCellGenerator.getLastGenerationMinedCellPositions());
    }

    private static void checkArguments(List<List<Cell>> cells, Collection<Position> minePositions) {
        Objects.requireNonNull(cells, "cells can not be null.");
        Objects.requireNonNull(minePositions, "minePositions can not be null.");

        if (!isAllRowsSameLength(cells)) {
            throw new IllegalArgumentException("Cell Board is not Rectangular");
        }
    }

    private static boolean isAllRowsSameLength(List<List<Cell>> cells) {
        return cells.stream().allMatch(list -> cells.get(0).size() == list.size());
    }
}
