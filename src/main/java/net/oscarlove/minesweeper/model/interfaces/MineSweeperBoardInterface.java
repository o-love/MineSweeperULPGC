package net.oscarlove.minesweeper.model.interfaces;

import net.oscarlove.minesweeper.model.cell.Cell;

public interface MineSweeperBoardInterface {

    /**
     *
     *
     *
     * @param i row, zero counted
     * @param j column, zero counted
     * @return The cell found in that row and column.
     *
     * @throws IndexOutOfBoundsException When the index of either parameter exceeds the board's length.
     */
    Cell getCell(int i, int j);
    int getNumberOfMines();
    int getNumberOfUnflaggedMines();

}
