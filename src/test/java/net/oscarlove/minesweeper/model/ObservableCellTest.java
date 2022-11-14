package net.oscarlove.minesweeper.model;

import static org.junit.jupiter.api.Assertions.*;

import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.observer.Observable;
import net.oscarlove.minesweeper.observer.Observer;
import org.junit.jupiter.api.Test;

public class ObservableCellTest {

    private static class ObservableCell implements Cell, Observable {

        ObservableCell(Cell cell) {

        }

        @Override
        public void setState(State state) {

        }

        @Override
        public State getState() {
            return null;
        }

        @Override
        public void addObserver(Observer observer) {

        }

        @Override
        public void removeObserver(Observer observer) {

        }

        private void updateObservers() {

        }
    }
}
