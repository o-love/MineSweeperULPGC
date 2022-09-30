package net.oscarlove.minesweeper.model.cell;

import net.oscarlove.minesweeper.model.interfaces.Observable;
import net.oscarlove.minesweeper.model.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Cell implements Observable {

    private final int x;
    private final int y;
    private boolean isVisible = false;

    List<Observer> observerList = new ArrayList<>();

    protected Cell(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("x and y must be positive numbers");
        }

        this.x = x;
        this.y = y;
    }

    public void selected() {
        isVisible = true;

        updateObservers();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    // Observable


    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    protected void updateObservers() {
        observerList.forEach(Observer::update);
    }
}
