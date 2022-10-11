package net.oscarlove.minesweeper.model.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private static class CellImplTesting extends Cell {
        CellImplTesting(int x, int y) {
            super(x, y);
        }
    }

    Cell testCell;

    @BeforeEach
    void setup() {
        testCell = new CellImplTesting(3, 3);
    }

    @Test
    void basicTestDefaultCreation(){
        assertEquals(testCell.getX(), 3);
        assertEquals(testCell.getY(), 3);
        assertFalse(testCell.getFlag());
        assertFalse(testCell.isSelected());
    }

    @Test
    void selectTest() {
        testCell.setAsSelected();
        assertTrue(testCell.isSelected());
    }

    @Test
    void flagSetTrueTest() {
        testCell.setFlag(true);
        assertTrue(testCell.getFlag());
    }

    @Test
    void flagSetFalseTest() {
        testCell.setFlag(true);
        testCell.setFlag(false);
        assertFalse(testCell.getFlag());
    }

    @Test
    void testNegativeRow() {
        assertThrows(IllegalArgumentException.class,() -> new CellImplTesting(-2, 3));
    }

    @Test
    void testNegativeColumn () {
        assertThrows(IllegalArgumentException.class, () -> new CellImplTesting(3, -2));
    }
}