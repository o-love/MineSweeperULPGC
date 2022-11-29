package net.oscarlove.minesweeper.view.swing;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUISwing extends JFrame {
    private static class WindowCloseManager extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
        }
    }

    public GUISwing(JPanel jPanel, String string) {
        super(string);

        getContentPane().add(jPanel);

        setSize(600, 300);

        this.addWindowListener(new WindowCloseManager());
        setVisible(true);
    }
}
