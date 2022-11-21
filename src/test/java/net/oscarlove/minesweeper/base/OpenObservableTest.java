package net.oscarlove.minesweeper.base;

import static org.junit.jupiter.api.Assertions.*;

import net.oscarlove.minesweeper.base.observer.Observer;
import net.oscarlove.minesweeper.base.observer.OpenObservable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpenObservableTest {

    OpenObservable openObservable;
    Boolean hasExecuted;
    Integer haveExecuted;
    Observer defaultObserver = this::defaultObserverFunc;
    void defaultObserverFunc() {
        hasExecuted = true;
        haveExecuted++;
    }

    @BeforeEach
    void setup() {
        this.openObservable = build();
        this.hasExecuted = false;
        this.haveExecuted = 0;
    }

    @Test
    void addSingleObserverAndUpdateIt() {
        openObservable.addObserver(defaultObserver);
        openObservable.updateObservers();
        assertTrue(hasExecuted);
    }

    @Test
    void onlyAddingDoesNothing() {
        openObservable.addObserver(defaultObserver);
        assertFalse(hasExecuted);
    }

    @Test
    void singleObserverRemoveDoesntUpdate() {
        openObservable.addObserver(defaultObserver);
        openObservable.removeObserver(defaultObserver);

        openObservable.updateObservers();

        assertFalse(hasExecuted);
    }

    @Test
    void multipleObservers() {
        openObservable.addObserver(this::defaultObserverFunc);
        openObservable.addObserver(this::defaultObserverFunc);

        openObservable.updateObservers();

        assertEquals(2, haveExecuted);
    }

    @Test
    void havingSameObserverMultipleTimesOnlyExecutesOnce() {
        openObservable.addObserver(defaultObserver);
        openObservable.addObserver(defaultObserver);
        openObservable.addObserver(defaultObserver);

        openObservable.updateObservers();

        assertEquals(1, haveExecuted);
    }

    private OpenObservable build() {
        return OpenObservable.create();
    }
}
