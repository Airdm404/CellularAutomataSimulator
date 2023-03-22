package cellsociety.view;


import cellsociety.SimulationController;
import cellsociety.SimulationController.CellState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * abstract class for all the specific cell blocks
 */

public abstract class CellBlock{
  final public static Map<CellState, Paint> CELL_COLOR = new HashMap<>(){{
    put(CellState.LIVING, Color.DODGERBLUE);
    put(CellState.DEAD, Color.AZURE);
    put(CellState.OPEN, Color.WHITE);
    put(CellState.PERCOLATED, Color.BLUEVIOLET);
    put(CellState.BLOCK, Color.DARKGRAY);
    put(CellState.ROCK, Color.RED);
    put(CellState.PAPER, Color.YELLOW);
    put(CellState.SCISSOR, Color.LIGHTBLUE);
    put(CellState.EMPTY, Color.YELLOW);
    put(CellState.BURNING, Color.DARKRED);
    put(CellState.TREE, Color.GREEN);
    put(CellState.AGENT_O, Color.RED);
    put(CellState.AGENT_X, Color.BLUE);
    put(CellState.NON_AGENT, Color.WHITE);
    put(CellState.SHARK, Color.BLUE);
    put(CellState.FISH, Color.GREEN);
    put(CellState.UNOCCUPIED, Color.LIGHTGRAY);
  }};

  protected Polygon blockShape;
  protected Integer xCoordinate;
  protected Integer yCoordinate;
  protected CellState cellState;

  private boolean isStateChanged = false;

  public CellBlock(CellState cellState, Integer xCoordinate, Integer yCoordinate){
    this.cellState = cellState;
    blockShape = new Polygon();
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    blockShape.setOnMouseClicked(mouseEvent -> changeState());
  }

  protected abstract void drawShape();

  protected void setColor(CellState cellState){
    blockShape.setFill(CELL_COLOR.get(cellState));
  }

  /**
   * get the block shape
   * @return the block shape
   */
  public Polygon getBlockShape(){
    return blockShape;
  }

  public void changeState(){
    for(List<CellState> allCellState : SimulationController.CELL_STATE_GROUP){
      if(allCellState.contains(cellState)){
        int index = allCellState.indexOf(cellState);
        if (index == allCellState.size() - 1){
          cellState = allCellState.get(0);
        }
        else{
          cellState = allCellState.get(index + 1);
        }
      }
    }
    isStateChanged = true;
    setColor(cellState);
  }

  /**
   * get the cell state
   * @return the cell state
   */
  public CellState getCellState() {
    return cellState;
  }

  /**
   * check if the state of an block has been changed
   * @return whether of state of the block has been changed
   */
  public boolean isStateChanged() {
    return isStateChanged;
  }
}
