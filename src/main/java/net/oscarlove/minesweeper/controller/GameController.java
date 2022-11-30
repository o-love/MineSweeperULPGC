package net.oscarlove.minesweeper.controller;

import net.oscarlove.minesweeper.view.swing.GUISwing;
import net.oscarlove.minesweeper.view.swing.GameOverPopup;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameController {

    public static GameController create(CellController cellController) {
        Objects.requireNonNull(cellController);

        return new GameController(cellController);
    }

    private final CellController cellController;

    private GameController(CellController cellController) {
        this.cellController = cellController;
    }

    public void onGameOver() {
        cellController.blockUpdates();
        SwingUtilities.invokeLater(() -> new GUISwing(new GameOverPopup(), "GAME OVER", new Dimension(100, 100)));
    }
}
