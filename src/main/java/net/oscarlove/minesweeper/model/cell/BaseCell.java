package net.oscarlove.minesweeper.model.cell;

import java.util.Objects;

class BaseCell implements Cell {

    static Cell create() {
        return new BaseCell();
    }

    private BaseCell() {
    }

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
        BaseCell baseCell = (BaseCell) o;
        return state == baseCell.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
