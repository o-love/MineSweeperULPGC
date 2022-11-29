package net.oscarlove.minesweeper.view.swing;

import net.oscarlove.minesweeper.base.observer.Observer;
import net.oscarlove.minesweeper.base.observer.OpenObservable;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.view.CellDialog;
import net.oscarlove.minesweeper.view.CellDisplay;

import javax.swing.*;

public class CellSwing extends JPanel implements CellDisplay, CellDialog {

    private final OpenObservable openObservable = OpenObservable.create();
    private JLabel label;
    private Cell.State state;
    private final int value;

    public CellSwing(Cell cell, int value) {
        setupGUI();

        this.value = value;

        refreshCellDisplay(cell);
    }

    @Override
    public void refreshCellDisplay(Cell cell) {
        this.state = cell.getState();

        switch (state) {
            case UNTOUCHED -> label.setText("X");
            case FLAGGED -> label.setText("F");
            case SELECTED -> label.setText(value == 0 ? " " : String.valueOf(value));
        }
    }

    private void setupGUI() {
        setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        setPreferredSize(new java.awt.Dimension(20, 20));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                state = Cell.State.SELECTED;
                openObservable.updateObservers();
            }
        });

        label = new JLabel();
        this.add(label);
    }


    @Override
    public void addObserver(Observer observer) {
        openObservable.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        openObservable.removeObserver(observer);
    }

    @Override
    public Cell.State getCellState() {
        return this.state;
    }
}
