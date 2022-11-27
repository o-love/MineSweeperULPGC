package net.oscarlove.minesweeper.view;

import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;

public interface BoardDisplay {

    void refreshCellDisplay(Position position, Cell cell);
}
