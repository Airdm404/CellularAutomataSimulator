package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SpreadingOfFireCellVariationTest {

  private final CellState t = CellState.TREE;
  private final CellState e = CellState.EMPTY;
  private final CellState b = CellState.BURNING;
  private final String simulationType = "SpreadingOfFire";
  private final String defaultNeighborType = "AllFirstLayerNeighbor";
  private final String defaultEdgeType = "Finite";
  private final String defaultShapeType = "Square";

  @Test
  public void checkForCardinalFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells(simulationType, "CardinalFirstLayerNeighbor",
        "Test1", defaultEdgeType, defaultShapeType);
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(e,e,e,e,e)),
            new ArrayList<>(List.of(e,t,b,t,e)),
            new ArrayList<>(List.of(e,b,e,b,e)),
            new ArrayList<>(List.of(e,t,b,t,e)),
            new ArrayList<>(List.of(e,e,e,e,e))
        ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForCornerFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells(simulationType, "CornerFirstLayerNeighbor",
        "Test1", defaultEdgeType, defaultShapeType);
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(e,e,e,e,e)),
            new ArrayList<>(List.of(e,b,t,b,e)),
            new ArrayList<>(List.of(e,t,e,t,e)),
            new ArrayList<>(List.of(e,b,t,b,e)),
            new ArrayList<>(List.of(e,e,e,e,e))
        ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForToroidalPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells(simulationType, defaultNeighborType,
        "Test1", "Toroidal", defaultShapeType);
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
  public void checkForUnboundedPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells(simulationType, defaultNeighborType,
        "Test1", "Unbounded", defaultShapeType);
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
  public void checkForTriangleLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells(simulationType, defaultNeighborType,
        "Test1", defaultEdgeType, "Triangle");
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(e,e,e,e,e)),
            new ArrayList<>(List.of(e,t,b,t,e)),
            new ArrayList<>(List.of(e,b,e,b,e)),
            new ArrayList<>(List.of(e,b,b,t,e)),
            new ArrayList<>(List.of(e,e,e,e,e))
        ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForHexagonLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allSpreadingOfFireCell = new SimulationCells(simulationType, defaultNeighborType,
        "Test1", defaultEdgeType, "Hexagon");
    allSpreadingOfFireCell.updateAllCellTest(1);
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(e,e,e,e,e)),
            new ArrayList<>(List.of(e,b,b,t,e)),
            new ArrayList<>(List.of(e,t,e,t,e)),
            new ArrayList<>(List.of(e,b,b,t,e)),
            new ArrayList<>(List.of(e,e,e,e,e))
        ));
    assertEquals(expectedCellState, allSpreadingOfFireCell.getAllCellState().getCellStateStructure());
  }
}
