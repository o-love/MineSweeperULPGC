package net.oscarlove.minesweeper.view.Swing;

import net.oscarlove.minesweeper.base.events.NotifyEvent;
import net.oscarlove.minesweeper.view.CellDialog;

import javax.swing.*;

public class CellSwing extends JPanel implements CellDialog {

    public static CellDialog create(int value) {
        return new CellSwing(value);
    }

    private final int value;
    NotifyEvent onRightClick = NotifyEvent.NULL;
    NotifyEvent onLeftClick = NotifyEvent.NULL;

    private CellSwing(int value) {
        this.value = value;

        setupGUI();
        refreshDisplay(Display.CLEAR);
    }

    private void setupGUI() {
        setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        setPreferredSize(new java.awt.Dimension(20, 20));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    onRightClick.handle();
                } else if (SwingUtilities.isLeftMouseButton(evt)) {
                    onLeftClick.handle();
                }
            }
        });
    }

    @Override
    public void refreshDisplay(Display display) {
        this.removeAll();

        switch (display) {
            case VISIBLE -> displayValue();
            case FLAG -> displayFlag();
            case CLEAR -> displayClear();
        }

        this.revalidate();
        this.repaint();
    }

    private void displayClear() {

    }

    private void displayValue() {
        if (value == -1) {
            displayMine();
            return;
        }

        this.add(new JLabel(Integer.toString(value)));
    }

    private void displayMine() {
        this.add(new JLabel("O"));
    }

    private void displayFlag() {
        this.add(new JLabel("F"));
    }

    @Override
    public CellDialog onMainClick(NotifyEvent event) {
        onLeftClick = event;
        return this;
    }

    @Override
    public CellDialog onSecondaryClick(NotifyEvent event) {
        onRightClick = event;
        return this;
    }
}
