package net.oscarlove.minesweeper.view.cli;

import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.view.BoardDisplay;

public class BoardCLIDisplay implements BoardDisplay {

    private final Board board;

    public BoardCLIDisplay(Board board) {
        this.board = board;
    }

    @Override
    public void refreshCellDisplay(Position position, Cell cell) {

    }
}
