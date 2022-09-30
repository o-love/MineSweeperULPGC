package net.oscarlove.minesweeper.model;

import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.cell.EmptyCell;

public class Board {

    public static class BoardBuilder {

        private Cell[][] cellArray;

        public BoardBuilder(int width, int height) {
            this.cellArray = new Cell[width][height];
        }

        public void setCell(int row, int column, Cell cell) {
            cellArray[row][column] = cell;
        }

        public void setRestToEmpty() {
            for (int i = 0; i < cellArray.length; i++) {
                for (int j = 0; j < cellArray[0].length; j++) {
                    if (cellArray[i][j] == null) {
                        cellArray[i][j] = new EmptyCell(i, j);
                    }
                }
            }
        }
    }

    private Cell[][] boardCells;

    private Board(BoardBuilder boardBuilder) {

    }

    public void selectCell(int x, int y) {
        boardCells[x][y].selected();
    }

    public Cell getCell(int row, int column) {
    }
}
