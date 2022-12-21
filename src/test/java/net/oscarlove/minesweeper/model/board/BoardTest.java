package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.base.datatypes.Dimension;
import net.oscarlove.minesweeper.base.datatypes.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    public void board_exists() {
        assertThat(buildBoard(new Dimension(2, 2), Collections.EMPTY_LIST))
                .isNotNull();
    }

    @Test
    public void cell_exists() {
        assertThat(baseBoard.getCell(0, 2))
                .isNotNull();
    }

    @Test
    public void mine_value_is_minus_one() {
        assertThat(buildBoard(new Dimension(2, 2), List.of(new Position(0, 1))).getCell(0, 1).value())
                .isEqualTo(-1);
    }

    @Test
    public void zero_value_cell_is_zero() {
        assertThat(buildBoard(new Dimension(2, 2), List.of()).getCell(1, 1).value())
                .isEqualTo(0);
    }

    @Test
    public void test_values_around_single_mine() {
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
                assertThat(board.getCell(pos.row() + i, pos.column() + j).value())
                        .isEqualTo(value);
            }
        }
    }

    @Test
    public void test_values_in_mid_ground_between_two_mines() {
        Board board = buildBoard(new Dimension(3, 3), List.of(new Position(1, 0), new Position(1, 2)));

        assertThat(board.getCell(0, 0).value())
                .isEqualTo(1);
        assertThat(board.getCell(0, 1).value())
                .isEqualTo(2);
        assertThat(board.getCell(0, 2).value())
                .isEqualTo(1);
    }

    @Test
    public void test_values_around_two_adjacent_mines() {
        Board board = buildBoard(new Dimension(3, 3), List.of(new Position(1, 0), new Position(1, 1)));

        assertThat(board.getCell(0, 0).value())
                .isEqualTo(2);
        assertThat(board.getCell(0, 1).value())
                .isEqualTo(2);
        assertThat(board.getCell(0, 2).value())
                .isEqualTo(1);

        assertThat(board.getCell(1, 0).value())
                .isEqualTo(-1);
        assertThat(board.getCell(1, 1).value())
                .isEqualTo(-1);
        assertThat(board.getCell(1, 2).value())
                .isEqualTo(1);
    }

    @Test
    public void base_state_exists() {
        assertThat(baseBoard.getCell(0, 0).state())
                .isNotNull();
    }

    @Test
    public void base_state_is_open() {
        assertThat(baseBoard.getCell(0, 0).state())
                .isEqualTo(Board.Cell.State.OPEN);
    }

    @Test
    public void state_is_modified_by_select_to_closed() {
        baseBoard.getCell(0, 0).setSelected();
        assertThat(baseBoard.getCell(0, 0).state())
                .isEqualTo(Board.Cell.State.CLOSED);
    }

    @Test
    public void flag_toggle_toggles_flag_state() {
        baseBoard.getCell(0, 0).toggleFlag();
        assertThat(baseBoard.getCell(0, 0).state())
                .isEqualTo(Board.Cell.State.FLAGGED);

        baseBoard.getCell(0, 0).toggleFlag();
        assertThat(baseBoard.getCell(0, 0).state())
                .isEqualTo(Board.Cell.State.OPEN);
    }

    @Test
    public void flag_toggle_after_select_does_nothing() {
        baseBoard.getCell(0, 0).setSelected();
        baseBoard.getCell(0, 0).toggleFlag();
        assertThat(baseBoard.getCell(0, 0).state())
                .isEqualTo(Board.Cell.State.CLOSED);
    }

    @Test
    public void neighbors_exist() {
        assertThat(baseBoard.getCell(0, 0).neighbors()).isNotNull();
    }

    @Test
    public void neighbors_is_not_empty() {
        assertThat(baseBoard.getCell(0, 0).neighbors()).isNotEmpty();
    }

    @Test
    public void neighbors_returns_right_number_of_cells_for_fully_surrounded_cell() {
        assertThat(baseBoard.getCell(1, 1).neighbors().size()).isEqualTo(8);
    }

    @Test
    public void neighbors_returns_right_number_of_cells_for_edges() {
        assertThat(baseBoard.getCell(0, 0).neighbors().size()).isEqualTo(3);
        assertThat(baseBoard.getCell(0, 1).neighbors().size()).isEqualTo(5);
        assertThat(baseBoard.getCell(1, 0).neighbors().size()).isEqualTo(5);
    }

    @Test
    public void neighbors_returns_actual_neighbors() {
        baseBoard.getCell(1, 1).neighbors().forEach(Board.Cell::setSelected);
        test_all_around_cell_has_state(baseBoard, new Position(1, 1), Board.Cell.State.CLOSED);

        setup();

        baseBoard.getCell(1, 4).neighbors().forEach(Board.Cell::toggleFlag);
        test_all_around_cell_has_state(baseBoard, new Position(1, 4), Board.Cell.State.FLAGGED);
    }

    public void test_all_around_cell_has_state(Board board, Position pos, Board.Cell.State state) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                assertThat(board.getCell(pos.row() + i, pos.column() + j).state())
                        .isEqualTo(state);
            }
        }
    }

    @Test
    public void test_cascade_on_select_zero_value_depth_one() {
        Board board = buildBoard(new Dimension(3, 3), List.of(new Position(1, 2)));
        board.getCell(1, 0).setSelected();

        assertThat(board.getCell(0, 0).state()).isEqualTo(Board.Cell.State.CLOSED);
        assertThat(board.getCell(1, 0).state()).isEqualTo(Board.Cell.State.CLOSED);
        assertThat(board.getCell(2, 0).state()).isEqualTo(Board.Cell.State.CLOSED);
        assertThat(board.getCell(0, 1).state()).isEqualTo(Board.Cell.State.CLOSED);
        assertThat(board.getCell(1, 1).state()).isEqualTo(Board.Cell.State.CLOSED);
        assertThat(board.getCell(2, 1).state()).isEqualTo(Board.Cell.State.CLOSED);
    }

    @Test
    public void test_cascade_does_not_go_overbound() {
        Board board = buildBoard(new Dimension(3, 3), List.of(new Position(1, 2)));
        board.getCell(1, 0).setSelected();

        assertThat(board.getCell(0, 2).state()).isEqualTo(Board.Cell.State.OPEN);
        assertThat(board.getCell(1, 2).state()).isEqualTo(Board.Cell.State.OPEN);
        assertThat(board.getCell(2, 2).state()).isEqualTo(Board.Cell.State.OPEN);
    }

    @Test
    public void test_cascade_on_select_recursive() {
        Board board = buildBoard(new Dimension(5, 5), List.of());
        board.getCell(0, 0).setSelected();

        for (Position pos : new Dimension(5, 5).positionsUnderneath()) {
            assertThat(board.getCell(pos.row(), pos.column()).state()).isEqualTo(Board.Cell.State.CLOSED);
        }
    }

    @Test
    public void test_board_dimension_exists() {
        assertThat(baseBoard.dimension()).isNotNull();
    }

    @Test
    public void test_board_dimension_correct() {
        Board board = buildBoard(new Dimension(8, 20), List.of());

        assertThat(board.dimension()).isEqualTo(new Dimension(8, 20));
    }

    private Board buildBoard(Dimension dimension, Collection<Position> minedCells) {
        return Board.create(dimension, minedCells);
    }
}