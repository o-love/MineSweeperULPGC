package net.oscarlove.minesweeper.model.cell;

import net.oscarlove.minesweeper.model.interfaces.Observable;
import net.oscarlove.minesweeper.model.interfaces.Observer;
import net.oscarlove.minesweeper.model.util.ObservableImpl;

public abstract class Cell implements Observable {

    public enum State {
        DESELECTED,
        SELECTED,
        FLAGGED,
    }

    private final ObservableImpl observableManager = new ObservableImpl();

    private State state = State.DESELECTED;

    /**
     *
     * @param state Set the state of the {@code Cell}
     */
    public void setState(State state) {
        this.state = state;

        updateObservers();
    }

    /**
     *
     * @return The state of the {@code Cell}
     */
    public State getState() {
        return this.state;
    }

    // Observable


    @Override
    public void addObserver(Observer observer) {
        observableManager.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observableManager.removeObserver(observer);
    }

    protected void updateObservers() {
        observableManager.updateObservers();
    }
}
