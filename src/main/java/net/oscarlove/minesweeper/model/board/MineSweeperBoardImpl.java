package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.interfaces.MineSweeperBoardInterface;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperBoardImpl implements MineSweeperBoardInterface {

    List<List<Cell>> cells;

    protected MineSweeperBoardImpl(List<List<Cell>> cells) {
        this.cells = new ArrayList<>(cells);
    }

    @Override
    public Cell getCell(int i, int j) {
        return null;
    }

    @Override
    public int getNumberOfMines() {
        return 0;
    }

    @Override
    public int getNumberOfUnflaggedMines() {
        return 0;
    }
}
