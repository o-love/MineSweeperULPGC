package net.oscarlove.minesweeper.base.observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface OpenObservable extends Observable {
    void updateObservers();

    static OpenObservable create() {
        return new OpenObservable() {

            private final Collection<Observer> observers = new HashSet<>();
            @Override
            public void updateObservers() {

            }

            @Override
            public void addObserver(Observer observer) {

            }

            @Override
            public void removeObserver(Observer observer) {

            }
        };
    }
}
