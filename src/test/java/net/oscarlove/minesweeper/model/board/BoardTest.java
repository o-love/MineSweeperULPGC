package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void returnsBoard() {
        assertNotNull(Board.create(new ArrayList<>(), List.of()));
    }

    @Test
    void singleCelledBoardReturns() {
        Board board = Board.create(List.of(List.of(Cell.create())), List.of());

        assertEquals(Cell.create(), board.getCell(new Position(0, 0)));
    }

    @Test
    void sameSingleCelledBoardReturns() {
        Cell cell = Cell.create();
        cell.setState(Cell.State.FLAGGED);

        Board board = Board.create(List.of(List.of(cell)), List.of());

        assertEquals(cell, board.getCell(new Position(0, 0)));
    }

    @Test
    void same2x2CelledBoardReturns() {
        List<List<Cell>> cells = createCellBoard(new Dimension(2, 2));

        assertAllSame(cells, Board.create(cells, List.of()));
    }

    @Test
    void noMinedCellsReturn0Value() {
        assertEquals(0, Board.create(create2x2(), List.of()).getCellValue(new Position(0, 0)));
    }

    @Test
    void oneAdjacentMinedCell() {
        assertEquals(1, Board.create(create2x2(), List.of(new Position(1, 1))).getCellValue(new Position(0, 0)));
    }

    @Test
    void oneNonAdjacentMineReturns0() {
        assertEquals(0, Board.create(createCellBoard(new Dimension(4, 4)), List.of(new Position(1, 1))).getCellValue(new Position(3, 2)));
    }

    @Test
    void multipleAdjacentReturnsN() {
        assertEquals(3,
                Board.create(
                        createCellBoard(new Dimension(4, 4)),
                        List.of(
                                new Position(1, 1),
                                new Position(1, 0),
                                new Position(0, 1)
                        )
                ).getCellValue(new Position(0, 0)));
    }

    @Test
    void isMinedCellReturnsMinus1() {
        assertEquals(-1, Board.create(create2x2(), List.of(new Position(0, 0))).getCellValue(new Position(0, 0)));
    }


    private void assertAllSame(List<List<Cell>> cells, Board board) {
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(0).size(); j++) {
                assertSame(cells.get(i).get(j), board.getCell(new Position(i, j)));
            }
        }
    }

    private List<List<Cell>> create2x2() {
        return createCellBoard(new Dimension(2, 2));
    }

    private List<List<Cell>> createCellBoard(Dimension dimension) {
        return BoardCellGenerator.create(dimension, 0, pos -> Cell.create(), pos -> Cell.create()).get();
    }


}
