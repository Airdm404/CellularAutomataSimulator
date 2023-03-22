package cellsociety;

import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.AllCells;
import cellsociety.model.SimulationCells;
import cellsociety.view.Grid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;

/**
 * controller in the MVC model
 */

public class SimulationController {

  public static final List<List<CellState>> CELL_STATE_GROUP = new ArrayList<>(){{
    add(List.of(CellState.LIVING, CellState.DEAD));
    add(List.of(CellState.BLOCK, CellState.OPEN, CellState.PERCOLATED));
    add(List.of(CellState.ROCK, CellState.PAPER, CellState.SCISSOR));
    add(List.of(CellState.TREE, CellState.EMPTY, CellState.BURNING));
    add(List.of(CellState.NON_AGENT, CellState.AGENT_O, CellState.AGENT_X));
    add(List.of(CellState.SHARK, CellState.FISH, CellState.UNOCCUPIED));
  }};

  private final AllCells allCells;
  private final Grid grid;
  private final Group myRoot;

  public enum CellState {
    //
    DEFAULT,
    // game of life
    LIVING,
    DEAD,
    // percolation
    BLOCK,
    OPEN,
    PERCOLATED,
    // rock, paper and scissors
    ROCK,
    PAPER,
    SCISSOR,
    // spreading of fire
    TREE,
    EMPTY,
    BURNING,
    // model of segregation
    NON_AGENT,
    AGENT_X,
    AGENT_O,
    // war-to world
    SHARK,
    FISH,
    UNOCCUPIED
  }

  public SimulationController(String simulationType, String neighborType, String initialPattern,
                              String edgeType, String shapeType, Group root)
          throws CSVDimensionsException, IOException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    this.myRoot = root;
    allCells = new SimulationCells(simulationType, neighborType, initialPattern, edgeType, shapeType);
    grid = new Grid(allCells.getAllCellState(),shapeType, myRoot);
  }

  /**
   * update the cell and the cell blocks in each iteration
   * @throws ClassOrMethodNotFoundException
   */
  public void updateBothCellAndCellBlocks() throws ClassOrMethodNotFoundException {
    if(grid.isCellStateUpdated()){
      allCells.updateAllCellState(grid.getAllCellBlocks().getCellBlockState());
    }

    allCells.updateAllCells();
    grid.updateAllCellBlock(allCells.getAllCellState());
  }


}
