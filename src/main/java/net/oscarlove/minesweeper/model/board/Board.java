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
            final int[][] cellValues = new int[dimension.rows()][dimension.columns()];
            final Cell.State[][] cellStates = new Cell.State[dimension.rows()][dimension.columns()];

            {
                initializeCellValues();
            }

            private void initializeCellValues() {
                for (Position pos : minedCells) {
                    this.cellValues[pos.row()][pos.column()] = -1;

                    incrementAdjacentCells(pos);
                }
            }

            private void incrementAdjacentCells(Position pos) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        if (isPositionInvalid(pos.row() + i, pos.column() + j)) continue;

                        this.cellValues[pos.row() + i][pos.column() + j] += 1;
                    }
                }
            }

            private boolean isPositionInvalid(int row, int column) {
                return row < 0 || column < 0 ||
                        row >= dimension.rows() || column >= dimension.columns();
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
                        return cellValues[i][j];
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
