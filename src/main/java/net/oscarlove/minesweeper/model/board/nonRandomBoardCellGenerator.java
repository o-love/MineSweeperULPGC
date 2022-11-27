package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

class nonRandomBoardCellGenerator implements BoardCellGenerator {


    private final Dimension size;
    private final int numberOfMines;
    private final Supplier<Cell> baseCellFactory;
    private final Supplier<Cell> minedCellFactory;
    private Collection<Position> minedCellPosition = Collections.emptyList();

    nonRandomBoardCellGenerator(Dimension size, int numberOfMines, Supplier<Cell> baseCellFactory, Supplier<Cell> minedCellFactory) {
        this.size = size;
        this.numberOfMines = numberOfMines;
        this.baseCellFactory = baseCellFactory;
        this.minedCellFactory = minedCellFactory;

        checkArgumentsValid();
    }

    private void checkArgumentsValid() {
        if (numberOfMines < 0) {
            throw new IllegalArgumentException("Number of mines can not be less than 0.");
        }

        Objects.requireNonNull(baseCellFactory, "baseCellFactory for BoardCellGenerator con not be null.");
        Objects.requireNonNull(minedCellFactory, "minedCellFactory for BoardCellGenerator con not be null.");
        Objects.requireNonNull(size, "size for BoardCellGenerator con not be null.");
    }

    @Override
    public List<List<Cell>> get() {
        this.minedCellPosition = new HashSet<>();
        List<List<Cell>> toReturn = buildBaseList();

        addCells(toReturn);

        return toReturn;
    }

    private List<List<Cell>> buildBaseList() {
        List<List<Cell>> toReturn = new ArrayList<>(size.rows());

        for (int i = 0; i < size.rows(); i++) {
            toReturn.add(new ArrayList<>(size.columns()));
        }

        return toReturn;
    }

    private void addCells(List<List<Cell>> cellBoard) {
        forEachCellPosSet(cellBoard, createCellBasedOnPositionFactory());
    }

    private void forEachCellPosSet(List<List<Cell>> cellBoard, Function<Position, Cell> setterForEachSupplier) {
        for (int i = 0; i < size.rows(); i++) {
            for (int j = 0; j < size.columns(); j++) {
                cellBoard.get(i).add(j, setterForEachSupplier.apply(new Position(i, j)));
            }
        }
    }

    private Function<Position, Cell> createCellBasedOnPositionFactory() {
        return new Function<>() {
            int minesLeftToAdd = numberOfMines;

            @Override
            public Cell apply(Position position) {
                if (minesLeftToAdd > 0) {
                    minesLeftToAdd--;
                    minedCellPosition.add(position);
                    return minedCellFactory.get();
                }

                return baseCellFactory.get();
            }
        };
    }

    @Override
    public Collection<Position> getLastGenerationMinedCellPositions() {
        return Collections.unmodifiableCollection(this.minedCellPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        nonRandomBoardCellGenerator that = (nonRandomBoardCellGenerator) o;
        return numberOfMines == that.numberOfMines && size.equals(that.size) && baseCellFactory.equals(that.baseCellFactory) && minedCellFactory.equals(that.minedCellFactory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, numberOfMines, baseCellFactory, minedCellFactory);
    }
}
