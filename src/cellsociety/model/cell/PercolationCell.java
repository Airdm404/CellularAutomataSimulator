package cellsociety.model.cell;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import java.util.ArrayList;
import java.util.List;

public class PercolationCell extends Cell {

  public PercolationCell(Integer cellConfig, String simulationType, String neighborType, String edgeType, String shapeType) {
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
      case 2 -> this.cellState = CellState.BLOCK;
      case 0 -> this.cellState = CellState.OPEN;
      case 1 -> this.cellState = CellState.PERCOLATED;
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
    List<Cell> surroundingCell = findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
        allCells.findCellYCoordinate(this), heightCount, widthCount);
    this.updateCell(surroundingCell);
  }

  /**
   * update cell when given the surrounding cells
   * @param surroundingCell the surrounding cells
   */

  public void updateCell(List<Cell> surroundingCell) {
    if (cellState.equals(CellState.OPEN)){
      for (Cell cell: surroundingCell){
        if (cell.getPreviousCellState().equals(CellState.PERCOLATED)) {
          this.cellState = CellState.PERCOLATED;
          break;
        }
      }
    }
  }

  /**
   * check if the cell is at its default cell state
   * @return whether the cell is at its cell state
   */

  @Override
  public boolean ifDefault() {
    return cellState.equals(CellState.OPEN);
  }

  /**
   * reset the cell to its default cell state
   */
  @Override
  public void resetCell() {
    this.cellState = CellState.OPEN;
    this.previousCellState = CellState.OPEN;
  }
}
