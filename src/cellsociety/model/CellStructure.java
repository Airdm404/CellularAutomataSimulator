package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *  data structure of all the cell
 *
 * assumptions: if the cell is not found in this cell structure, both x and y coordinate will return -1;
 */

public class CellStructure {
  private List<List<Cell>> allCells;

  public CellStructure(List<List<Cell>> allCells){
    this.allCells = allCells;
  }

  /**
   * get all the cells
   * @return all the cells
   */
  public List<List<Cell>> getAllCells(){
    return allCells;
  }

  /**
   * set all the cells
   * @param allCells the new cells
   */

  public void setAllCells(List<List<Cell>> allCells) {
    this.allCells = allCells;
  }

  /**
   * find x coordinate of a cell from the grid
   * @param cell the cell
   * @return the x coordinate
   */

  public int findCellXCoordinate(Cell cell){
    for(int i = 0; i < allCells.size(); i++){
      if (allCells.get(i).contains(cell)){
        return i;
      }
    }
    return -1;
  }

  /**
   * find y coordinate of a cell from the grid
   * @param cell the cell
   * @return the y coordinate
   */

  public int findCellYCoordinate(Cell cell){
    int xCoordinate = findCellXCoordinate(cell);
    if (xCoordinate != -1) {
      for(int i = 0; i < allCells.get(xCoordinate).size(); i++){
        if (allCells.get(xCoordinate).get(i).equals(cell)){
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * height of the data structure
   * @return the height
   */

  public int getHeight(){
    return allCells.size();
  }

  /**
   * width of the data structure
   * @return the width
   */

  public int getWidth(){
    return allCells.get(0).size();
  }
}
