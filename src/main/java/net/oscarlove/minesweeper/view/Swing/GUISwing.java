package net.oscarlove.minesweeper.view.Swing;

import javax.swing.*;
import java.awt.*;

public class GUISwing extends JFrame {


    public GUISwing(JPanel jPanel, String string, Dimension dimension) {
        this(jPanel, string, dimension, JFrame.EXIT_ON_CLOSE);
    }

    public GUISwing(JPanel jPanel, String string, Dimension dimension, int defaultCloseOperation) {
        super(string);

        getContentPane().add(jPanel);

        setSize(dimension.width, dimension.height);

        this.setDefaultCloseOperation(defaultCloseOperation);
        this.setVisible(true);
    }
}