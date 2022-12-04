package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private Board baseBoard;

    @BeforeEach
    public void setup() {
        baseBoard = buildBoard(new Dimension(10, 10), List.of(
                new Position(1, 5),
                new Position(4, 7),
                new Position(6, 3),
                new Position(3, 5),
                new Position(8, 8),
                new Position(0, 0)
        ));
    }

    @Test
    public void testBoardExists() {
        assertThat(buildBoard(new Dimension(2, 2), Collections.EMPTY_LIST))
                .isNotNull();
    }

    @Test
    public void checkCellExists() {
        assertThat(baseBoard.getCell(0, 2))
                .isNotNull();
    }

    @Test
    public void testMineValue() {
        assertThat(buildBoard(new Dimension(2, 2), List.of(new Position(0, 1))).getCell(0, 1).getValue())
                .isEqualTo(-1);
    }

    @Test
    public void testZeroValue() {
        assertThat(buildBoard(new Dimension(2, 2), List.of()).getCell(1, 1).getValue())
                .isEqualTo(0);
    }

    @Test
    public void testSingleMineNeighborValueIsOne() {
        testAllAroundPosHasVal(
                buildBoard(new Dimension(3, 3), List.of(new Position(1, 1))),
                new Position(1, 1),
                1
        );
    }

    private void testAllAroundPosHasVal(Board board, Position pos, int value) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                assertThat(board.getCell(pos.row() + i, pos.column() + j).getValue())
                        .isEqualTo(value);
            }
        }
    }

    @Test
    public void testIntersectionHasValue2WhileNonIntersectionHasValue1() {
        Board board = buildBoard(new Dimension(3, 3), List.of(new Position(1, 0), new Position(1, 2)));

        assertThat(board.getCell(0, 0).getValue())
                .isEqualTo(1);
        assertThat(board.getCell(0, 1).getValue())
                .isEqualTo(2);
        assertThat(board.getCell(0, 2).getValue())
                .isEqualTo(1);
    }

    @Test
    public void testTwoAdjacentMines() {
        Board board = buildBoard(new Dimension(3, 3), List.of(new Position(1, 0), new Position(1, 1)));

        assertThat(board.getCell(0, 0).getValue())
                .isEqualTo(2);
        assertThat(board.getCell(0, 1).getValue())
                .isEqualTo(2);
        assertThat(board.getCell(0, 2).getValue())
                .isEqualTo(1);

        assertThat(board.getCell(1, 0).getValue())
                .isEqualTo(-1);
        assertThat(board.getCell(1, 1).getValue())
                .isEqualTo(-1);
        assertThat(board.getCell(1, 2).getValue())
                .isEqualTo(1);
    }


    private Board buildBoard(Dimension dimension, Collection<Position> minedCells) {
        return Board.create(dimension, minedCells);
    }
}
