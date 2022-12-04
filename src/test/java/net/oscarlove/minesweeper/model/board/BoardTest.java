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
    public void checkBoardExists() {
        assertThat(buildBoard(new Dimension(2, 2), Collections.EMPTY_LIST))
                .isNotNull();
    }

    @Test
    public void checkCellExists() {
        assertThat(baseBoard.getCell(0, 2))
                .isNotNull();
    }

    @Test
    public void checkMinesExists() {
        assertThat(buildBoard(new Dimension(2, 2), List.of(new Position(0, 1))).getCell(0, 1).getValue())
                .isEqualTo(-1);
    }


    private Board buildBoard(Dimension dimension, Collection<Position> minedCells) {
        return Board.create(dimension, minedCells);
    }
}
