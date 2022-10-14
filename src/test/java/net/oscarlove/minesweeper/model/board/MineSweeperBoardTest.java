package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.cell.EmptyCell;
import net.oscarlove.minesweeper.model.cell.MinedCell;
import net.oscarlove.minesweeper.model.cell.NumberedCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


class MineSweeperBoardTest {

    MineSweeperBoard board;


    MineSweeperBoard buildBoard1() {
        List<List<Cell>> board = new ArrayList<>(6);

        List<Cell> temp;
        // Row 1
        temp = new ArrayList<>(5);
        temp.add(new MinedCell());
        temp.add(new NumberedCell(3));
        temp.add(new MinedCell());
        temp.add(new NumberedCell(2));
        temp.add(new NumberedCell(1));

        board.add(temp);

        // Row 2
        temp = new ArrayList<>(5);
        temp.add(new MinedCell());
        temp.add(new NumberedCell(4));
        temp.add(new NumberedCell(2));
        temp.add(new NumberedCell(3));
        temp.add(new MinedCell());

        board.add(temp);

        // Row 3
        temp = new ArrayList<>(5);
        temp.add(new NumberedCell(1));
        temp.add(new NumberedCell(2));
        temp.add(new MinedCell());
        temp.add(new NumberedCell(3));
        temp.add(new NumberedCell(2));

        board.add(temp);

        // Row 4
        temp = new ArrayList<>(5);
        temp.add(new NumberedCell(1));
        temp.add(new NumberedCell(2));
        temp.add(new NumberedCell(3));
        temp.add(new MinedCell());
        temp.add(new NumberedCell(1));

        board.add(temp);

        // Row 5
        temp = new ArrayList<>(5);
        temp.add(new NumberedCell(1));
        temp.add(new MinedCell());
        temp.add(new NumberedCell(2));
        temp.add(new NumberedCell(1));
        temp.add(new NumberedCell(1));

        board.add(temp);

        // Row 6
        temp = new ArrayList<>(5);
        temp.add(new NumberedCell(1));
        temp.add(new NumberedCell(1));
        temp.add(new NumberedCell(1));
        temp.add(new EmptyCell());
        temp.add(new EmptyCell());
        temp.add(new EmptyCell());

        board.add(temp);

        return new MineSweeperBoard(board);
    }



    @BeforeEach
    void setup() {
        this.board = buildBoard1();
    }

    @Test
    void basicBoardTest() {

    }
}