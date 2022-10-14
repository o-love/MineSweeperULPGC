package net.oscarlove.minesweeper.model.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private static class CellImplTesting extends Cell {
    }

    Cell testCell;

    @BeforeEach
    void setup() {
        testCell = new CellImplTesting();
    }

    @Test
    void basicTestDefaultCreation(){
        assertEquals(testCell.getState(), Cell.State.DESELECTED);

    }

    @Test
    void selectTest() {
        testCell.setSelected();
        assertEquals(testCell.getState(), Cell.State.SELECTED);
    }

    @Test
    void flagTest() {
        testCell.setFlag(true);
        assertEquals(testCell.getState(), Cell.State.FLAGGED);
    }

    @Test
    void DeFlagTest() {
        testCell.setFlag(true);
        testCell.setFlag(false);
        assertEquals(testCell.getState(), Cell.State.DESELECTED);
    }

    @Test
    void IllegalFlagEnableTest() {
        testCell.setSelected();
        assertThrows(IllegalStateException.class, () -> testCell.setFlag(true));
    }

    @Test
    void IllegalFlagDisableTest() {
        testCell.setSelected();
        assertThrows(IllegalStateException.class, () -> testCell.setFlag(true));
    }
}