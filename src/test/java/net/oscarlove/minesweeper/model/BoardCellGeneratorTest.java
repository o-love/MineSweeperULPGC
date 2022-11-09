package net.oscarlove.minesweeper.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BoardCellGeneratorTest {

    final static Dimension TEST_SIZE = new Dimension(2, 2);
    final static int TEST_NUM_MINES = 2;

    BoardCellGenerator testGenerator = BoardCellGenerator.build(TEST_SIZE, TEST_NUM_MINES, () -> baseCellFactory(), () -> minedCellFactory());

    static Cell baseCellFactory() {
        Cell toReturn = Cell.build();
        toReturn.setState(Cell.State.UNTOUCHED);
        return toReturn;
    }

    static Cell minedCellFactory() {
        Cell toReturn = Cell.build();
        toReturn.setState(Cell.State.SELECTED);
        return toReturn;
    }

    static boolean isMined(Cell cell) {
        return cell.getState().equals(Cell.State.SELECTED);
    }

    @Test
    void testReturnsList() {
        assertNotNull(testGenerator.buildBoardCells().getClass());
    }

    @Test
    void testReturnsSublists() {
        for (int i = 0; i < TEST_SIZE.rows(); i++) {
            assertNotNull(testGenerator.buildBoardCells().get(i));
        }
    }

    @Test
    void testAllContainCells() {
        for (int i = 0; i < TEST_SIZE.rows(); i++) {
            for (int j = 0; j < TEST_SIZE.columns(); j++) {
                assertNotNull(testGenerator.buildBoardCells().get(i).get(j));
            }
        }
    }

    @Test
    void testCorrectNumberOfMinedCells() {
        assertEquals(TEST_NUM_MINES, mineCount(testGenerator.buildBoardCells()));
    }

    int mineCount(List<List<Cell>> cellBoard) {
        return cellBoard.stream()
                .mapToInt(cells -> columnMineCount(cells))
                .sum();
    }

    int columnMineCount(List<Cell> cells) {
        return (int) cells.stream()
                .filter(BoardCellGeneratorTest::isMined)
                .count();
    }

    interface BoardCellGenerator {
        List<List<Cell>> buildBoardCells();

        static BoardCellGenerator build(Dimension size, int numberOfMines, Supplier<Cell> baseCellFactory, Supplier<Cell> minedCellFactory) {
            return new BoardCellGenerator() {
                @Override
                public List<List<Cell>> buildBoardCells() {
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
            };
        }
    }
}
