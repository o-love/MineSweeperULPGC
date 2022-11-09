package net.oscarlove.minesweeper.model;

import net.oscarlove.minesweeper.model.cell.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    void defaultTest() {
        assertEquals(Cell.State.UNTOUCHED, buildCell().getState());
    }

    @Test
    void assignmentSelectedTest() {
        assignmentChecker(Cell.State.SELECTED);
    }

    @Test
    void assignmentFlaggedTest() {
        assignmentChecker(Cell.State.FLAGGED);
    }

    void assignmentChecker(Cell.State state) {
        Cell cell = buildCell();
        cell.setState(state);
        assertEquals(state, cell.getState());
    }

    @Test
    void deselectionThrowsUntouchedTest() {
        deselectionThrowsChecker(Cell.State.UNTOUCHED);
    }

    @Test
    void deselectionThrowsFlaggedTest() {
        deselectionThrowsChecker(Cell.State.FLAGGED);
    }

    void deselectionThrowsChecker(Cell.State state) {
        Cell cell = buildCell();
        cell.setState(Cell.State.SELECTED);
        assertThrows(IllegalStateException.class, () -> cell.setState(state));
    }

    @Test
    void reSelectionDoesntThrowTest() {
        Cell cell = buildCell();
        cell.setState(Cell.State.SELECTED);
        assertDoesNotThrow(() -> cell.setState(Cell.State.SELECTED));
    }

    @Test
    void checkDeselectionDoesntChangeState() {
        Cell cell = buildCell();
        cell.setState(Cell.State.SELECTED);
        try {
            cell.setState(Cell.State.UNTOUCHED);
        } catch (Exception ignored) {}

        assertEquals(Cell.State.SELECTED, cell.getState());
    }

    @Test
    void checkThrowsOnNullState(){
        Cell cell = buildCell();
        assertThrows(NullPointerException.class, () -> cell.setState(null));
    }

    Cell buildCell() {
        return Cell.build();
    }


}
