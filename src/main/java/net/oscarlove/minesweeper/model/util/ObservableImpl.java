package net.oscarlove.minesweeper.model.util;

import net.oscarlove.minesweeper.model.interfaces.Observable;
import net.oscarlove.minesweeper.model.interfaces.Observer;

import java.util.ArrayList;
import java.util.Collection;

public class ObservableImpl implements Observable {

    Collection<Observer> observers;

    public ObservableImpl() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void updateObservers() {
        observers.forEach(Observer::update);
    }
}
