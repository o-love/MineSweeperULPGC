package net.oscarlove.minesweeper.model;

import net.oscarlove.minesweeper.model.board.BoardCellGenerator;
import net.oscarlove.minesweeper.model.cell.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    @Test
    void returnsBoard() {
        assertNotNull(Board.build(new ArrayList<>(), List.of()));
    }

    @Test
    void singleCelledBoardReturns() {
        Board board = Board.build(List.of(List.of(Cell.build())), List.of());

        assertEquals(Cell.build(), board.getCell(new Position(0,0)));
    }

    @Test
    void sameSingleCelledBoardReturns() {
        Cell cell = Cell.build();
        cell.setState(Cell.State.FLAGGED);

        Board board = Board.build(List.of(List.of(cell)), List.of());

        assertEquals(cell, board.getCell(new Position(0,0)));
    }

    @Test
    void same2x2CelledBoardReturns() {
        List<List<Cell>> cells = createCellBoard(new Dimension(2, 2));

        assertAllSame(cells, Board.build(cells, List.of()));
    }

    @Test
    void noMinedCellsReturn0Value() {
        assertEquals(0, Board.build(create2x2(), List.of()).getCellValue(new Position(0,0)));
    }

    @Test
    void oneAdjacentMinedCell() {
        assertEquals(1, Board.build(create2x2(), List.of(new Position(1,1))).getCellValue(new Position(0,0)));
    }

    @Test
    void oneNonAdjacentMineReturns0() {
        assertEquals(0, Board.build(createCellBoard(new Dimension(4, 4)), List.of(new Position(1,1))).getCellValue(new Position(3,2)));
    }

    @Test
    void multipleAdjacentReturnsN() {
        assertEquals(3,
                Board.build(
                        createCellBoard(new Dimension(4, 4)),
                        List.of(
                                new Position(1,1),
                                new Position(1, 0),
                                new Position(0, 1)
                        )
                ).getCellValue(new Position(0,0)));
    }

    @Test
    void isMinedCellReturnsMinus1() {
        assertEquals(-1, Board.build(create2x2(), List.of(new Position(0,0))).getCellValue(new Position(0,0)));
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
        return BoardCellGenerator.create(dimension, 0, Cell::build, Cell::build).get();
    }


    interface Board {
        Cell getCell(Position position);
        int getCellValue(Position position);

        static Board build(List<List<Cell>> cells, List<Position> minePositions) {

            return new Board() {
                @Override
                public Cell getCell (Position position) {
                    return cells.get(position.row()).get(position.column());
                }

                @Override
                public int getCellValue(Position position) {
                    if (isAMine(position)) {
                        return -1;
                    }

                    return (int) minePositions.stream()
                            .filter(minePos -> isAdjacent(minePos, position))
                            .count();
                }

                private boolean isAMine(Position position) {
                    return minePositions.contains(position);
                }

                private boolean isAdjacent(Position pos1, Position pos2){
                    return isAdjacent(pos1.row(), pos2.row()) && isAdjacent(pos1.column(), pos2.column());
                }

                private boolean isAdjacent(int int1, int int2) {
                    return Math.abs(int1 - int2) <= 1;
                }
            };
        }
    }
}
