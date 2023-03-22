package cellsociety.model.cell;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import java.util.ArrayList;

/**
 * Game of life cell
 */

public class GameOfLifeCell extends Cell {


  public GameOfLifeCell(Integer cellConfig, String simulationType, String neighborType, String edgeType, String shapeType) {
    super(cellConfig, simulationType, neighborType, edgeType, shapeType);
    initiateCellState(cellConfig);
  }


  /**
   * initiate cell state according to the cell configuration
   * @param cellConfig the cell configuration
   */
  @Override
  public void initiateCellState(Integer cellConfig) {
    switch (cellConfig){
      case 0 -> cellState = CellState.DEAD;
      case 1 -> cellState = CellState.LIVING;
    }
    previousCellState = cellState;
  }

  /**
   * update cell in each updates iteration
   * @param allCells all the cells
   * @param heightCount height of the grid
   * @param widthCount width of the grid
   * @throws ClassOrMethodNotFoundException
   */

  @Override
  public void updateCell(CellStructure allCells, int heightCount, int widthCount)
      throws ClassOrMethodNotFoundException {
    int livingNeighbours = 0;

    for (Cell cell : findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
        allCells.findCellYCoordinate(this), heightCount, widthCount)) {
      if (cell.getPreviousCellState().equals(CellState.LIVING)) {
        livingNeighbours += 1;
      }
    }

    if (this.cellState.equals(CellState.LIVING)) {
      if (livingNeighbours != 2 && livingNeighbours != 3) {
        this.cellState = CellState.DEAD;
      }
    }
    else {
      if (livingNeighbours == 3) {
        this.cellState = CellState.LIVING;
      }
    }
  }

  /**
   * update cell when given the surrounding cells
   * @param surroundingCells the surrounding cells
   */

  public void updateCell(ArrayList<Cell> surroundingCells) {
    int livingNeighbours = 0;

    for (Cell cell : surroundingCells) {
      if (cell.getPreviousCellState().equals(CellState.LIVING)) {
        livingNeighbours += 1;
      }
    }

    if (this.cellState.equals(CellState.LIVING)) {
      if (livingNeighbours != 2 && livingNeighbours != 3) {
        this.cellState = CellState.DEAD;
      }
    }
    else {
      if (livingNeighbours == 3) {
        this.cellState = CellState.LIVING;
      }
    }
  }

  /**
   * check if the cell is at its default cell state
   * @return whether the cell is at its cell state
   */

  @Override
  public boolean ifDefault() {
    return cellState.equals(CellState.DEAD);
  }

  /**
   * reset the cell to its default cell state
   */
  @Override
  public void resetCell() {
    this.cellState = CellState.DEAD;
    this.previousCellState = CellState.DEAD;
  }
}
