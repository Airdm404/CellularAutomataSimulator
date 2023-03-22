package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RockPaperScissorCellVariationTest {

  private final CellState r = CellState.ROCK;
  private final CellState p = CellState.PAPER;
  private final CellState s = CellState.SCISSOR;
  private final String simulationType = "RockPaperScissor";
  private final String defaultNeighborType = "AllFirstLayerNeighbor";
  private final String defaultEdgeType = "Finite";
  private final String defaultShapeType = "Square";

  @Test
  public void checkForCardinalFirstLayerNeighborPatternTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells(simulationType,
        "CardinalFirstLayerNeighbor", "Test1"
        , defaultEdgeType, defaultShapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(p, r, r)),
            new ArrayList<>(List.of(s, r, p)),
            new ArrayList<>(List.of(p, p, p))
        ));
    assertEquals(expectedCellState,
        allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForCornerFirstLayerNeighborPatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells(simulationType,
        "CornerFirstLayerNeighbor", "Test1"
        , defaultEdgeType, defaultShapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(p, r, r)),
            new ArrayList<>(List.of(s, p, p)),
            new ArrayList<>(List.of(p, p, p))
        ));
    assertEquals(expectedCellState,
        allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForToroidalEdgePatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1"
        , "Toroidal", defaultShapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(p, p, p)),
            new ArrayList<>(List.of(s, p, s)),
            new ArrayList<>(List.of(p, p, p))
        ));
    assertEquals(expectedCellState,
        allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForUnboundedEdgePatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1"
        , "Unbounded", defaultShapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(r, r, r, r, r, r, r)),
            new ArrayList<>(List.of(r, p, r, r, r, r, r)),
            new ArrayList<>(List.of(r, r, p, p, r, r, r)),
            new ArrayList<>(List.of(r, p, p, p, r, r, r)),
            new ArrayList<>(List.of(r, r, p, r, r, r, r)),
            new ArrayList<>(List.of(r, r, r, r, r, r, r)),
            new ArrayList<>(List.of(r, r, r, r, r, r, r))
        ));
    assertEquals(expectedCellState,
        allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForTriangleLocationShapePatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1"
        , defaultEdgeType, "Triangle");
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(p, p, r)),
            new ArrayList<>(List.of(s, p, p)),
            new ArrayList<>(List.of(p, p, p))
        ));
    assertEquals(expectedCellState,
        allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForHexagonLocationShapePatterTest1() throws IOException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells(simulationType,
        defaultNeighborType, "Test1"
        , defaultEdgeType, "Hexagon");
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
        List.of(
            new ArrayList<>(List.of(p, r, r)),
            new ArrayList<>(List.of(s, r, p)),
            new ArrayList<>(List.of(p, p, p))
        ));
    assertEquals(expectedCellState,
        allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }
}
