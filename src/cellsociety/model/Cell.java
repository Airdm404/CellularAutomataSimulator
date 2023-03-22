package cellsociety.model;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * abstract class for cells
 */

public abstract class Cell {
  protected CellState cellState;
  protected CellState previousCellState;
  protected int survivedCount;
  protected int energy;
  protected String neighborType;
  protected String edgeType;
  protected String simulationType;
  protected String shapeType;

  public Cell(Integer cellConfig, String simulationType, String neighborType, String edgeType,
      String shapeType){
    initiateCellState(cellConfig);
    survivedCount = 0;
    energy = 0;
    this.simulationType = simulationType;
    this.neighborType = neighborType;
    this.edgeType = edgeType;
    this.shapeType = shapeType;
  }

  /**
   * update cells
   * @param allCells all the cells
   * @param heightCount height of the grid
   * @param widthCount width of the grid
   * @throws ClassOrMethodNotFoundException
   */

  public abstract void updateCell(CellStructure allCells, int heightCount, int widthCount)
      throws ClassOrMethodNotFoundException;

  /**
   * reset the cell to default state
   */
  public abstract void resetCell();

  /**
   * check if the cell is at its default state
   * @return whether the cell is at its default state
   */
  public abstract boolean ifDefault();

  /**
   * initiate cell state
   * @param cellConfig cell configuration
   */
  public void initiateCellState(Integer cellConfig){
    this.cellState = CellState.DEFAULT;
    this.previousCellState = CellState.DEFAULT;
  }

  /**
   * get cell state
   * @return current cell state
   */

  public CellState getCellState(){
    return cellState;
  }

  /**
   * get previous cell state
   * @return previous cell state
   */

  public CellState getPreviousCellState(){
    return previousCellState;
  }

  /**
   * set the current cell state
   * @param cellState current cell state
   */

  public void setCellState(CellState cellState){
    this.cellState = cellState;
  }

  /**
   * unify the previous cell state and the current cell state
   */

  public void unifyPreviousAndCurrentCellState(){
    previousCellState = cellState;
  }

  /**
   * get the survival count
   * @return the survival count
   */

  public int getSurvivedCount(){
    return survivedCount;
  }

  /**
   * set the survival count
   * @param survivedCount the new survival count
   */

  public void setSurvivedCount(int survivedCount){
    this.survivedCount = survivedCount;
  }

  /**
   * get the current energy
   * @return current energy
   */
  public int getEnergy(){
    return energy;
  }

  /**
   * set the current energy
   * @param energy the new energy
   */

  public void setEnergy(int energy){
    this.energy = energy;
  }

  /**
   * find the surrounding cells
   * @param allCells all the cells
   * @param x x coordinate
   * @param y y coordinate
   * @param heightCount height count of the grid
   * @param widthCount width count of the grid
   * @return list of neighboring cells
   * @throws ClassOrMethodNotFoundException
   */

  protected List<Cell> findSurroundingCells(CellStructure allCells, int x, int y, int heightCount, int widthCount)
      throws ClassOrMethodNotFoundException {
    Class<?> neighborFinder;
    try {
      neighborFinder = Class.forName("cellsociety.model.NeighborFinder");
    }
    catch (ClassNotFoundException e){
      throw new ClassOrMethodNotFoundException("class is not found");
    }
    NeighborFinder neighbor = new NeighborFinder(simulationType, neighborType, edgeType, shapeType);
    Method method = null;
    try{
      method = neighborFinder.getDeclaredMethod("findNeighbor" + edgeType, CellStructure.class,
          int.class, int.class, int.class, int.class);
      method.setAccessible(true);
    }
    catch (NoSuchMethodException | SecurityException e){
      throw new ClassOrMethodNotFoundException("method is not found");
    }
    List<Cell> neighborCell;
    try{
      neighborCell = (List<Cell>) method.invoke(neighbor, allCells, x, y, heightCount, widthCount);
    }
    catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
      throw new ClassOrMethodNotFoundException("result is not found");
    }
    return neighborCell;
  }

  /**
   * uodate cell when given a set probability
   * @param allCells all the cells
   * @param heightCount height count of the grid
   * @param widthCount width count of the grid
   * @param prob a given probability
   * @throws ClassOrMethodNotFoundException
   */
  //only for SpreadingOfFireCell test
  public void updateCell(CellStructure allCells, int heightCount, int widthCount, double prob)
      throws ClassOrMethodNotFoundException {}
}
