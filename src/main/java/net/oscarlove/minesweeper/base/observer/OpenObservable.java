package net.oscarlove.minesweeper.base.observer;

import java.util.Collection;
import java.util.HashSet;

public interface OpenObservable extends Observable {
    void updateObservers();

    static OpenObservable create() {
        return new OpenObservable() {

            private final Collection<Observer> observers = new HashSet<>();
            @Override
            public void updateObservers() {
                observers.forEach(Observer::update);
            }

            @Override
            public void addObserver(Observer observer) {
                this.observers.add(observer);
            }

            @Override
            public void removeObserver(Observer observer) {
                this.observers.remove(observer);
            }
        };
    }
}
