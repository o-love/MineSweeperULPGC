package net.oscarlove.minesweeper.model.interfaces;

import net.oscarlove.minesweeper.model.cell.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class MineSweeperBoardInterfaceTest<T extends MineSweeperBoardInterface> {

    protected class BoardTestData {
        T board;
        List<List<Cell>> cells;
        int numberOfMines;
        int numberOfUnflaggedMines;
    }
    protected abstract List<BoardTestData> getBoards();

    //
    private final List<BoardTestData> boardTestDataList = getBoards();


    @Test
    void testCellsEqual() {
        for (BoardTestData boardTest: boardTestDataList) {

            for (int i = 0; i < boardTest.cells.size(); i++) {
                List<Cell> row = boardTest.cells.get(i);

                for (int j = 0; j < row.size(); j++) {
                    assertEquals(boardTest.board.getCell(i,j), row.get(j));
                }
            }
        }
    }

    @Test
    void testCellsSquareBoard(){
        boardTestDataList.forEach(boardTestData -> {

            int length = boardTestData.cells.size();

            for (int i = 0; i < length; i++) {

                // test rows
                assertDoesNotThrow((Executable) boardTestData.board.getCell(i, length-1));
                assertThrows(IndexOutOfBoundsException.class, (Executable) boardTestData.board.getCell(i, length));

                // test columns
                assertDoesNotThrow((Executable) boardTestData.board.getCell(length-1, i));
                assertThrows(IndexOutOfBoundsException.class, (Executable) boardTestData.board.getCell(length, i));
            }
        });
    }

    @Test
    void basicGetNumberOfMines() {
        boardTestDataList.forEach(boardTestData -> assertEquals(boardTestData.numberOfUnflaggedMines, boardTestData.board.getNumberOfUnflaggedMines()));
    }

    @Test
    void basicGetNumberOfUnflaggedMines() {
        boardTestDataList.forEach(boardTestData -> assertEquals(boardTestData.numberOfMines, boardTestData.board.getNumberOfMines()));
    }
}