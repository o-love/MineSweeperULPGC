package net.oscarlove.minesweeper.model;

import java.util.Map;

public class BoardTest {



    record Position(int row, int column) {}

    interface Board {
        Cell getCell(Position position);
        int calculateCellValue(Position position);

        static Board build(Map<Position, Cell> minedCells) {
            return new Board() {
                @Override
                public Cell getCell(Position position) {
                    return null;
                }

                @Override
                public int calculateCellValue (Position position) {
                    return 0;
                }
            };
        }
    }
}
