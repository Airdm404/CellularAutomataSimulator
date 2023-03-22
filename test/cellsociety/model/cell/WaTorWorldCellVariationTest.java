package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WaTorWorldCellVariationTest {

  private final CellState f = CellState.FISH;
  private final CellState s = CellState.SHARK;
  private final CellState u = CellState.UNOCCUPIED;
  private final String simulationType = "WaTorWorld";
  private final String defaultNeighborType = "AllFirstLayerNeighbor";
  private final String defaultEdgeType = "Finite";
  private final String defaultShapeType = "Square";

  @Test
  public void checkForCardinalFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", "CardinalFirstLayerNeighbor",
        "Test1", defaultEdgeType, defaultShapeType);
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(f,s,u)),
            new ArrayList<>(List.of(f,f,f)),
            new ArrayList<>(List.of(u,u,u))
        ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForCornerFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", "CornerFirstLayerNeighbor",
        "Test1", defaultEdgeType, defaultShapeType);
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(u,u,s)),
            new ArrayList<>(List.of(f,f,f)),
            new ArrayList<>(List.of(u,u,f))
        ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForToroidalPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", defaultNeighborType,
        "Test1", "Toroidal", defaultShapeType);
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(s,f,u)),
            new ArrayList<>(List.of(f,f,f)),
            new ArrayList<>(List.of(u,u,u))
        ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForUnboundedPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", defaultNeighborType,
        "Test1", "Unbounded", defaultShapeType);
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(u,f,f,u,u,u,u)),
            new ArrayList<>(List.of(u,u,u,u,u,u,u)),
            new ArrayList<>(List.of(u,f,f,u,u,u,u)),
            new ArrayList<>(List.of(u,s,u,u,u,u,u)),
            new ArrayList<>(List.of(u,u,u,u,u,u,u)),
            new ArrayList<>(List.of(u,u,u,u,u,u,u)),
            new ArrayList<>(List.of(u,u,u,u,u,u,u))
        ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForTriangleLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", defaultNeighborType,
        "Test1", defaultEdgeType, "Triangle");
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(s,f,u)),
            new ArrayList<>(List.of(f,u,u)),
            new ArrayList<>(List.of(f,f,u))
        ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForHexagonLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", defaultNeighborType,
        "Test1", defaultEdgeType, "Hexagon");
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(f,u,f)),
            new ArrayList<>(List.of(f,u,f)),
            new ArrayList<>(List.of(u,s,u))
        ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

}
