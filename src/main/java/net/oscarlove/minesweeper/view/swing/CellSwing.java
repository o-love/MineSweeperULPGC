package net.oscarlove.minesweeper.view.swing;

import net.oscarlove.minesweeper.base.observer.Observer;
import net.oscarlove.minesweeper.base.observer.OpenObservable;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.view.CellDialog;
import net.oscarlove.minesweeper.view.CellDisplay;

public class CellSwing implements CellDisplay, CellDialog {

    private final OpenObservable openObservable = OpenObservable.create();
    Cell.State state;

    public CellSwing(Cell cell) {
        setupGUI();

        refreshCellDisplay(cell);
    }

    @Override
    public void refreshCellDisplay(Cell cell) {
        this.state = cell.getState();
    }

    private void setupGUI() {

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
    public Cell getCell() {
        return Cell.of(this.state);
    }
}
