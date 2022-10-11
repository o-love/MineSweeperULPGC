package net.oscarlove.minesweeper.model.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberedCellTest {

    NumberedCell cell;

    @BeforeEach
    void setup() {
        cell = new NumberedCell(3);
    }

    @Test
    void testNumbered() {
        assertEquals(cell.getCellValue(), 3);
    }

}