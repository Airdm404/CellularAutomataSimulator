package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

class WaTorWorldCellTest {

  private String simulationType = "WaTorWorld";
  private final String neighborType = "CardinalFirstLayerNeighbor";
  private String edgeType = "Finite";
  private final String shapeType = "Square";
  private final CellState f = CellState.FISH;
  private final CellState s = CellState.SHARK;
  private final CellState u = CellState.UNOCCUPIED;

  @Test
  public void checkForWaTorWorldTest1Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", neighborType,
            "Test1", edgeType, shapeType);
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
  public void checkForWaTorWorldTest2Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", neighborType,
            "Test2", edgeType, shapeType);
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(u,u,s)),
                    new ArrayList<>(List.of(s,f,f)),
                    new ArrayList<>(List.of(u,u,u))
            ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkForWaTorWorldTest3Pattern()
          throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
    SimulationCells allWaTorWorldCell = new SimulationCells("WaTorWorld", neighborType,
            "Test3", edgeType, shapeType);
    allWaTorWorldCell.updateAllCells();
    ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(f,f,f)),
                    new ArrayList<>(List.of(f,f,s)),
                    new ArrayList<>(List.of(f,f,u))
            ));
    assertEquals(expectedCellState, allWaTorWorldCell.getAllCellState().getCellStateStructure());
  }

  @Test
  public void checkIfFishMoveToUnoccupiedCell() {
    WaTorWorldCell fishCell = new WaTorWorldCell(1, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      surroundingCells.add(new WaTorWorldCell(0, simulationType, neighborType, edgeType, shapeType));
    }
    fishCell.updateCell(surroundingCells);
    assertEquals(CellState.UNOCCUPIED, fishCell.getCellState());
    assertEquals(CellState.FISH, surroundingCells.get(0).getCellState());
  }

  @Test
  public void checkIfFishReproduceFishAfter3Rounds() {
    WaTorWorldCell fishCell = new WaTorWorldCell(1, simulationType, neighborType, edgeType, shapeType);
    fishCell.setSurvivedCount(WaTorWorldCell.REPRODUCTION_COUNT - 1);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      surroundingCells.add(new WaTorWorldCell(0, simulationType, neighborType, edgeType, shapeType));
    }
    fishCell.updateCell(surroundingCells);
    assertEquals(CellState.FISH, fishCell.getCellState());
    assertEquals(1, fishCell.getSurvivedCount());
    assertEquals(CellState.FISH, surroundingCells.get(0).getCellState());
    assertEquals(WaTorWorldCell.REPRODUCTION_COUNT, surroundingCells.get(0).getSurvivedCount());
  }

  @Test
  public void checkIfSharkEatFishAndGainEnergy() {
    WaTorWorldCell sharkCell = new WaTorWorldCell(2, simulationType, neighborType, edgeType, shapeType);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      if (i < 1) {
        surroundingCells.add(new WaTorWorldCell(1, simulationType, neighborType, edgeType, shapeType));
      } else {
        surroundingCells.add(new WaTorWorldCell(0, simulationType, neighborType, edgeType, shapeType));
      }
    }
    sharkCell.updateCell(surroundingCells);
    assertEquals(CellState.UNOCCUPIED, sharkCell.getCellState());
    assertEquals(0, sharkCell.getSurvivedCount());
    assertEquals(0, sharkCell.getEnergy());
    assertEquals(CellState.SHARK, surroundingCells.get(0).getCellState());
    assertEquals(WaTorWorldCell.DEFAULT_ENERGY + WaTorWorldCell.ENERGY_BY_ONE_FISH
            - WaTorWorldCell.ENERGY_CONSUMED_EACH_CIRCLE, surroundingCells.get(0).getEnergy());
    assertEquals(2, surroundingCells.get(0).getSurvivedCount());
  }

  @Test
  public void checkIfSharkDieWhenEnergyRunOut(){
    WaTorWorldCell sharkCell = new WaTorWorldCell(2, simulationType, neighborType, edgeType, shapeType);
    sharkCell.setEnergy(WaTorWorldCell.ENERGY_CONSUMED_EACH_CIRCLE);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      surroundingCells.add(new WaTorWorldCell(0, simulationType, neighborType, edgeType, shapeType));

    }
    sharkCell.updateCell(surroundingCells);
    assertEquals(CellState.UNOCCUPIED, sharkCell.getCellState());
    assertEquals(0, sharkCell.getEnergy());
    assertEquals(0, sharkCell.getSurvivedCount());
    for (Cell cell: surroundingCells){
      assertEquals(CellState.UNOCCUPIED, cell.getCellState());
      assertEquals(0, cell.getSurvivedCount());
      assertEquals(0, cell.getEnergy());
    }
  }


  @Test
  public void checkIfSharkMoveToUnoccupiedCellAndReproduce(){
    WaTorWorldCell sharkCell = new WaTorWorldCell(2, simulationType, neighborType, edgeType, shapeType);
    sharkCell.setSurvivedCount(WaTorWorldCell.REPRODUCTION_COUNT - 1);
    ArrayList<Cell> surroundingCells = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      surroundingCells.add(new WaTorWorldCell(0, simulationType, neighborType, edgeType, shapeType));

    }
    sharkCell.updateCell(surroundingCells);
    assertEquals(CellState.SHARK, sharkCell.getCellState());
    assertEquals(1, sharkCell.getSurvivedCount());
    assertEquals(CellState.SHARK, surroundingCells.get(0).getCellState());
    assertEquals(WaTorWorldCell.REPRODUCTION_COUNT, surroundingCells.get(0).getSurvivedCount());
  }
}
