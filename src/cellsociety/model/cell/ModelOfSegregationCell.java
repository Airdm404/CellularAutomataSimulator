package cellsociety.model.cell;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import java.util.List;

/**
 * model of segregation cell
 *
 * assumption:
 * to find choose the new location for dissatisfied cell, the code will find the first empty cell in all cells
 * and move the dissatisfied cell to there.
 */

public class ModelOfSegregationCell extends Cell {

  public static final double SATISFACTION_RATE = 0.3;

  public ModelOfSegregationCell(Integer cellConfig, String simulationType, String neighborType, String edgeType, String shapeType){
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
      case 0 -> this.cellState = CellState.NON_AGENT;
      case 1 -> this.cellState = CellState.AGENT_X;
      case 2 -> this.cellState = CellState.AGENT_O;
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
    List<Cell> surroundingCells = findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
        allCells.findCellYCoordinate(this), heightCount, widthCount);
    updateCell(allCells, surroundingCells);
  }

  /**
   * update cell when given the surrounding cells
   * @param surroundingCell the surrounding cells
   * @param allCells all the cells
   */

  public void updateCell(CellStructure allCells, List<Cell> surroundingCell) {
    int satisfiedCellNum = 0;
    if (!previousCellState.equals(CellState.NON_AGENT)){
      for(Cell cell: surroundingCell){
        if (cell.getCellState().equals(previousCellState)){
          satisfiedCellNum += 1;
        }
      }
      if ((float) satisfiedCellNum / surroundingCell.size() <= SATISFACTION_RATE){
        putCellToNonAgentPosition(allCells);
      }
    }
  }

  private void putCellToNonAgentPosition(CellStructure allCells){
    boolean newPositionFound = false;
    for (List<Cell> cellLine: allCells.getAllCells()){
      for (Cell cell: cellLine){
        if (cell.getCellState().equals(CellState.NON_AGENT)){
          cell.setCellState(cellState);
          newPositionFound = true;
          break;
        }
      }
      if (newPositionFound){
        break;
      }
    }
    cellState = CellState.NON_AGENT;
  }

  /**
   * check if the cell is at its default cell state
   * @return whether the cell is at its cell state
   */

  @Override
  public boolean ifDefault() {
    return cellState.equals(CellState.NON_AGENT);
  }

  /**
   * reset the cell to its default cell state
   */
  @Override
  public void resetCell() {
    this.cellState = CellState.NON_AGENT;
    this.previousCellState = CellState.NON_AGENT;
  }
}
