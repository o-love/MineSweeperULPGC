package net.oscarlove.minesweeper.base.observer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface OpenObservable extends Observable {
    void updateObservers();
}
