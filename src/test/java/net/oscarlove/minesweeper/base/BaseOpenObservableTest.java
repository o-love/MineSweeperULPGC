package net.oscarlove.minesweeper.base;

import net.oscarlove.minesweeper.base.observer.OpenObservable;

public class BaseOpenObservableTest extends OpenObservableTest{
    @Override
    protected OpenObservable build() {
        return OpenObservable.create();
    }
}
