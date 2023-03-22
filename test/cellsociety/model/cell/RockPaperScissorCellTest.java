package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import cellsociety.model.Cell;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RockPaperScissorCellTest {

  private String simulationType = "RockPaperScissor";
  private String neighborType = "AllFirstLayerNeighbor";
  private String edgeType = "Finite";
  private final String shapeType = "Square";
  private final CellState r = CellState.ROCK;
  private final CellState p = CellState.PAPER;
  private final CellState s = CellState.SCISSOR;

  @Test
  public void checkForRockPaperScissorTest1Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells("RockPaperScissor", neighborType, "Test1"
            , edgeType, shapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(p,r,r)),
                    new ArrayList<>(List.of(s,p,p)),
                    new ArrayList<>(List.of(p,p,p))
            ));
    assertEquals(expectedCellState, allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForRockPaperScissorTest2Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells("RockPaperScissor", neighborType,
            "Test2", edgeType, shapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(s,s,r)),
                    new ArrayList<>(List.of(s,r,r)),
                    new ArrayList<>(List.of(p,r,p))
            ));
    assertEquals(expectedCellState, allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }


  @Test
  public void checkForRockPaperScissorTest3Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allRockPaperScissorCell = new SimulationCells("RockPaperScissor", neighborType,
            "Test3", edgeType, shapeType);
    allRockPaperScissorCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(s,s,s)),
                    new ArrayList<>(List.of(s,s,s)),
                    new ArrayList<>(List.of(r,s,s))
            ));
    assertEquals(expectedCellState, allRockPaperScissorCell.getAllCellState().getCellStateStructure());
  }


  @Test
  public void checkRockBecomePaperWhen5PaperSurround(){
    RockPaperScissorCell rockCell = new RockPaperScissorCell(0, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 4) {
        surroundingCells.add(new RockPaperScissorCell(1, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new RockPaperScissorCell(2, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new RockPaperScissorCell(0, simulationType,neighborType, edgeType, shapeType));
      }
    }
    rockCell.updateCell(surroundingCells);
    assertEquals(CellState.PAPER, rockCell.getCellState());
  }

  @Test
  public void checkPaperBecomeScissorWhen5ScissorSurround(){
    RockPaperScissorCell paperCell = new RockPaperScissorCell(1, simulationType,neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 4) {
        surroundingCells.add(new RockPaperScissorCell(2,simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new RockPaperScissorCell(1, simulationType,neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new RockPaperScissorCell(0, simulationType,neighborType, edgeType, shapeType));
      }
    }
    paperCell.updateCell(surroundingCells);
    assertEquals(CellState.SCISSOR, paperCell.getCellState());
  }

  @Test
  public void checkScissorBecomeRockWhen5RockSurround(){
    RockPaperScissorCell scissorCell = new RockPaperScissorCell(2, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      if (i < 4) {
        surroundingCells.add(new RockPaperScissorCell(0, simulationType, neighborType, edgeType, shapeType));
      } else if (i < 7) {
        surroundingCells.add(new RockPaperScissorCell(1, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new RockPaperScissorCell(2, simulationType, neighborType, edgeType, shapeType));
      }
    }
    scissorCell.updateCell(surroundingCells);
    assertEquals(CellState.ROCK, scissorCell.getCellState());
  }
}
