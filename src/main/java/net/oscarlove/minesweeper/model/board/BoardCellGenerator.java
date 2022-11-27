package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public interface BoardCellGenerator extends Supplier<List<List<Cell>>> {

    static BoardCellGenerator create(Dimension size, int numberOfMines, Supplier<Cell> baseCellFactory, Supplier<Cell> minedCellFactory) {
        return new nonRandomBoardCellGenerator(size, numberOfMines, baseCellFactory, minedCellFactory);
    }

    Collection<Position> getLastGenerationMinedCellPositions();
}