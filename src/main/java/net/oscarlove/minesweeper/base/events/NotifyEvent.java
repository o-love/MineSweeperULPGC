package net.oscarlove.minesweeper.base.events;

public interface NotifyEvent {
    NotifyEvent NULL = () -> {
    };

    void handle();
}
