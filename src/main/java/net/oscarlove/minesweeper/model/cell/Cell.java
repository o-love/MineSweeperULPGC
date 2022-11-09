package net.oscarlove.minesweeper.model.cell;

public interface Cell {
    enum State {
        UNTOUCHED,
        SELECTED,
        FLAGGED
    }

    void setState(State state);

    State getState();

    static Cell build() {
        return new BaseCell();
    }
}