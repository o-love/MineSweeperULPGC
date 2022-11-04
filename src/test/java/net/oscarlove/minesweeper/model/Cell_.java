package net.oscarlove.minesweeper.model;

import org.junit.jupiter.api.Test;

public class Cell_ {



    Cell buildCell() {
        return new Cell() {
            private State state;

            @Override
            public void setState(State state) {

            }
        };
    }

    private interface Cell {
        enum State {
            unselected,
            selected,
            flagged
        }

        void setState(State state);
    }


}
