package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ModelOfSegregationCellVariationTest {

  private final CellState a = CellState.AGENT_X;
  private final CellState b = CellState.AGENT_O;
  private final CellState n = CellState.NON_AGENT;
  private final String simulationType = "ModelOfSegregation";
  private final String defaultNeighborType = "AllFirstLayerNeighbor";
  private final String defaultEdgeType = "Finite";
  private final String defaultShapeType = "Square";

  @Test
  public void checkForCardinalFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allModelOfSegregationCell = new SimulationCells(simulationType,
        "CardinalFirstLayerNeighbor", "Test1", defaultEdgeType, defaultShapeType);
    allModelOfSegregationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(a,b,b)),
            new ArrayList<>(List.of(a,n,a)),
            new ArrayList<>(List.of(a,a,a))
        ));
    assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForCornerFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allModelOfSegregationCell = new SimulationCells(simulationType,
        "CornerFirstLayerNeighbor", "Test1", defaultEdgeType, defaultShapeType);
    allModelOfSegregationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(b,a,b)),
            new ArrayList<>(List.of(a,a,a)),
            new ArrayList<>(List.of(n,a,a))
        ));
    assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForToroidalEdgePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allModelOfSegregationCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1", "Toroidal", defaultShapeType);
    allModelOfSegregationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(b,a,b)),
            new ArrayList<>(List.of(a,n,a)),
            new ArrayList<>(List.of(a,a,a))
        ));
    assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForUnboundedEdgePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allModelOfSegregationCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1", "Unbounded", defaultShapeType);
    allModelOfSegregationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(a,a,b,b,a,a,a)),
            new ArrayList<>(List.of(a,n,n,n,n,n,n)),
            new ArrayList<>(List.of(n,n,n,n,n,n,n)),
            new ArrayList<>(List.of(n,n,n,n,n,n,n)),
            new ArrayList<>(List.of(n,n,n,n,n,n,n)),
            new ArrayList<>(List.of(n,n,n,n,n,n,n)),
            new ArrayList<>(List.of(n,n,n,n,n,n,n))
        ));
    assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForTriangleLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allModelOfSegregationCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1", defaultEdgeType, "Triangle");
    allModelOfSegregationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(b,a,b)),
            new ArrayList<>(List.of(a,n,a)),
            new ArrayList<>(List.of(a,a,a))
        ));
    assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForHexagonLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allModelOfSegregationCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1", defaultEdgeType, "Hexagon");
    allModelOfSegregationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(b,a,b)),
            new ArrayList<>(List.of(a,n,a)),
            new ArrayList<>(List.of(a,a,a))
        ));
    assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
  }
}
