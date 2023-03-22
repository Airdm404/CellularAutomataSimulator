package cellsociety.model.cell;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RockPaperScissorCell extends Cell {

  final public static int THRESHOLD = 3;

  public RockPaperScissorCell(Integer cellConfig,String simulationType, String neighborType, String edgeType, String shapeType) {
    super(cellConfig, simulationType, neighborType, edgeType, shapeType);
    initiateCellState(cellConfig);
  }

  /**
   * initiate cell state according to the cell configuration
   * @param cellConfig the cell configuration
   */
  @Override
  public void initiateCellState(Integer cellConfig) {
    switch (cellConfig) {
      case 0 -> this.cellState = CellState.ROCK;
      case 1 -> this.cellState = CellState.PAPER;
      case 2 -> this.cellState = CellState.SCISSOR;
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
    Map<CellState, Integer> cellCount = new HashMap<>() {{
      put(CellState.ROCK, 0);
      put(CellState.PAPER, 0);
      put(CellState.SCISSOR, 0);
    }};
    for (Cell cell : findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
        allCells.findCellYCoordinate(this), heightCount,
        widthCount)) {
      cellCount.put(cell.getPreviousCellState(), cellCount.get(cell.getPreviousCellState()) + 1);
    }
    updateCellAccordingToNeighbor(cellCount.get(CellState.ROCK), cellCount.get(CellState.PAPER),
        cellCount.get(CellState.SCISSOR));
  }

  //only for test
  public void updateCell(ArrayList<Cell> surroundingCell) {
    Map<CellState, Integer> cellCount = new HashMap<>() {{
      put(CellState.ROCK, 0);
      put(CellState.PAPER, 0);
      put(CellState.SCISSOR, 0);
    }};
    for (Cell cell : surroundingCell) {
      System.out.println(cell.getPreviousCellState());
      cellCount.put(cell.getPreviousCellState(), cellCount.get(cell.getPreviousCellState()) + 1);
    }
    updateCellAccordingToNeighbor(cellCount.get(CellState.ROCK), cellCount.get(CellState.PAPER),
        cellCount.get(CellState.SCISSOR));
  }

  private void updateCellAccordingToNeighbor(int rockCount, int paperCount, int scissorCount) {
    if (previousCellState.equals(CellState.ROCK)) {
      if (paperCount >= THRESHOLD) {
        cellState = CellState.PAPER;
      }
    } else if (previousCellState.equals(CellState.PAPER)) {
      if (scissorCount >= THRESHOLD) {
        cellState = CellState.SCISSOR;
      }
    } else if (previousCellState.equals(CellState.SCISSOR)) {
      if (rockCount >= THRESHOLD) {
        cellState = CellState.ROCK;
      }
    }
  }

  /**
   * check if the cell is at its default cell state
   * @return whether the cell is at its cell state
   */

  @Override
  public boolean ifDefault() {
    return cellState.equals(CellState.ROCK);
  }

  /**
   * reset the cell to its default cell state
   */
  @Override
  public void resetCell() {
    this.cellState = CellState.ROCK;
    this.previousCellState = CellState.ROCK;
  }
}
