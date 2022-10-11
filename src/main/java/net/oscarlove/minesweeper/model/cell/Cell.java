package net.oscarlove.minesweeper.model.cell;

import net.oscarlove.minesweeper.model.interfaces.Observable;
import net.oscarlove.minesweeper.model.interfaces.Observer;
import net.oscarlove.minesweeper.model.util.ObservableImpl;

public abstract class Cell implements Observable {

    private final int x;
    private final int y;
    private final ObservableImpl observableManager = new ObservableImpl();

    private boolean isSelected = false;

    private boolean flag = false;

    /**
     * @implSpec Both booleans are initially set to {@code false}.
     * @param x Row cell is located at.
     * @param y Column cell is located at.
     *
     * @throws IllegalArgumentException When {@code x} or {@code y} is negative.
     */
    public Cell(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("x and y must be positive numbers");
        }

        this.x = x;
        this.y = y;
    }

    public void setAsSelected() {
        isSelected = true;

        updateObservers();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;

        updateObservers();
    }

    public boolean getFlag() {
        return flag;
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
