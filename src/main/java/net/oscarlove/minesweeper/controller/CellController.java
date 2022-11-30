package net.oscarlove.minesweeper.controller;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.view.BoardDisplay;
import net.oscarlove.minesweeper.view.CellDialog;

import java.util.Objects;

public class CellController {

    public static CellController create() {
        return new CellController();
    }

    private Board board;

    private BoardDisplay boardDisplay;

    private CellController() {
    }

    public void onCellDialogUpdate(Position position, CellDialog cellDialog) {
        validateArguments(position, cellDialog);

        board.getCell(position).setState(cellDialog.getCellState());
    }

    public void onCellModelUpdate(Position position, Cell cell) {
        boardDisplay.refreshCellDisplay(position, cell);
    }

    private void validateArguments(Position position, CellDialog cellDialog) {
        Objects.requireNonNull(position, "position can not be null.");
        Objects.requireNonNull(cellDialog, "cellDialog can not be null.");
    }

    public CellController withBoard(Board board) {
        Objects.requireNonNull(board);
        this.board = board;
        return this;
    }

    public CellController withBoardDisplay(BoardDisplay boardDisplay) {
        Objects.requireNonNull(boardDisplay);
        this.boardDisplay = boardDisplay;
        return this;
    }

    public void blockUpdates() {
        this.board = new Board() {
            final Cell cell = Cell.create();

            @Override
            public Cell getCell(Position position) {
                return cell;
            }

            @Override
            public int getCellValue(Position position) {
                return 0;
            }

            @Override
            public Dimension produceDimension() {
                return null;
            }
        };
    }

}