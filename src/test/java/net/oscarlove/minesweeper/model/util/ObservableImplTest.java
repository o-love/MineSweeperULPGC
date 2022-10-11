package net.oscarlove.minesweeper.model.util;

import net.oscarlove.minesweeper.model.interfaces.Observer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ObservableImplTest {

    boolean testBool;

    ObservableImpl observable;

    @BeforeEach
    void setup() {
        testBool = false;
        observable = new ObservableImpl();
    }

    @Test
    void basicTestAdd() {
        observable.addObserver(() -> System.out.println("test"));
    }

    @Test
    void basicRemoveTest() {
        Observer observer = Assertions::fail;

        observable.addObserver(observer);
        observable.removeObserver(observer);

        observable.updateObservers();
    }

    @Test
    void basicUpdateTest() {
        Observer observer = () -> testBool = true;

        observable.addObserver(observer);

        observable.updateObservers();

        assertTrue(testBool);
    }



}