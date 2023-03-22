package cellsociety.model.cell;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import java.util.ArrayList;

public class SpreadingOfFireCell extends Cell {
  public final static double BURN_PROB = 0.5;

  public SpreadingOfFireCell(Integer cellConfig, String simulationType, String neighborType, String edgeType, String shapeType){
    super(cellConfig,simulationType, neighborType, edgeType, shapeType);
    initiateCellState(cellConfig);
  }

  /**
   * initiate cell state according to the cell configuration
   * @param cellConfig the cell configuration
   */
  @Override
  public void initiateCellState(Integer cellConfig) {
    switch (cellConfig){
      case 0 -> this.cellState = CellState.EMPTY;
      case 1 -> this.cellState = CellState.TREE;
      case 2 -> this.cellState = CellState.BURNING;
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
    if (cellState.equals(CellState.TREE)){
      for (Cell cell: findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
          allCells.findCellYCoordinate(this), heightCount, widthCount)){
        if (cell.getPreviousCellState().equals(CellState.BURNING)){
          double prob = Math.random();
          if (prob >= BURN_PROB){
            cellState = CellState.BURNING;
          }
          break;
        }
      }
    }
    else if (cellState.equals(CellState.BURNING)){
      cellState = CellState.EMPTY;
    }
  }

  /**
   * update cell with given probability
   * @param allCells all the cells
   * @param heightCount the height count of the grid
   * @param widthCount the width count of the grid
   * @param prob the given probability
   * @throws ClassOrMethodNotFoundException
   */

  @Override
  //only for test
  public void updateCell(CellStructure allCells, int heightCount, int widthCount, double prob)
      throws ClassOrMethodNotFoundException {
    if (cellState.equals(CellState.TREE)){
      for (Cell cell: findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
          allCells.findCellYCoordinate(this), heightCount, widthCount)){
        if (cell.getPreviousCellState().equals(CellState.BURNING)){
          if (prob >= BURN_PROB){
            cellState = CellState.BURNING;
          }
          break;
        }
      }
    }
    else if (cellState.equals(CellState.BURNING)){
      cellState = CellState.EMPTY;
    }
  }

  /**
   * update cell when given the surrounding cells
   * @param surroundingCells the surrounding cells
   */

  // only for test
  public void updateCell(ArrayList<Cell> surroundingCells, double prob){
    if (cellState.equals(CellState.TREE)){
      for (Cell cell: surroundingCells){
        if (cell.getPreviousCellState().equals(CellState.BURNING)){
          if (prob >= BURN_PROB){
            cellState = CellState.BURNING;
          }

          break;
        }
      }
    }
    else if (cellState.equals(CellState.BURNING)){
      cellState = CellState.EMPTY;
    }
  }

  /**
   * check if the cell is at its default cell state
   * @return whether the cell is at its cell state
   */

  @Override
  public boolean ifDefault() {
    return cellState.equals(CellState.EMPTY);
  }

  /**
   * reset the cell to its default cell state
   */
  @Override
  public void resetCell() {
    this.cellState = CellState.EMPTY;
    this.previousCellState = CellState.EMPTY;
  }
}
