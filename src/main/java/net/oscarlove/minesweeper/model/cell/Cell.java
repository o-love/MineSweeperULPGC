package net.oscarlove.minesweeper.model.cell;

public interface Cell {
    enum State {
        UNTOUCHED,
        SELECTED,
        FLAGGED
    }

    void setState(State state);

    State getState();

    static Cell create() {
        return new BaseCell();
    }

    static Cell of(State state) {
        Cell cell = create();
        cell.setState(state);
        return cell;
    }
}