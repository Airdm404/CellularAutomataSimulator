package cellsociety.model.cell;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import java.util.ArrayList;
import java.util.List;

public class WaTorWorldCell extends Cell {
  public static final int REPRODUCTION_COUNT = 3;
  public static final int ENERGY_BY_ONE_FISH = 2;
  public static final int DEFAULT_ENERGY = 5;
  public static final int ENERGY_CONSUMED_EACH_CIRCLE = 1;

  public WaTorWorldCell(Integer cellConfig, String simulationType, String neighborType, String edgeType, String shapeType){
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
      case 1 -> {
        cellState = CellState.FISH;
        survivedCount = 1;
        energy = 0;
      }
      case 2 -> {
        cellState = CellState.SHARK;
        survivedCount = 1;
        energy = DEFAULT_ENERGY;
      }
      default -> {
        cellState = CellState.UNOCCUPIED;
        survivedCount = 0;
        energy = 0;
      }
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
  public void updateCell(CellStructure allCells,  int heightCount, int widthCount)
      throws ClassOrMethodNotFoundException {
    List<Cell> surroundingCells = findSurroundingCells(allCells, allCells.findCellXCoordinate(this),
        allCells.findCellYCoordinate(this), heightCount, widthCount);
    //if the cell is a fish
    updateCellAccordingToNeighbor(surroundingCells);
  }

  /**
   * update cell when given the surrounding cells
   * @param surroundingCells the surrounding cells
   */

  private void updateCellAccordingToNeighbor(List<Cell> surroundingCells) {
    // if the cell is a fish
    if (previousCellState.equals(CellState.FISH) && previousCellState.equals(cellState)){
      survivedCount += 1;
      for (Cell cell: surroundingCells){
        //fish randomly move to an unoccupied adjacent cell
        if (cell.getCellState().equals(CellState.UNOCCUPIED)){
          fishMoveToUnoccupiedCellAndReproduce(cell);
          break;
        }
      }
    }
    //if the cell is a shark
    else if (previousCellState.equals(CellState.SHARK) && previousCellState.equals(cellState)){
      boolean eatenFish = false;
      for (Cell cell: surroundingCells){
        //the shark will eat the fish if there if a fish is around
        if (cell.getCellState().equals(CellState.FISH)){
          eatenFish = true;
          sharkEatFish(cell);
          break;
        }
      }
      //if there are not fish around, the shark moves to a unoccupied cell
      if (!eatenFish) {
        energy -= ENERGY_CONSUMED_EACH_CIRCLE;
        //if the energy reach 0, the shark dies
        if (energy <= 0) {
          sharkDie();
        }
        else{
         survivedCount += 1;
          for (Cell cell: surroundingCells){
            if (cell.getCellState().equals(CellState.UNOCCUPIED)){
              sharkMoveToUnoccupiedCellAndReproduce(cell);
              break;
            }
          }
        }
      }
    }
  }

  public void updateCell(ArrayList<Cell> surroundingCells){
    updateCellAccordingToNeighbor(surroundingCells);
  }


  private void fishMoveToUnoccupiedCellAndReproduce(Cell cell){
    cell.setCellState(CellState.FISH);
    cell.setSurvivedCount(survivedCount);
    //if the fish survived for a certain amount of time, it reproduce a fish
    if (survivedCount % REPRODUCTION_COUNT == 0) {
      survivedCount = 1;
    }
    else {
      cellState = CellState.UNOCCUPIED;
      survivedCount = 0;
    }
  }

  private void sharkMoveToUnoccupiedCellAndReproduce(Cell cell){
    cell.setEnergy(energy);
    energy = 0;
    cell.setCellState(CellState.SHARK);
    cell.setSurvivedCount(survivedCount);
    //if the shark survived for a certain amount of time, it reproduce a fish
    if (survivedCount % REPRODUCTION_COUNT == 0){
      survivedCount = 1;
      energy = DEFAULT_ENERGY;
    }
    else {
      cellState = CellState.UNOCCUPIED;
      survivedCount = 0;
      energy = 0;
    }
  }

  private void sharkEatFish(Cell cell){
    cell.setCellState(CellState.SHARK);
    cell.setSurvivedCount(survivedCount + 1);
    // energy consumed by each circle and gained through each round
    energy += ENERGY_BY_ONE_FISH - ENERGY_CONSUMED_EACH_CIRCLE;
    cell.setEnergy(energy);
    if (survivedCount % REPRODUCTION_COUNT == 0){
      survivedCount = 1;
      energy = DEFAULT_ENERGY;
    }
    else{
      cellState = CellState.UNOCCUPIED;
      survivedCount = 0;
      energy = 0;
    }

  }

  private void sharkDie(){
    cellState = CellState.UNOCCUPIED;
    survivedCount = 0;
  }

  /**
   * check if the cell is at its default cell state
   * @return whether the cell is at its cell state
   */

  @Override
  public boolean ifDefault() {
    return cellState.equals(CellState.UNOCCUPIED);
  }

  /**
   * reset the cell to its default cell state
   */
  @Override
  public void resetCell() {
    this.cellState = CellState.UNOCCUPIED;
    this.previousCellState = CellState.UNOCCUPIED;
  }
}
