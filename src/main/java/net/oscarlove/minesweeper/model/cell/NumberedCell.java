package net.oscarlove.minesweeper.model.cell;

public class NumberedCell extends Cell {

    private final int cellValue;

    /**
     * @param cellValue Value indicating number of mines around this cell.
     * @throws IllegalArgumentException When cellValue has an out-of-bounds number.
     */
    public NumberedCell(int cellValue) {
        super();

        checkValid(cellValue);

        this.cellValue = cellValue;
    }

    public int getCellValue() {
        return cellValue;
    }

    private void checkValid(int cellValue) {
        if (cellValue < 1 || cellValue > 8) {
            throw new IllegalArgumentException("Invalid value for NumberedCell, values can only be between 1 and 8, both inclusive.");
        }
    }
}
