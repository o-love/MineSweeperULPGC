package net.oscarlove.minesweeper.controller;

import net.oscarlove.minesweeper.view.swing.GUISwing;
import net.oscarlove.minesweeper.view.swing.GameOverPopup;

import javax.swing.*;
import java.awt.*;

public class GameController {
    public static void onGameOver() {
        SwingUtilities.invokeLater(() -> new GUISwing(new GameOverPopup(), "GAME OVER", new Dimension(100, 100)));
    }
}
