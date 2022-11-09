package net.oscarlove.minesweeper.model;

import static org.junit.jupiter.api.Assertions.*;

import net.oscarlove.minesweeper.model.cell.Cell;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

public class BoardCellGeneratorTest {

    BoardCellGenerator testGenerator = buildBoardCellGenerator(TEST_SIZE, TEST_NUM_MINES, () -> baseCellFactory(), () -> minedCellFactory());

    final static Dimension TEST_SIZE = new Dimension(2, 2);
    final static int TEST_NUM_MINES = 2;

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
        assertNotNull(testGenerator.get().getClass());
    }

    @Test
    void testReturnsSublists() {
        for (int i = 0; i < TEST_SIZE.rows(); i++) {
            assertNotNull(testGenerator.get().get(i));
        }
    }

    @Test
    void testAllContainCells() {
        for (int i = 0; i < TEST_SIZE.rows(); i++) {
            for (int j = 0; j < TEST_SIZE.columns(); j++) {
                assertNotNull(testGenerator.get().get(i).get(j));
            }
        }
    }

    @Test
    void testCorrectNumberOfMinedCells() {
        assertEquals(TEST_NUM_MINES, mineCount(testGenerator.get()));
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

    @Test
    void testThrowsOnNulls() {
        assertThrows(NullPointerException.class,
                () -> buildBoardCellGenerator(null, TEST_NUM_MINES, () -> baseCellFactory(), () -> minedCellFactory())
        );
        assertThrows(NullPointerException.class,
                () -> buildBoardCellGenerator(TEST_SIZE, TEST_NUM_MINES, null, () -> minedCellFactory())
        );
        assertThrows(NullPointerException.class,
                () -> buildBoardCellGenerator(TEST_SIZE, TEST_NUM_MINES, () -> baseCellFactory(), null)
        );
    }

    @Test
    void testThrowsOnNegativeNumberOfMines() {
        assertThrows(IllegalArgumentException.class,
                () -> buildBoardCellGenerator(TEST_SIZE, -2, () -> baseCellFactory(), () -> minedCellFactory())
        );
    }

    BoardCellGenerator buildBoardCellGenerator(Dimension size, int numberOfMines, Supplier<Cell> baseCellFactory, Supplier<Cell> minedCellFactory) {
        return BoardCellGenerator.build(size, numberOfMines, baseCellFactory, minedCellFactory);
    }


}
