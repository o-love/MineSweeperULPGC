package net.oscarlove.minesweeper.model.interfaces;

import net.oscarlove.minesweeper.model.cell.Cell;

public interface MineSweeperBoardBuilder {
    void addCell(int i, int j, Cell cell);
    MineSweeperBoardInterface build();
}
