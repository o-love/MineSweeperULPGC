package net.oscarlove.minesweeper.model;

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

                this.state = state;
            }

            @Override
            public State getState() {
                return state;
            }
        };
    }
}
