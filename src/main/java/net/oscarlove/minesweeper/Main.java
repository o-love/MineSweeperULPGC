package net.oscarlove.minesweeper;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.model.board.BoardCellGenerator;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.cell.ObservableCell;

public class Main {
    private static final Dimension BOARD_SIZE = new Dimension(10, 10);
    private static final int NUMBER_OF_MINES = 10;

    public static void main(String[] args) {
        Board board = Board.create(BoardCellGenerator.create(BOARD_SIZE, NUMBER_OF_MINES, Cell::create, Main::minedCellFactory));


    }

    private static Cell minedCellFactory() {
        ObservableCell observableCell = ObservableCell.create(Cell.create());
        observableCell.addObserver(Main::onGameEnd);
        return observableCell;
    }

    private static void onGameEnd() {
        System.out.println("Game Over");
        System.exit(0);
    }
}
