package net.oscarlove.minesweeper.model.interfaces;

import net.oscarlove.minesweeper.model.cell.Cell;

public interface MineSweeperBoardInterface {

    Cell getCell(int i, int j);
    int getNumberOfMines();
    int getNumberOfUnflaggedMines();

}
