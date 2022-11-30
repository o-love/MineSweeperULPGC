package net.oscarlove.minesweeper;

import net.oscarlove.minesweeper.controller.CellController;
import net.oscarlove.minesweeper.controller.GameController;
import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.model.board.BoardCellGenerator;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.cell.ObservableCell;
import net.oscarlove.minesweeper.view.swing.BoardSwingDisplay;
import net.oscarlove.minesweeper.view.swing.GUISwing;

import javax.swing.*;

public class Main {
    private static final Dimension BOARD_SIZE = new Dimension(10, 10);
    private static final int NUMBER_OF_MINES = 10;

    public static void main(String[] args) {
        new Main();
    }

    private CellController cellController;
    private GameController gameController;
    private Board board;

    public Main() {
        setupControllers();
        setupModel();
        setupGUI();
    }

    private void setupControllers() {
        this.cellController = CellController.create();
        this.gameController = GameController.create(cellController);
    }

    private void setupModel() {
        this.board = Board.create(BoardCellGenerator.create(
                BOARD_SIZE,
                NUMBER_OF_MINES,
                this::normalCellFactory,
                this::minedCellFactory
        ));
        cellController.withBoard(this.board);
    }

    private void setupGUI() {
        SwingUtilities.invokeLater(() -> {
            BoardSwingDisplay boardDisplay = new BoardSwingDisplay.Builder()
                    .of(board)
                    .withPositionListener(cellController::onCellDialogUpdate)
                    .build();

            cellController.withBoardDisplay(boardDisplay);

            new GUISwing(boardDisplay, "Minesweeper", new java.awt.Dimension(600, 600));
        });
    }

    private ObservableCell normalCellFactory(Position position) {
        ObservableCell observableCell = ObservableCell.create(Cell.create());
        observableCell.addObserver(() -> cellController.onCellModelUpdate(position, observableCell));
        return observableCell;
    }

    private ObservableCell minedCellFactory(Position position) {
        ObservableCell observableCell = normalCellFactory(position);
        observableCell.addObserver(gameController::onGameOver);
        return observableCell;
    }
}
