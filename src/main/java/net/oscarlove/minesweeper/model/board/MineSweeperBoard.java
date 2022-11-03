package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.cell.MinedCell;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperBoard {

    private final List<List<Cell>> cells;
    private final int numberOfMines;

    /**
     *
     * @param cells A 2D list containing all the {@code Cell}s that will form the board.
     *
     * @throws IllegalArgumentException If board is not rectangular.
     * @throws IllegalArgumentException If board doesn't have rows or doesn't have {@code Cell}s in a row.
     */
    public MineSweeperBoard(List<List<Cell>> cells) {
        checkValid(cells);

        this.cells = new ArrayList<>(cells);
        this.numberOfMines = (int) calculateNumberOfMinesInBoard();
    }

    /**
     * Get the {@code Cell} at the specified row and column
     *
     * @param i row, zero counted
     * @param j column, zero counted
     * @return The cell found in that row and column.
     *
     * @throws IndexOutOfBoundsException When the index of either parameter exceeds the board's length.
     */
    public Cell getCell(int i, int j) {
        return this.cells.get(i).get(j);
    }

    /**
     *
     * @return The number of {@code MinedCell}'s in the board.
     */
    public int getNumberOfMines() {
        return numberOfMines;
    }

    /**
     *
     * @return The number of rows on the board.
     */
    public int getHeight(){
        return this.cells.size();
    }

    /**
     *
     * @return The number of columns on the board.
     */
    public int getWidth() {
        return this.cells.get(0).size();
    }

    /**
     *
     * @return number of {@code Cells} that have been flagged.
     */
    public int numberOfFlaggedCells() {
        return (int) this.cells.stream().mapToLong(cellList -> {
            return cellList.stream()
                    .filter(cell -> cell.getState().equals(Cell.State.FLAGGED))
                    .count();
        }).sum();
    }

    private void checkValid(List<List<Cell>> cells){
        checkBoardIsNotEmpty(cells);
        checkRectangular(cells);
    }

    private void checkRectangular(List<List<Cell>> cells) {
        // Check that the board is rectangular
        int size = cells.get(0).size();
        for (List<Cell> cellList : cells) {
            if (cellList.size() != size) {
                throw new IllegalArgumentException("MineSweeperBoard is not Rectangular");
            }
        }
    }

    private void checkBoardIsNotEmpty(List<List<Cell>> cells) {
        if (cells.size() == 0 || cells.get(0).size() == 0) {
            throw new IllegalArgumentException("Board is of size 0");
        }
    }

    private long calculateNumberOfMinesInBoard() {
        return this.cells.stream().mapToLong(cellList -> {
            return cellList.stream()
                    .filter(cell -> cell instanceof MinedCell)
                    .count();
        }).sum();
    }
}
