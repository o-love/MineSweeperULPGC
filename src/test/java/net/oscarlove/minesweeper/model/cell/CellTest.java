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
        testCell.setState(Cell.State.SELECTED);
        assertEquals(testCell.getState(), Cell.State.SELECTED);
    }

    @Test
    void flagTest() {
        testCell.setState(Cell.State.FLAGGED);
        assertEquals(testCell.getState(), Cell.State.FLAGGED);
    }

    @Test
    void DeselectTest() {
        testCell.setState(Cell.State.DESELECTED);
        assertEquals(testCell.getState(), Cell.State.DESELECTED);
    }
}