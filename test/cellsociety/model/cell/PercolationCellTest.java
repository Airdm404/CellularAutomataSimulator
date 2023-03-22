package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.Cell;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class PercolationCellTest {

  private final String shapeType = "Square";
  private final CellState o = CellState.OPEN;
  private final CellState p = CellState.PERCOLATED;
  private final CellState b = CellState.BLOCK;
  private final String simulationType = "Percolation";
  private final String neighborType = "AllFirstLayerNeighbor";
  private final String edgeType = "Finite";

  @Test
  public void checkForPercolationTest1Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", neighborType, "Test1",
            edgeType, shapeType);
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
  public void checkForPercolationTest2Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", neighborType, "Test2",
            edgeType, shapeType);
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
            new ArrayList<>(List.of(o, o, o, o, o)),
            new ArrayList<>(List.of(b, b, b, o, o)),
            new ArrayList<>(List.of(b, p, b, o, o)),
            new ArrayList<>(List.of(b, b, b, o, o)),
            new ArrayList<>(List.of(o, o, o, o, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForPercolationTest3Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allPercolationCell = new SimulationCells("Percolation", neighborType, "Test3",
            edgeType, shapeType);
    allPercolationCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(List.of(
            new ArrayList<>(List.of(o, b, o, b, o)),
            new ArrayList<>(List.of(o, b, p, b, o)),
            new ArrayList<>(List.of(o, b, p, b, o)),
            new ArrayList<>(List.of(o, b, p, b, o)),
            new ArrayList<>(List.of(o, b, o, b, o))
    ));
    assertEquals(expectedCellState, allPercolationCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkIfBlockCellAlwaysRemainAsBlock() {
    PercolationCell blockCell = new PercolationCell(2, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 7) {
        surroundingCells.add(new PercolationCell(1, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new PercolationCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    blockCell.updateCell(surroundingCells);
    assertEquals(CellState.BLOCK, blockCell.getCellState());
  }

  @Test
  public void checkIfPercolatedCellRemainsPercolated() {
    PercolationCell blockCell = new PercolationCell(1, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 3) {
        surroundingCells.add(new PercolationCell(1, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new PercolationCell(0, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new PercolationCell(2, simulationType, neighborType, edgeType, shapeType));
      }
    }
    blockCell.updateCell(surroundingCells);
    assertEquals(CellState.PERCOLATED, blockCell.getCellState());
  }

  @Test
  public void checkIfOpenCellBecomePercolated() {
    PercolationCell blockCell = new PercolationCell(0, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 3) {
        surroundingCells.add(new PercolationCell(2, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new PercolationCell(0, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new PercolationCell(1, simulationType, neighborType, edgeType, shapeType));
      }
    }
    blockCell.updateCell(surroundingCells);
    assertEquals(CellState.PERCOLATED, blockCell.getCellState());
  }
}
