package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;

import java.util.Collection;

public interface Board {

    interface Cell {
        enum State {
            OPEN,
            CLOSED,
            FLAGGED
        }

        void setSelected();

        boolean toggleFlag();

        State getState();

        int getValue();

        Collection<Cell> getNeighbors();
    }

    Cell getCell(int i, int j);

    static Board create(Dimension dimension, Collection<Position> minedCells) {
        return new Board() {
            final int[][] cells = new int[dimension.rows()][dimension.columns()];

            {
                setMinedCells();
                calculateCellValues();
            }

            private void setMinedCells() {
                for (Position pos : minedCells) {
                    this.cells[pos.row()][pos.column()] = -1;
                }
            }

            private void calculateCellValues() {
            }

            @Override
            public Cell getCell(int i, int j) {
                return new Cell() {
                    @Override
                    public void setSelected() {

                    }

                    @Override
                    public boolean toggleFlag() {
                        return false;
                    }

                    @Override
                    public State getState() {
                        return null;
                    }

                    @Override
                    public int getValue() {
                        return -1;
                    }

                    @Override
                    public Collection<Cell> getNeighbors() {
                        return null;
                    }
                };
            }
        };
    }
}
