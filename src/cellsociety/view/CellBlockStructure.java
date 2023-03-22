package cellsociety.view;

import cellsociety.SimulationController.CellState;
import cellsociety.model.CellStateStructure;
import java.util.ArrayList;
import java.util.List;

/**
 * data structure of all the cell blocks
 */

public class CellBlockStructure {
  private List<List<CellBlock>> allCellBlock;

  public CellBlockStructure(List<List<CellBlock>> allCellBlock){
    this.allCellBlock = allCellBlock;
  }

  /**
   * get all the cell blocks
   * @return
   */
  public List<List<CellBlock>> getAllCellBlock() {
    return allCellBlock;
  }

  /**
   * set the cell blocks
   * @param allCellBlock the new cell blocks
   */

  public void setAllCellBlock(List<List<CellBlock>> allCellBlock) {
    this.allCellBlock = allCellBlock;
  }

  /**
   * check if there are cell's state changed from the frontend
   * @return whether there are block's state have been changed
   */

  public boolean cellStateUpdated(){
    for(List<CellBlock> cellBlocks : allCellBlock){
      for(CellBlock cellBlock : cellBlocks){
        if (cellBlock.isStateChanged()){
          return true;
        }
      }
    }
    return false;
  }

  /**
   * get all the cell state
   * @return all the cell state
   */

  public CellStateStructure getCellBlockState(){
    List<List<CellState>> allCellState = new ArrayList<>();
    for(List<CellBlock> cellBlocks: allCellBlock){
      List<CellState> cellStateList = new ArrayList<>();
      for (CellBlock cellBlock : cellBlocks){
        cellStateList.add(cellBlock.getCellState());
      }
      allCellState.add(cellStateList);
    }
    return new CellStateStructure(allCellState);
  }
}
