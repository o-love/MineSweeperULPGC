package net.oscarlove.minesweeper.controller;

import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.view.BoardDisplay;
import net.oscarlove.minesweeper.view.CellDialog;

import java.util.Objects;

public class CellController {

    public static CellController create() {
        return new CellController();
    }

    /**
     * Creates a new game controller.
     *
     * @param board        the board to control
     * @param boardDisplay the display to update
     * @throws NullPointerException if {@code board} or {@code boardDisplay} is null
     */
    public static CellController create(Board board, BoardDisplay boardDisplay) {
        validateArguments(board, boardDisplay);

        return new CellController(board, boardDisplay);
    }

    private static void validateArguments(Board board, BoardDisplay boardDisplay) {
        Objects.requireNonNull(board, "board cannot be null");
        Objects.requireNonNull(boardDisplay, "boardDisplay cannot be null");
    }

    private Board board;

    private BoardDisplay boardDisplay;

    private CellController() {
    }

    private CellController(Board board, BoardDisplay boardDisplay) {
        this.board = board;
        this.boardDisplay = boardDisplay;
    }

    public void onCellDialogUpdate(Position position, CellDialog cellDialog) {
        validateArguments(position, cellDialog);

        board.getCell(position).setState(cellDialog.getCellState());

        boardDisplay.refreshCellDisplay(position, board.getCell(position));
    }

    private void validateArguments(Position position, CellDialog cellDialog) {
        Objects.requireNonNull(position, "position can not be null.");
        Objects.requireNonNull(cellDialog, "cellDialog can not be null.");
    }

    public CellController withBoard(Board board) {
        this.board = board;
        return this;
    }

    public CellController withBoardDisplay(BoardDisplay boardDisplay) {
        this.boardDisplay = boardDisplay;
        return this;
    }

}