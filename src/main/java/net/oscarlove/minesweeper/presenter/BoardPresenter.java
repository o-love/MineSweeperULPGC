package net.oscarlove.minesweeper.presenter;

import net.oscarlove.minesweeper.base.datatypes.Position;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.view.BoardView;
import net.oscarlove.minesweeper.view.CellDialog;

public class BoardPresenter {

    public static BoardPresenter of(Board board, BoardView boardView) {
        return new BoardPresenter(board, boardView);
    }

    private final Board board;
    private final BoardView boardView;

    public BoardPresenter(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    private void addEvents() {
        board.setCellStateListener(this::onPositionUpdate);

    }

    private void onRightClick(Position position) {

    }

    private void onLiftClick(Position position) {

    }

    private void onPositionUpdate(int row, int column) {
        modelToView(board.getCell(row, column), boardView.getCellDialog(row, column));
    }

    private void modelToView(Board.Cell cell, CellDialog cellDialog) {
        switch (cell.state()) {
            case OPEN -> cellDialog.refreshDisplay(CellDialog.Display.CLEAR);
            case CLOSED -> {
                if (cell.value() == 0) cellDialog.refreshDisplay(CellDialog.Display.CLEAR);
                else cellDialog.refreshDisplay(CellDialog.Display.VISIBLE);
            }
            case FLAGGED -> cellDialog.refreshDisplay(CellDialog.Display.FLAG);
        }
    }
}
