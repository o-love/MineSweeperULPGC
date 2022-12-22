package net.oscarlove.minesweeper.view.Swing;

import net.oscarlove.minesweeper.base.datatypes.Dimension;
import net.oscarlove.minesweeper.view.BoardView;
import net.oscarlove.minesweeper.view.CellDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoardSwing extends JPanel implements BoardView {

    public static BoardSwing of(Dimension dimension) {
        return new BoardSwing(generateCells(dimension));
    }

    private static List<List<CellSwing>> generateCells(Dimension dimension) {
        return null; // TODO
    }

    private BoardSwing(List<List<CellSwing>> cells) {
        setupGUI(cells);
    }


    private void setupGUI(List<List<CellSwing>> cells) {
        setLayout(new GridLayout(cells.size(), cells.get(0).size()));

        for (List<CellSwing> rowList : cells) {
            for (CellSwing cellSwing : rowList) {
                this.add(cellSwing);
            }
        }
    }

    @Override
    public CellDialog getCellDialog(int row, int column) {
        return null; // TODO
    }
}
