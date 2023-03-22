package cellsociety.model;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * all the methods that relates to finding neighbors
 */

public class NeighborFinder {
  public static final int DEFAULT_STATE_CONFIG = 0;

  private ResourceBundle resourceBundle;
  private static final String NEIGHBOR_CONFIGURATION =
      Cell.class.getPackageName() + ".resources.";
  private static final String NEIGHBOR_FILE_NAME = "NeighborConfiguration";
  private final String simulationType;
  private final String neighborType;
  private final String edgeType;
  private final String shapeType;

  public NeighborFinder(String simulationType ,String neighborType, String edgeType, String shapeType){
    this.simulationType = simulationType;
    this.neighborType = neighborType;
    this.edgeType = edgeType;
    this.shapeType = shapeType;
  }

  private String findResourcesBundleName(int x, int y){
    if (shapeType.equals("Triangle")){
      if (x % 2 == 0){
        if ((x/2) % 2 == 0){
          return shapeType + "UpEven" + NEIGHBOR_FILE_NAME;
        }
        else{
          return shapeType + "UpOdd" + NEIGHBOR_FILE_NAME;
        }
      }
      else{
        if (((x-1)/2) % 2 == 0){
          return shapeType + "DownEven" + NEIGHBOR_FILE_NAME;
        }
        else{
          return shapeType + "DownOdd" + NEIGHBOR_FILE_NAME;
        }
      }
    }
    if(shapeType.equals("Hexagon")){
      if(x % 2 == 0){
        return shapeType + "Even" + NEIGHBOR_FILE_NAME;
      }
      else{
        return shapeType + "Odd" + NEIGHBOR_FILE_NAME;
      }
    }
    return shapeType + NEIGHBOR_FILE_NAME;
  }

  protected ArrayList<Vector<Integer>> loadNeighbor(int x, int y){
    String resourcesFile =
        NEIGHBOR_CONFIGURATION.replaceAll("\\.", "/") + findResourcesBundleName(x, y);
    this.resourceBundle = ResourceBundle.getBundle(resourcesFile);
    String neighborConfig = resourceBundle.getString(neighborType);
    ArrayList<String> neighborCells = new ArrayList<>(
        Arrays.asList(neighborConfig.split("/")));
    ArrayList<Vector<Integer>> allNeighbors = new ArrayList<>();
    for (String cell: neighborCells){
      String[] cellCoordinate = cell.split(",");
      allNeighbors.add(new Vector<>(){{
        add(Integer.parseInt(cellCoordinate[0]));
        add(Integer.parseInt(cellCoordinate[1]));
      }});
    }
    return allNeighbors;
  }

  private List<Cell> findNeighborFinite (CellStructure allCells, int x, int y, int heightCount, int widthCount){
    List<Cell> surroundingCell = new ArrayList<>();

    ArrayList<Vector<Integer>> allNeighborConfig = loadNeighbor(x, y);
    for (Vector<Integer> neighborConfig: allNeighborConfig){
      int xMove = neighborConfig.get(0);
      int yMove = neighborConfig.get(1);

      if (x + xMove >= 0 && x + xMove < heightCount && y + yMove >= 0 && y + yMove < widthCount){
        surroundingCell.add(allCells.getAllCells().get(x + xMove).get(y + yMove));
      }
    }

    return surroundingCell;
  }

  private List<Cell> findNeighborToroidal (CellStructure allCells, int x, int y, int heightCount, int widthCount){
    List<Cell> surroundingCell = new ArrayList<>();

    ArrayList<Vector<Integer>> allNeighborConfig = loadNeighbor(x, y);
    for (Vector<Integer> neighborConfig: allNeighborConfig) {
      int xMove = neighborConfig.get(0);
      int yMove = neighborConfig.get(1);
      if (x + xMove >= 0 && x + xMove < heightCount && y + yMove >= 0 && y + yMove < widthCount){
        surroundingCell.add(allCells.getAllCells().get(x + xMove).get(y + yMove));
      }
      else if (x + xMove < 0 && y + yMove >= 0 && y + yMove < widthCount){
        surroundingCell.add(allCells.getAllCells().get(heightCount - 1).get(y));
      }
      else if (x + xMove >= heightCount && y + yMove >= 0 && y + yMove < widthCount){
        surroundingCell.add(allCells.getAllCells().get(0).get(y));
      }
      else if (x + xMove >= 0 && x + xMove < heightCount && y + yMove < 0 ){
        surroundingCell.add(allCells.getAllCells().get(x).get(widthCount - 1));
      }
      else if (x + xMove >= 0 && x + xMove < heightCount && y + yMove >= widthCount){
        surroundingCell.add(allCells.getAllCells().get(x).get(0));
      }
      else if (x + xMove < 0 && y + yMove < 0){
        surroundingCell.add(allCells.getAllCells().get(heightCount - 1).get(widthCount - 1));
      }
      else if (x + xMove < 0 && y + yMove >= widthCount ){
        surroundingCell.add(allCells.getAllCells().get(heightCount - 1).get(0));
      }
      else if (x + xMove >= heightCount && y + yMove < 0){
        surroundingCell.add(allCells.getAllCells().get(0).get(widthCount - 1));
      }
      else if (x + xMove >= heightCount && y + yMove >= widthCount){
        surroundingCell.add(allCells.getAllCells().get(0).get(0));
      }
    }

    return surroundingCell;
  }

  private List<Cell> findNeighborUnbounded(CellStructure allCells, int x, int y, int heightCount, int widthCount)
      throws ClassOrMethodNotFoundException {
    List<Cell> surroundingCell = new ArrayList<>();

    ArrayList<Vector<Integer>> allNeighborConfig = loadNeighbor(x, y);
    for (Vector<Integer> neighborConfig: allNeighborConfig) {
      int xMove = neighborConfig.get(0);
      int yMove = neighborConfig.get(1);
      if (x + xMove >= 0 && x + xMove < heightCount && y + yMove >= 0 && y + yMove < widthCount){
        surroundingCell.add(allCells.getAllCells().get(x + xMove).get(y + yMove));
      }
      if (x + xMove < 0 && !centerCellIsDefault(allCells, x, y)){
        ArrayList<Cell> newCellRow = new ArrayList<>();
        for (int i = 0; i < allCells.getWidth(); i++){
          newCellRow.add(SimulationCells
              .createCell(DEFAULT_STATE_CONFIG, simulationType, neighborType, edgeType, shapeType));
        }
        allCells.getAllCells().add(0, newCellRow);
      }
      if (x + xMove >= heightCount && !centerCellIsDefault(allCells, x, y)){
        ArrayList<Cell> newCellRow = new ArrayList<>();
        for (int i = 0; i < allCells.getWidth(); i++){
          newCellRow.add(SimulationCells
              .createCell(DEFAULT_STATE_CONFIG, simulationType, neighborType, edgeType, shapeType));
        }
        allCells.getAllCells().add(newCellRow);
      }
      if (y + yMove < 0 && !centerCellIsDefault(allCells, x, y)){
        for (List<Cell> cellRow: allCells.getAllCells()){
          cellRow.add(0, SimulationCells
              .createCell(DEFAULT_STATE_CONFIG, simulationType, neighborType, edgeType, shapeType));
        }
      }
      if (y + yMove >= widthCount && !centerCellIsDefault(allCells, x, y)){
        for (List<Cell> cellRow: allCells.getAllCells()){
          cellRow.add(SimulationCells
              .createCell(DEFAULT_STATE_CONFIG, simulationType, neighborType, edgeType, shapeType));
        }
      }

    }
    return surroundingCell;
  }

  private boolean centerCellIsDefault(CellStructure allCell, int x, int y){
    Cell centerCell = allCell.getAllCells().get(x).get(y);
    return centerCell.ifDefault();
  }
}
