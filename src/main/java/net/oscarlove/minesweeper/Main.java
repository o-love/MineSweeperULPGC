package net.oscarlove.minesweeper;

import net.oscarlove.minesweeper.base.datatypes.Dimension;
import net.oscarlove.minesweeper.base.datatypes.RandomPositionGenerator;
import net.oscarlove.minesweeper.model.board.Board;

public class Main {

    public static void main(String[] args) {

    }

    private Board board;

    public Main() {
        buildModel();

        buildView();

        buildPresenter();

        connectAndDisplay();
    }

    private void buildModel() {
        this.board = Board.create(
                new Dimension(10, 10),
                RandomPositionGenerator.create(new Dimension(10, 10))
                        .withMinimum(10)
                        .withMaximum(60)
                        .generate()
        );
    }

    private void buildView() {

    }

    private void buildPresenter() {

    }

    private void connectAndDisplay() {

    }


}
