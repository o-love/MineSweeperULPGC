package net.oscarlove.minesweeper.model.cell;

import net.oscarlove.minesweeper.base.observer.Observable;
import net.oscarlove.minesweeper.base.observer.Observer;
import net.oscarlove.minesweeper.base.observer.OpenObservable;

import java.util.Objects;

public class ObservableCell implements Cell, Observable {

    /**
     *
     * @throws NullPointerException If {@code Cell} is Null
     */
    public static ObservableCell create(Cell cell) {
        Objects.requireNonNull(cell);

        return new ObservableCell(cell);
    }

    private final OpenObservable observable = OpenObservable.create();
    private final Cell cell;

    private ObservableCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void setState(State state) {
        cell.setState(state);
        observable.updateObservers();
    }

    @Override
    public State getState() {
        return cell.getState();
    }

    @Override
    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observable.removeObserver(observer);
    }
}
