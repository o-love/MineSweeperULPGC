package net.oscarlove.minesweeper.model.cell;

public class NumberedCell extends Cell {

    private int cellValue;

    public NumberedCell(int x, int y, int cellValue) {
        super(x, y);

        // TODO: Add argument validation

        this.cellValue = cellValue;
    }

    public int getCellValue() {
        return cellValue;
    }
}
