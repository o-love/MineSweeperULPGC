package net.oscarlove.minesweeper.model.interfaces;

public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
