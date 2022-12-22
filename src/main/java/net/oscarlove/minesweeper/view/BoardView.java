package net.oscarlove.minesweeper.view;

import net.oscarlove.minesweeper.base.datatypes.Position;

public interface BoardView {
    CellDialog getCellDialog(int row, int column);

    default CellDialog getCellDialog(Position position) {
        return getCellDialog(position.row(), position.column());
    }
}
