package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;

import java.util.*;

public class Board {

    /**
     * @throws NullPointerException If any of the arguments are {@code null}.
     * @throws IllegalArgumentException If {@code cells} is not rectangular.
     */
    public static Board create(List<List<Cell>> cells, Collection<Position> minePositions) {
        checkArguments(cells, minePositions);

        return new Board(cells, minePositions);
    }

    /**
     * @throws NullPointerException If {@code boardCellGenerator} is {@code null}.
     */
    public static Board create(BoardCellGenerator boardCellGenerator) {
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

    private final List<List<Cell>> cells;
    private final Collection<Position> minePositions;

    private Board(List<List<Cell>> cells, Collection<Position> minePositions) {
        this.cells = copy(cells);
        this.minePositions = copy(minePositions);
    }

    public Cell getCell (Position position) {
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

    private boolean isAMine(Position position) {
        return minePositions.contains(position);
    }

    private boolean isAdjacent(Position pos1, Position pos2){
        return isAdjacent(pos1.row(), pos2.row()) && isAdjacent(pos1.column(), pos2.column());
    }

    private boolean isAdjacent(int int1, int int2) {
        return Math.abs(int1 - int2) <= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board baseBoard = (Board) o;
        return cells.equals(baseBoard.cells) && minePositions.equals(baseBoard.minePositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells, minePositions);
    }

    // Constructor helper functions

    private List<List<Cell>> copy(List<List<Cell>> cells) {
        return new ArrayList<>(
                cells.stream().map(ArrayList::new).toList()
        );
    }

    private Collection<Position> copy(Collection<Position> minePositions) {
        return new HashSet<>(minePositions);
    }
}
