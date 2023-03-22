package cellsociety.model;

import cellsociety.CellConfig;
import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * all the cells in a simulation
 */

public class SimulationCells implements AllCells {

  private final String simulationType;
  private CellStructure cellStructure;
  //TODO: encapsulate ArrayList<ArrayList<Integer>> data structure.
  private ArrayList<ArrayList<Integer>> initialLivingStatus;
  private int widthCount;
  private int heightCount;
  private final String neighborType;
  private final String initialPattern;
  private final String edgeType;
  private final String shapeType;
  private CellConfig config;

  public SimulationCells(String simulationType, String neighborType, String initialPattern,
      String edgeType, String shapeType) throws CSVDimensionsException, IOException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    this.simulationType = simulationType;
    this.neighborType = neighborType;
    this.initialPattern = initialPattern;
    this.edgeType = edgeType;
    this.shapeType = shapeType;
    loadInitialLivingStatus();
    createAllCells();
    this.heightCount = cellStructure.getHeight();
    this.widthCount = cellStructure.getWidth();
  }

  private void loadInitialLivingStatus() throws CSVDimensionsException, IOException, InvalidCellStateGivenException {
    CellConfig config = new CellConfig(simulationType + "/" + simulationType + initialPattern);
    initialLivingStatus = config
        .readFile(simulationType + "/" + simulationType + initialPattern);
  }

  /**
   * create a cell
   * @param cellState cell configuration
   * @param simulationType simulation type
   * @param neighborType neighbor type
   * @param edgeType edge type
   * @param shapeType shape location type
   * @return cell
   * @throws ClassOrMethodNotFoundException
   */

  public static Cell createCell(int cellState, String simulationType, String neighborType, String edgeType, String shapeType)
      throws ClassOrMethodNotFoundException{
    try {
      Class<?> cell = Class.forName("cellsociety.model.cell." + simulationType + "Cell");
      Class<?>[] param = {Integer.class, String.class, String.class, String.class, String.class};
      Constructor<?> cons = cell.getConstructor(param);
      Object[] cellObject = {cellState, simulationType, neighborType, edgeType, shapeType};
      Object cellOfType = cons.newInstance(cellObject);
      return (Cell) cellOfType;
    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ClassOrMethodNotFoundException("class is not found");
    }
  }

  /**
   * create all the cells
   * @throws ClassOrMethodNotFoundException
   */

  public void createAllCells() throws ClassOrMethodNotFoundException {
    List<List<Cell>> allCells = new ArrayList<>();
    for (int x = 0; x < initialLivingStatus.size(); x++) {
      ArrayList<Cell> cellLine = new ArrayList<>();
      for (int y = 0; y < initialLivingStatus.get(0).size(); y++) {
        cellLine
            .add(createCell(initialLivingStatus.get(x).get(y), simulationType, neighborType, edgeType, shapeType));
      }
      allCells.add(cellLine);
    }
    cellStructure = new CellStructure(allCells);
  }

  /**
   * updates all the cells
   * @throws ClassOrMethodNotFoundException
   */


  public void updateAllCells() throws ClassOrMethodNotFoundException {
    for (int x = 0; x < heightCount; x++) {
      for (int y = 0; y < widthCount; y++) {
        Cell cell = cellStructure.getAllCells().get(x).get(y);
        cell.updateCell(cellStructure, heightCount, widthCount);
        // for unbounded edges
        updateWidthAndHeightCount();
      }
    }
    unifyAllCellCurrentAndPreviousState();
  }

  /**
   * update all the cells with given probability (for test)
   * @param prob given probability
   * @throws ClassOrMethodNotFoundException
   */

  public void updateAllCellTest(double prob) throws ClassOrMethodNotFoundException {
    for (int x = 0; x < heightCount; x++) {
      for (int y = 0; y < widthCount; y++) {
        Cell cell = cellStructure.getAllCells().get(x).get(y);
        cell.updateCell(cellStructure, heightCount, widthCount, prob);
        // for unbounded edges
        updateWidthAndHeightCount();
      }
    }
    unifyAllCellCurrentAndPreviousState();
  }

  private void unifyAllCellCurrentAndPreviousState() {
    for (List<Cell> cellLine : cellStructure.getAllCells()) {
      for (Cell cell : cellLine) {
        cell.unifyPreviousAndCurrentCellState();
      }
    }
  }

  private void updateWidthAndHeightCount(){
    this.heightCount = cellStructure.getHeight();
    this.widthCount = cellStructure.getWidth();
  }

  /**
   * get all the cell state
   * @return all the cell state
   */

  public CellStateStructure getAllCellState() {
    List<List<CellState>> allCellStatus = new ArrayList<>();
    for (List<Cell> cellLine : cellStructure.getAllCells()) {
      ArrayList<CellState> cellLineStatus = new ArrayList<>();
      for (Cell cell : cellLine) {
        cellLineStatus.add(cell.getCellState());
      }
      allCellStatus.add(cellLineStatus);
    }
    return new CellStateStructure(allCellStatus);
  }

  /**
   * get the width count
   * @return width count
   */

  public int getWidthCount() {
    return widthCount;
  }

  /**
   * get the height count
   * @return height count
   */

  public int getHeightCount() {
    return heightCount;
  }

  /**
   * update all the cell state
   * @param cellStateStructure new cell states
   */

  @Override
  public void updateAllCellState(CellStateStructure cellStateStructure) {
    for(List<Cell> cellLine: cellStructure.getAllCells()){
      for(Cell cell : cellLine){
        int x = cellStructure.findCellXCoordinate(cell);
        int y = cellStructure.findCellYCoordinate(cell);
        cell.setCellState(cellStateStructure.getCellStateStructure().get(x).get(y));
      }
    }
  }
}
