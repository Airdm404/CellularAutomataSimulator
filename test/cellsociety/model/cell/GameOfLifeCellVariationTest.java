package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameOfLifeCellVariationTest {

  private final CellState d = CellState.DEAD;
  private final CellState l = CellState.LIVING;
  private final String simulationType = "GameOfLife";
  private final String defaultNeighborType = "AllFirstLayerNeighbor";
  private final String defaultEdgeType = "Finite";
  private final String defaultShapeType = "Square";

  @Test
  public void checkForCardinalFirstLayerNeighborPatternBeacon() throws IOException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells(simulationType,
        "CardinalFirstLayerNeighbor", "Beacon",
        defaultEdgeType, defaultShapeType);
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, l, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, l, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d))
        ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForCornerFirstLayerNeighborPatterBeacon() throws IOException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells(simulationType,
        "CornerFirstLayerNeighbor", "Beacon",
        defaultEdgeType, defaultShapeType);
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d))
        ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForToroidalEdgePatternBeacon() throws IOException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells(simulationType, defaultNeighborType,
        "Beacon",
        "Toroidal", defaultShapeType);
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, l, l, d, d, d)),
            new ArrayList<>(List.of(d, l, l, d, d, d)),
            new ArrayList<>(List.of(d, d, d, l, l, d)),
            new ArrayList<>(List.of(d, d, d, l, l, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d))
        ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForUnboundedEdgePatternBeacon() throws IOException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells(simulationType, defaultNeighborType,
        "Beacon",
        "Unbounded", defaultShapeType);
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, l, l, d, d, d)),
            new ArrayList<>(List.of(d, l, l, d, d, d)),
            new ArrayList<>(List.of(d, d, d, l, l, d)),
            new ArrayList<>(List.of(d, d, d, l, l, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d))
        ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForTriangleShapeLocationPatternBeacon() throws IOException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells(simulationType, defaultNeighborType,
        "Beacon",
        defaultEdgeType, "Triangle");
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(d, l, d, d, d, d)),
            new ArrayList<>(List.of(d, l, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, l, d)),
            new ArrayList<>(List.of(d, l, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, l, d)),
            new ArrayList<>(List.of(d, d, d, d, l, d))
        ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForHexagonShapeLocationPatternBeacon() throws IOException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells(simulationType, defaultNeighborType,
        "Beacon",
        defaultEdgeType, "Hexagon");
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d)),
            new ArrayList<>(List.of(d, d, d, d, d, d))
        ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }
}
