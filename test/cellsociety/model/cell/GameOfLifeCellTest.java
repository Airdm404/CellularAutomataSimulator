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

class GameOfLifeCellTest {

  private final CellState d = CellState.DEAD;
  private final CellState l = CellState.LIVING;
  private final String simulationType = "GameOfLife";
  private final String neighborType = "AllFirstLayerNeighbor";
  private final String edgeType = "Finite";
  private final String shapeType = "Square";

  @Test
  public void checkForGameOfLifeBeaconPattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells("GameOfLife", neighborType, "Beacon",
            edgeType, shapeType);
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
  public void checkForGameOfLifeBlinkerPattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells("GameOfLife", neighborType, "Blinker",
            edgeType, shapeType);
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(d, d, d, d, d)),
                    new ArrayList<>(List.of(d, d, l, d, d)),
                    new ArrayList<>(List.of(d, d, l, d, d)),
                    new ArrayList<>(List.of(d, d, l, d, d)),
                    new ArrayList<>(List.of(d, d, d, d, d))
            ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForGameOfLifeToadPattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allGameOfLifeCell = new SimulationCells("GameOfLife", neighborType, "Toad",
            edgeType, shapeType);
    allGameOfLifeCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(d, d, d, d, d, d)),
                    new ArrayList<>(List.of(d, d, d, l, d, d)),
                    new ArrayList<>(List.of(d, l, d, d, l, d)),
                    new ArrayList<>(List.of(d, l, d, d, l, d)),
                    new ArrayList<>(List.of(d, d, l, d, d, d)),
                    new ArrayList<>(List.of(d, d, d, d, d, d))
            ));
    assertEquals(expectedCellState, allGameOfLifeCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkIfLivingCellDie7LivingNeighbors() {
    GameOfLifeCell centerCell = new GameOfLifeCell(1, simulationType, neighborType, edgeType,
            shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 7) {
        surroundingCells
                .add(new GameOfLifeCell(1, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells
                .add(new GameOfLifeCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    centerCell.updateCell(surroundingCells);
    assertEquals(CellState.DEAD, centerCell.getCellState());
  }

  @Test
  public void checkIfLivingCellDieWhen1LivingNeighbors() {
    GameOfLifeCell centerCell = new GameOfLifeCell(1, simulationType, neighborType, edgeType,
            shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 1) {
        surroundingCells
                .add(new GameOfLifeCell(1, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells
                .add(new GameOfLifeCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    centerCell.updateCell(surroundingCells);
    assertEquals(CellState.DEAD, centerCell.getCellState());
  }

  @Test
  public void checkIfDeadCellLiveWhen3LivingNeighbors() {
    GameOfLifeCell centerCell = new GameOfLifeCell(0, simulationType, neighborType, edgeType,
            shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 3) {
        surroundingCells
                .add(new GameOfLifeCell(1, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells
                .add(new GameOfLifeCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    centerCell.updateCell(surroundingCells);
    assertEquals(CellState.LIVING, centerCell.getCellState());
  }
}
