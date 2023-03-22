package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PercolationCellVariationTest {

  private final CellState o = CellState.OPEN;
  private final CellState p = CellState.PERCOLATED;
  private final CellState b = CellState.BLOCK;
  private final String simulationType = "Percolation";
  private final String defaultNeighborType = "AllFirstLayerNeighbor";
  private final String defaultEdgeType = "Finite";
  private final String defaultShapeType = "Square";

  @Test
  public void checkForCardinalFirstLayerNeighborPatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation",
        "CardinalFirstLayerNeighbor", "Test1",
        defaultEdgeType, defaultShapeType);
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(p, p, b, b, b)),
        new ArrayList<>(List.of(o, p, o, o, o)),
        new ArrayList<>(List.of(o, o, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForCornerFirstLayerNeighborPatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation",
        "CornerFirstLayerNeighbor", "Test1",
        defaultEdgeType, defaultShapeType);
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(p, b, p, o, o)),
        new ArrayList<>(List.of(o, p, b, b, b)),
        new ArrayList<>(List.of(p, o, p, o, o)),
        new ArrayList<>(List.of(o, o, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForToroidalEdgePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", defaultNeighborType,
        "Test1",
        "Toroidal", defaultShapeType);
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(p, b, p, o, o)),
        new ArrayList<>(List.of(p, p, b, b, b)),
        new ArrayList<>(List.of(p, p, p, o, o)),
        new ArrayList<>(List.of(o, o, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForUnboundedEdgePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", defaultNeighborType,
        "Test1",
        "Toroidal", defaultShapeType);
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(p, b, p, o, o)),
        new ArrayList<>(List.of(p, p, b, b, b)),
        new ArrayList<>(List.of(p, p, p, o, o)),
        new ArrayList<>(List.of(o, o, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForTriangleLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", defaultNeighborType,
        "Test1",
        defaultEdgeType, "Triangle");
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
        new ArrayList<>(List.of(p, b, o, o, o)),
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(p, p, b, b, b)),
        new ArrayList<>(List.of(p, p, o, o, o)),
        new ArrayList<>(List.of(p, p, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForHexagonLocationShapePatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", defaultNeighborType,
        "Test1",
        defaultEdgeType, "Hexagon");
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
        new ArrayList<>(List.of(o, b, o, o, o)),
        new ArrayList<>(List.of(p, b, o, o, o)),
        new ArrayList<>(List.of(o, p, b, b, b)),
        new ArrayList<>(List.of(p, p, o, o, o)),
        new ArrayList<>(List.of(o, p, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }
}
