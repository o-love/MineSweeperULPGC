package net.oscarlove.minesweeper.view;

import net.oscarlove.minesweeper.base.observer.Observable;
import net.oscarlove.minesweeper.model.cell.Cell;

public interface CellDialog extends Observable {
    Cell getCell();
}
