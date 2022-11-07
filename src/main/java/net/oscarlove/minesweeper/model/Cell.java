package net.oscarlove.minesweeper.model;

import java.util.Objects;

interface Cell {
    enum State {
        UNTOUCHED,
        SELECTED,
        FLAGGED
    }

    void setState(State state);

    State getState();

    static Cell build() {
        return new Cell() {

            private State state = State.UNTOUCHED;

            @Override
            public void setState(State state) {
                if (this.state == State.SELECTED && state != State.SELECTED) {
                    throw new IllegalStateException("State of Cell is Selected. Can't unselect");
                }
                if (state == null) {
                    throw new NullPointerException("State given to Cell was null");
                }

                this.state = state;
            }

            @Override
            public State getState() {
                return state;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Cell aCell = (Cell) o;
                return state == aCell.getState();
            }

            @Override
            public int hashCode() {
                return Objects.hash(state);
            }
        };
    }
}