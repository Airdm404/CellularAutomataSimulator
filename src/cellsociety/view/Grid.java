package cellsociety.view;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.model.Cell;
import cellsociety.model.CellStateStructure;
import cellsociety.view.CellBlock;
import cellsociety.view.block.SquareCellBlock;
import cellsociety.view.block.TriangleCellBlock;
import com.sun.security.auth.NTSidUserPrincipal;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;

/**
 * class that holds all the cell blocks
 */

public class Grid{
  private CellBlockStructure allCellBlocks;
  private Group root;
  private int widthCount;
  private int heightCount;
  private String shapeType;

  public Grid(CellStateStructure initialLivingStatus, String shapeType, Group root)
      throws ClassOrMethodNotFoundException {
    this.shapeType = shapeType;
    this.widthCount = initialLivingStatus.getWidth();
    this.heightCount = initialLivingStatus.getHeight();
    this.root = root;
    setUpRoot(initialLivingStatus);
  }


  private void setUpRoot(CellStateStructure initialLivingStatus)
      throws ClassOrMethodNotFoundException {
    arrangeCellBlocks(initialLivingStatus);
  }

  /**
   * update all the cell blocks
   * @param currentLivingStatus the current states of the block
   * @throws ClassOrMethodNotFoundException
   */

  public void updateAllCellBlock(CellStateStructure currentLivingStatus)
      throws ClassOrMethodNotFoundException {
    root.getChildren().removeAll(allCellBlocks.getAllCellBlock());
    this.widthCount = currentLivingStatus.getCellStateStructure().get(0).size();
    this.heightCount = currentLivingStatus.getCellStateStructure().size();
    arrangeCellBlocks(currentLivingStatus);
  }

  /**
   * create cell block
   * @param shapeType shape type
   * @param cellState cell state
   * @param xCoordinate x coordinate
   * @param yCoordinate y coordinate
   * @return cell block
   * @throws ClassOrMethodNotFoundException
   */

  public static CellBlock createCellBlock(String shapeType, CellState cellState, int xCoordinate, int yCoordinate)
      throws ClassOrMethodNotFoundException {
    try{
      Class<?> cellBlock = Class.forName("cellsociety.view.block." + shapeType + "CellBlock");
      Class<?>[] param = {CellState.class, Integer.class, Integer.class};
      Constructor<?> cons = cellBlock.getConstructor(param);
      Object[] cellObject = {cellState, xCoordinate, yCoordinate};
      Object cellBlockOfType = cons.newInstance(cellObject);
      return (CellBlock) cellBlockOfType;
    }catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e){
      e.printStackTrace();
      throw new ClassOrMethodNotFoundException("class is not found");
    }
  }

  private void arrangeCellBlocks(CellStateStructure currentLivingStatus)
      throws ClassOrMethodNotFoundException {
    List<List<CellBlock>> cellBlockGrid = new ArrayList<>();
    for (int x = 0; x < heightCount; x++) {
      ArrayList<CellBlock> cellBlockLine = new ArrayList<>();
      for (int y = 0; y < widthCount; y++) {
        CellBlock cellBlock = createCellBlock(shapeType, currentLivingStatus.getCellStateStructure().get(x).get(y), y, x);
        cellBlockLine.add(cellBlock);
        root.getChildren().add(cellBlock.getBlockShape());
      }
      cellBlockGrid.add(cellBlockLine);
    }
    allCellBlocks = new CellBlockStructure(cellBlockGrid);
  }

  /**
   * checked if cell state has been updated
   * @return
   */

  public boolean isCellStateUpdated(){
    return allCellBlocks.cellStateUpdated();
  }

  /**
   * get all the blocks
   * @return all the cell blocks
   */

  public CellBlockStructure getAllCellBlocks() {
    return allCellBlocks;
  }
}
