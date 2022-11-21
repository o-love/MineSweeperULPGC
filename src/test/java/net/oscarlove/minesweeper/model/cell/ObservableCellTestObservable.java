package net.oscarlove.minesweeper.model.cell;

import net.oscarlove.minesweeper.base.OpenObservableTest;
import net.oscarlove.minesweeper.base.observer.Observer;
import net.oscarlove.minesweeper.base.observer.OpenObservable;

public class ObservableCellTestObservable extends OpenObservableTest {

    @Override
    protected OpenObservable build() {
        return new OpenObservable() {
            final ObservableCell observableCell = ObservableCell.create(Cell.build());

            @Override
            public void updateObservers() {
                observableCell.setState(Cell.State.UNTOUCHED);
            }

            @Override
            public void addObserver(Observer observer) {
                observableCell.addObserver(observer);
            }

            @Override
            public void removeObserver(Observer observer) {
                observableCell.removeObserver(observer);
            }
        };
    }
}
