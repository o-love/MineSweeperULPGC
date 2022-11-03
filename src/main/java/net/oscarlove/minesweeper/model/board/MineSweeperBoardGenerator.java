package net.oscarlove.minesweeper.model.board;

import net.oscarlove.minesweeper.model.Position;
import net.oscarlove.minesweeper.model.cell.Cell;
import net.oscarlove.minesweeper.model.cell.EmptyCell;
import net.oscarlove.minesweeper.model.cell.MinedCell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MineSweeperBoardGenerator {

    private int boardHeight;
    private int boardWidth;
    private int numberOfMines;

    /**
     * Setups a generator to generate random {@code MineSweeperBoars}s.
     *
     * @param boardHeight The number of rows the board will have.
     * @param boardWidth The number of columns the board will have.
     */
    public MineSweeperBoardGenerator(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        // Default values
        numberOfMines = 2 * boardHeight;
    }

    /**
     *
     * @param numberOfMines Sets the number of min to be generated when generating a board.
     */
    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    public MineSweeperBoard generate() {
        List<List<Cell>> rawBoard = initializeRawBoard();

        fillRawBoardWithMinesAndNumberedCells(rawBoard);

        completeRawBoardWithEmptyCells(rawBoard);

        // TODO: OPTIONAL: Check if board is solvable and if not (recursive?). Need to create solver for this.

        return new MineSweeperBoard(rawBoard);
    }

    private List<List<Cell>> initializeRawBoard() {
        List<List<Cell>> rawBoard = new ArrayList<>(boardHeight);

        for (int i = 0; i < boardHeight; i++) {
            rawBoard.add(i, new ArrayList<>(boardWidth));
        }

        return rawBoard;
    }

    private void fillRawBoardWithMinesAndNumberedCells(List<List<Cell>> rawBoard) {
        // TODO: As we add mines we increase all numbered cells around it by one. if there isn't a numbered cell we add a new one.
        //  Cache solution would be better since numbered cells are immutable
    }

    private void completeRawBoardWithEmptyCells(List<List<Cell>> rawBoard) {
        forEachCellWithPosition(rawBoard, positionCellPair -> {
            if (positionCellPair.cell==null) {
                List<Cell> ourRow = rawBoard.get(positionCellPair.position.row());

                ourRow.set(
                        positionCellPair.position.column(),
                        new EmptyCell()
                );
            }
        });
    }

    private record PositionCellPair(Position position, Cell cell) {

    }

    private void forEachCellWithPosition(List<List<Cell>> rawBoard, Consumer<PositionCellPair> positionCellPairConsumer) {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                positionCellPairConsumer.accept(new PositionCellPair(
                        new Position(i, j),
                        rawBoard.get(i).get(j)
                ));
            }
        }
    }
}
