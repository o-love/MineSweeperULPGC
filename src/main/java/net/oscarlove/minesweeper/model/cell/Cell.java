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
     * Sets the {@code State} of the Cell to {@code SELECTED}.
     */
    public void setSelected() {
        setState(State.SELECTED);
    }

    /**
     * @throws IllegalStateException If trying to change flag while cell is selected.
     */
    public void setFlag(boolean flagSet) {
        if (this.state.equals(State.SELECTED)) {
            throw new IllegalStateException("Can't flag a selected Cell");
        }

        setState(flagSet ? State.FLAGGED : State.DESELECTED);
    }

    /**
     *
     * @return The state of the {@code Cell}
     */
    public State getState() {
        return this.state;
    }

    private void setState(State state) {


        this.state = state;

        updateObservers();
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
