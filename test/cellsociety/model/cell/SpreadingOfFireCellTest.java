package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import cellsociety.model.Cell;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SpreadingOfFireCellTest{

  private String simulationType = "SpreadingOfFire";
  private String neighborType = "AllFirstLayerNeighbor";
  private String edgeType = "Finite";
  private final String shapeType = "Square";
  private final CellState t = CellState.TREE;
  private final CellState e = CellState.EMPTY;
  private final CellState b = CellState.BURNING;

  @Test
  public void checkForSpreadingOfFirePatternTest1()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells("SpreadingOfFire", neighborType,
            "Test1", edgeType, shapeType);
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(e,e,e,e,e)),
                    new ArrayList<>(List.of(e,b,b,b,e)),
                    new ArrayList<>(List.of(e,b,e,b,e)),
                    new ArrayList<>(List.of(e,b,b,b,e)),
                    new ArrayList<>(List.of(e,e,e,e,e))
            ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForSpreadingOfFirePatternTest2()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells("SpreadingOfFire", neighborType,
            "Test2", edgeType, shapeType);
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(t,t,t,t,t)),
                    new ArrayList<>(List.of(t,e,t,e,b)),
                    new ArrayList<>(List.of(t,t,t,b,e)),
                    new ArrayList<>(List.of(t,e,t,e,b)),
                    new ArrayList<>(List.of(t,t,t,t,t))
            ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }


  @Test
  public void checkForSpreadingOfFirePatternTest3()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells("SpreadingOfFire", neighborType,
            "Test3", edgeType, shapeType);
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(t,t,t,t,t)),
                    new ArrayList<>(List.of(e,e,e,e,e)),
                    new ArrayList<>(List.of(t,t,t,t,t)),
                    new ArrayList<>(List.of(e,e,e,e,e)),
                    new ArrayList<>(List.of(t,t,t,b,e))
            ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }


  @Test
  public void checkIfTreeGotBurntWhenProbLagerThanThreshold(){
    SpreadingOfFireCell treeCell = new SpreadingOfFireCell(1, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 4) {
        surroundingCells.add(new SpreadingOfFireCell(1, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new SpreadingOfFireCell(2, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new SpreadingOfFireCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    treeCell.updateCell(surroundingCells, SpreadingOfFireCell.BURN_PROB + 0.1);
    assertEquals(CellState.BURNING, treeCell.getCellState());
  }

  @Test
  public void checkIfTreeWontGotBurntWhenProbSmallerThanThreshold(){
    SpreadingOfFireCell treeCell = new SpreadingOfFireCell(1, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 4) {
        surroundingCells.add(new SpreadingOfFireCell(1, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new SpreadingOfFireCell(2, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new SpreadingOfFireCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    treeCell.updateCell(surroundingCells, SpreadingOfFireCell.BURN_PROB - 0.1);
    assertEquals(CellState.TREE, treeCell.getCellState());
  }

  @Test
  public void checkIfBurningCellTurIntoEmptyAfterOneUpdate(){
    SpreadingOfFireCell treeCell = new SpreadingOfFireCell(2, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 4) {
        surroundingCells.add(new SpreadingOfFireCell(1, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new SpreadingOfFireCell(2, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new SpreadingOfFireCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    treeCell.updateCell(surroundingCells, SpreadingOfFireCell.BURN_PROB);
    assertEquals(CellState.EMPTY, treeCell.getCellState());
  }
}
