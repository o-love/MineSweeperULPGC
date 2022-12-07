package net.oscarlove.minesweeper.view;

import net.oscarlove.minesweeper.base.events.NotifyEvent;

public interface CellDialog {
    enum Display {
        CLEAR,
        VISIBLE,
        FLAG
    }

    void refreshDisplay(Display display);

    CellDialog onMainClick(NotifyEvent event);

    CellDialog onSecondaryClick(NotifyEvent event);
}
