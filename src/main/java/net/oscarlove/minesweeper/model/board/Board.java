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
                initializeCellStates();
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

                        int row = pos.row() + i;
                        int column = pos.column() + j;

                        if (isPositionOutOfBounds(row, column)) continue;
                        if (isPositionAMine(row, column)) continue;

                        this.cellValues[row][column] += 1;
                    }
                }
            }

            private boolean isPositionOutOfBounds(int row, int column) {
                return row < 0 || column < 0 ||
                        row >= dimension.rows() || column >= dimension.columns();
            }

            private boolean isPositionAMine(int row, int column) {
                return minedCells.contains(new Position(row, column));
            }

            private void initializeCellStates() {
                for (int i = 0; i < dimension.rows(); i++) {
                    for (int j = 0; j < dimension.columns(); j++) {
                        this.cellStates[i][j] = Cell.State.OPEN;
                    }
                }
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
                        return cellStates[i][j];
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
