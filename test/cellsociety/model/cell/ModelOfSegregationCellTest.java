package cellsociety.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.SimulationController.CellState;
import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.model.Cell;
import cellsociety.model.CellStructure;
import cellsociety.model.SimulationCells;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ModelOfSegregationCellTest {

    private final String shapeType = "Square";
    private final CellState a = CellState.AGENT_X;
    private final CellState b = CellState.AGENT_O;
    private final CellState n = CellState.NON_AGENT;
    private final CellStructure allCells = new CellStructure(new ArrayList<>());
    private final String simulationType = "ModelOfSegregation";
    private final String neighborType = "AllFirstLayerNeighbor";
    private final String edgeType = "Finite";

    @Test
    public void checkForModelOfSegregationTest1Pattern()
            throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
        SimulationCells allModelOfSegregationCell = new SimulationCells("ModelOfSegregation",
                neighborType, "Test1", edgeType, shapeType);
        allModelOfSegregationCell.updateAllCells();
        ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of(b, a, b)),
                        new ArrayList<>(List.of(a, n, a)),
                        new ArrayList<>(List.of(a, a, a))
                ));
        assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
    }

    @Test
    public void checkForModelOfSegregationTest2Pattern()
            throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
        SimulationCells allModelOfSegregationCell = new SimulationCells("ModelOfSegregation",
                neighborType, "Test2", edgeType, shapeType);
        allModelOfSegregationCell.updateAllCells();
        ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of(a, a, a)),
                        new ArrayList<>(List.of(b, a, a)),
                        new ArrayList<>(List.of(n, b, n))
                ));
        assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
    }

    @Test
    public void checkForModelOfSegregationTest3Pattern()
            throws IOException, CSVDimensionsException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {
        SimulationCells allModelOfSegregationCell = new SimulationCells("ModelOfSegregation",
                neighborType, "Test3", edgeType, shapeType);
        allModelOfSegregationCell.updateAllCells();
        ArrayList<ArrayList<CellState>> expectedCellState = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of(a, b, a)),
                        new ArrayList<>(List.of(n, n, n)),
                        new ArrayList<>(List.of(n, n, n))
                ));
        assertEquals(expectedCellState, allModelOfSegregationCell.getAllCellState().getCellStateStructure());
    }


    @Test
    public void checkIfAgentOWillBeMovedIf5AgentXSurrounded() {
        ModelOfSegregationCell agentOCell = new ModelOfSegregationCell(2, simulationType, neighborType,
                edgeType, shapeType);
        ArrayList<Cell> surroundingCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < 5) {
                surroundingCells
                        .add(new ModelOfSegregationCell(1, simulationType, neighborType, edgeType, shapeType));
            } else if (i < 7) {
                surroundingCells
                        .add(new ModelOfSegregationCell(0, simulationType, neighborType, edgeType, shapeType));
            } else {
                surroundingCells
                        .add(new ModelOfSegregationCell(2, simulationType, neighborType, edgeType, shapeType));
            }
        }
        agentOCell.updateCell(allCells, surroundingCells);
        assertEquals(CellState.NON_AGENT, agentOCell.getCellState());
    }

    @Test
    public void checkIfAgentXWillBeMovedIf5AgentOSurrounded() {
        ModelOfSegregationCell agentOCell = new ModelOfSegregationCell(1, simulationType, neighborType,
                edgeType, shapeType);
        ArrayList<Cell> surroundingCells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i < 5) {
                surroundingCells
                        .add(new ModelOfSegregationCell(2, simulationType, neighborType, edgeType, shapeType));
            } else if (i < 7) {
                surroundingCells
                        .add(new ModelOfSegregationCell(0, simulationType, neighborType, edgeType, shapeType));
            } else {
                surroundingCells
                        .add(new ModelOfSegregationCell(1, simulationType, neighborType, edgeType, shapeType));
            }
        }
        agentOCell.updateCell(allCells, surroundingCells);
        assertEquals(CellState.NON_AGENT, agentOCell.getCellState());
    }

}
