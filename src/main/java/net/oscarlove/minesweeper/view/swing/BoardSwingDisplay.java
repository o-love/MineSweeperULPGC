package net.oscarlove.minesweeper.view.swing;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.board.Board;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.view.BoardDisplay;
import net.oscarlove.minesweeper.view.CellDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.BiConsumer;

public class BoardSwingDisplay extends JPanel implements BoardDisplay {

    public static class Builder {
        private Board board;
        private final Set<BiConsumer<Position, CellDialog>> consumerSet = new HashSet<>();

        public Builder of(Board board) {
            Objects.requireNonNull(board);
            this.board = board;
            return this;
        }

        public Builder withPositionListener(BiConsumer<Position, CellDialog> consumer) {
            Objects.requireNonNull(consumer);
            this.consumerSet.add(consumer);
            return this;
        }

        public BoardSwingDisplay build() {
            return new BoardSwingDisplay(
                    populatedListOf(produceBare2dList(board.produceDimension()))
            );
        }

        private List<List<CellSwing>> populatedListOf(List<List<CellSwing>> cells) {
            for (Position position : board.produceDimension().positionsUnderneath()) {
                cells.get(position.row())
                        .add(
                                position.column(),
                                produceCellSwing(position)
                        );
            }
            return cells;
        }

        private CellSwing produceCellSwing(Position position) {
            CellSwing cellSwing = new CellSwing(board.getCell(position), board.getCellValue(position));

            consumerSet.forEach(consumer -> cellSwing.addObserver(() -> consumer.accept(position, cellSwing)));

            return cellSwing;
        }
    }

    private static List<List<CellSwing>> produceBare2dList(Dimension dimension) {
        List<List<CellSwing>> cells = new ArrayList<>(dimension.rows());

        for (int i = 0; i < dimension.rows(); i++) {
            cells.add(i, new ArrayList<>(dimension.columns()));
        }

        return cells;
    }

    private final List<List<CellSwing>> cells;

    private BoardSwingDisplay(List<List<CellSwing>> cells) {
        this.cells = cells;

        setupGUI();
    }

    private void setupGUI() {
        setLayout(new GridLayout(cells.size(), cells.get(0).size()));

        for (List<CellSwing> rowList : cells) {
            for (CellSwing cellSwing : rowList) {
                this.add(cellSwing);
            }
        }
    }

    @Override
    public void refreshCellDisplay(Position position, Cell cell) {
        cells.get(position.row()).get(position.column()).refreshCellDisplay(cell);
    }
}
