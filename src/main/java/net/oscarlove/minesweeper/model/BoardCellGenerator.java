package net.oscarlove.minesweeper.model;

import net.oscarlove.minesweeper.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class BoardCellGenerator implements Supplier<List<List<Cell>>> {

    public static BoardCellGenerator build(Dimension size, int numberOfMines, Supplier<Cell> baseCellFactory, Supplier<Cell> minedCellFactory) {
        return new BoardCellGenerator(size, numberOfMines, baseCellFactory, minedCellFactory);
    }

    private final Dimension size;
    private final int numberOfMines;
    private final Supplier<Cell> baseCellFactory;
    private final Supplier<Cell> minedCellFactory;

    private BoardCellGenerator(Dimension size, int numberOfMines, Supplier<Cell> baseCellFactory, Supplier<Cell> minedCellFactory) {
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
        forEachCellPosSet(cellBoard, new Supplier<>() {
            int minesLeftToAdd = numberOfMines;

            @Override
            public Cell get() {
                if (minesLeftToAdd > 0) {
                    minesLeftToAdd--;
                    return minedCellFactory.get();
                }

                return baseCellFactory.get();
            }
        });
    }

    private void forEachCellPosSet(List<List<Cell>> cellBoard, Supplier<Cell> setterForEachSupplier) {
        for (int i = 0; i < size.rows(); i++) {
            for (int j = 0; j < size.columns(); j++) {
                cellBoard.get(i).add(j, setterForEachSupplier.get());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardCellGenerator that = (BoardCellGenerator) o;
        return numberOfMines == that.numberOfMines && size.equals(that.size) && baseCellFactory.equals(that.baseCellFactory) && minedCellFactory.equals(that.minedCellFactory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, numberOfMines, baseCellFactory, minedCellFactory);
    }
}