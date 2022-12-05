package net.oscarlove.minesweeper.base.events;

public interface PositionEvent {
    PositionEvent NULL = (row, col) -> {
    };

    void handle(int row, int col);
}
