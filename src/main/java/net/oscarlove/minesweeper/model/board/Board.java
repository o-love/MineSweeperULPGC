package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.base.events.PositionEvent;
import net.oscarlove.minesweeper.model.Dimension;
import net.oscarlove.minesweeper.model.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public interface Board {

    interface Cell {
        enum State {
            OPEN,
            CLOSED,
            FLAGGED
        }

        void setSelected();

        void toggleFlag();

        State state();

        int value();

        Collection<Cell> neighbors();
    }

    Cell getCell(Position position);

    Cell getCell(int i, int j);

    void setCellStateListener(PositionEvent cellStateChangeEvent);

    Dimension dimension();

    static Board create(Dimension dimension, Collection<Position> minedCells) {
        return new Board() {
            final int[][] cellValues = new int[dimension.rows()][dimension.columns()];
            final Cell.State[][] cellStates = new Cell.State[dimension.rows()][dimension.columns()];

            private PositionEvent cellStateChangeEvent = PositionEvent.NULL;

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
                forAdjacentPosition(pos, position -> {
                    if (isPositionOutOfBounds(position)) return;
                    if (isPositionAMine(position)) return;

                    this.cellValues[position.row()][position.column()] += 1;
                });
            }

            private void forAdjacentPosition(Position pos, Consumer<Position> positionConsumer) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;

                        positionConsumer.accept(new Position(pos.row() + i, pos.column() + j));
                    }
                }
            }

            private boolean isPositionOutOfBounds(Position position) {
                return isPositionOutOfBounds(position.row(), position.column());
            }

            private boolean isPositionOutOfBounds(int row, int column) {
                return row < 0 || column < 0 ||
                        row >= dimension.rows() || column >= dimension.columns();
            }

            private boolean isPositionAMine(Position position) {
                return isPositionAMine(position.row(), position.column());
            }

            private boolean isPositionAMine(int row, int column) {
                return minedCells.contains(new Position(row, column));
            }

            private void initializeCellStates() {
                dimension.positionsUnderneath().forEach(position ->
                        this.cellStates[position.row()][position.column()] = Cell.State.OPEN
                );
            }

            @Override
            public void setCellStateListener(PositionEvent cellStateChange) {
                this.cellStateChangeEvent = cellStateChange;
            }

            @Override
            public Cell getCell(Position position) {
                return getCell(position.row(), position.column());
            }

            @Override
            public Dimension dimension() {
                return dimension;
            }

            @Override
            public Cell getCell(int row, int col) {
                return new Cell() {
                    @Override
                    public void setSelected() {
                        if (state() == State.CLOSED) return;

                        setState(State.CLOSED);

                        if (value() == 0) {
                            neighbors().forEach(Cell::setSelected);
                        }
                    }

                    @Override
                    public void toggleFlag() {
                        if (cellStates[row][col] == State.CLOSED) return;

                        setState(cellStates[row][col] == State.OPEN ? State.FLAGGED : State.OPEN);
                    }

                    private void setState(State state) {
                        cellStates[row][col] = state;
                        cellStateChangeEvent.handle(row, col);
                    }

                    @Override
                    public State state() {
                        return cellStates[row][col];
                    }

                    @Override
                    public int value() {
                        return cellValues[row][col];
                    }

                    @Override
                    public Collection<Cell> neighbors() {
                        Set<Cell> toReturn = new HashSet<>();

                        populateWithNeighbors(toReturn);

                        return toReturn;
                    }

                    private void populateWithNeighbors(Collection<Cell> cells) {
                        forAdjacentPosition(new Position(row, col), position -> {
                            if (isPositionOutOfBounds(position)) return;

                            cells.add(getCell(position));
                        });
                    }
                };
            }
        };
    }
}
