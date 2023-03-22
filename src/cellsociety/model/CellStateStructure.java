package cellsociety.model;

import cellsociety.SimulationController.CellState;
import java.util.List;

/**
 * data structure of all the cell state
 */

public class CellStateStructure {
  private List<List<CellState>> cellStateStructure;

  public CellStateStructure(List<List<CellState>> cellStateStructure){
    this.cellStateStructure = cellStateStructure;
  }

  /**
   * get cell state structure
   * @return cell state structure
   */
  public List<List<CellState>> getCellStateStructure() {
    return cellStateStructure;
  }

  /**
   * set the cell state structure
   * @param cellStateStructure the new cell state structure
   */

  public void setCellStateStructure(
      List<List<CellState>> cellStateStructure) {
    this.cellStateStructure = cellStateStructure;
  }

  /**
   * get the height of the data structure
   * @return height of the data structure
   */

  public int getHeight(){
    return cellStateStructure.size();
  }

  /**
   * get the width of the data structure
   * @return width of the data structure
   */

  public int getWidth(){
    return cellStateStructure.get(0).size();
  }
}
