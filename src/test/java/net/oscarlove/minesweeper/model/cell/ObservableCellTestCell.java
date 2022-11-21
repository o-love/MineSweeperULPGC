package net.oscarlove.minesweeper.model.cell;

public class ObservableCellTestCell extends GeneralCellTest {

    @Override
    ObservableCell buildCell() {
        return ObservableCell.create(Cell.build());
    }
}
