package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;

import java.util.Collection;
import java.util.List;

public interface Board {
    Cell getCell(Position position);
    int getCellValue(Position position);

    /**
     *
     * @throws NullPointerException If any of the arguments are {@code null}.
     * @throws IllegalArgumentException If {@code cells} is not rectangular.
     */
    static Board build(List<List<Cell>> cells, Collection<Position> minePositions) {
        return new BaseBoard(cells, minePositions);
    }
}