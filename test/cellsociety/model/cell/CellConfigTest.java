package cellsociety.model.cell;

import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.CellConfig;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.exceptions.MissingPropertyKeyException;
import cellsociety.exceptions.SimulationNotSupportedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

class CellConfigTest {

    @Test
    void readFileBeacon() throws CSVDimensionsException, IOException, InvalidCellStateGivenException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("GameOfLife/GameOfLifeBlinker");
        Assertions.assertEquals("[[0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 1, 1, 1, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]",
            config.readFile("GameOfLife/GameOfLifeBlinker").toString());

    }
    @Test
    void readFileBlinker() throws CSVDimensionsException, IOException, InvalidCellStateGivenException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("GameOfLife/GameOfLifeBeacon");
        Assertions.assertEquals("[[0, 0, 0, 0, 0, 0], [0, 1, 1, 0, 0, 0], [0, 1, 0, 0, 0, 0], [0, 0, 0, 0, 1, 0], [0, 0, 0, 1, 1, 0], [0, 0, 0, 0, 0, 0]]",config.readFile(
            "GameOfLife/GameOfLifeBeacon").toString());
    }
    @Test
    void readFileToad() throws CSVDimensionsException, IOException, InvalidCellStateGivenException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("GameOfLife/GameOfLifeToad");
        Assertions.assertEquals("[[0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 1, 1, 1, 0], [0, 1, 1, 1, 0, 0], [0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]]",config.readFile(
            "GameOfLife/GameOfLifeToad").toString());
    }
    @Test
    void readGOLBeaconProperties() throws IOException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("GameOfLife/gameOfLifeBeacon");
        TreeMap<String, String> properties =  config.getPropValues("GameOfLife/gameOfLifeBeacon");
        String propertyString = properties.toString();
        Assertions.assertEquals("{AUTHOR=JOHN CONWAY, CSV_NAME=gameOfLifeBeacon, DESCRIPTION=COMMON PERIOD 2 OSCILLATOR, COMPOSED OF TWO DIAGONALLY TOUCHING BLOCKS, GUI_TITLE=GAME OF LIFE - BEACON, SIMULATION=GAME OF LIFE}", propertyString);
    }
    @Test
    void readGOLBlinkerProperties() throws IOException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("GameOfLife/gameOfLifeBlinker");
        TreeMap<String, String> properties =  config.getPropValues("GameOfLife/gameOfLifeBlinker");
        String propertyString = properties.toString();
        Assertions.assertEquals("{AUTHOR=JOHN CONWAY, CSV_NAME=gameOfLifeBlinker, DESCRIPTION=SMALLEST AND MOST COMMON OSCILLATOR, GUI_TITLE=GAME OF LIFE - BLINKER, SIMULATION=GAME OF LIFE}", propertyString);
    }

    @Test
    void readGOLToadProperties() throws IOException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("GameOfLife/gameOfLifeToad");
        TreeMap<String, String> properties =  config.getPropValues("GameOfLife/gameOfLifeToad");
        String propertyString = properties.toString();
        Assertions.assertEquals("{AUTHOR=SIMON NORTON, CSV_NAME=gameOfLifeToad, DESCRIPTION=PERIOD 2 OSCILLATOR, GUI_TITLE=GAME OF LIFE - TOAD, SIMULATION=GAME OF LIFE}", propertyString);
    }

    @Test
    void readModelSegregationCSV() throws MissingPropertyKeyException, SimulationNotSupportedException, IOException, CSVDimensionsException, InvalidCellStateGivenException {
        CellConfig config = new CellConfig("ModelOfSegregation/ModelOfSegregationDefault");
        Assertions.assertEquals("[[0, 1, 2, 1, 1, 0, 0, 2, 1, 2], [0, 0, 2, 1, 0, 0, 1, 1, 2, 0], [2, 2, 1, 1, 0, 0, 2, 1, 1, 0], [0, 0, 0, 2, 2, 1, 1, 2, 0, 2], [1, 2, 0, 0, 0, 0, 1, 1, 2, 1], [1, 1, 2, 2, 0, 0, 0, 1, 0, 2], [0, 1, 2, 0, 0, 2, 1, 2, 0, 0], [1, 2, 2, 2, 1, 0, 0, 1, 2, 0], [0, 0, 1, 2, 1, 2, 0, 1, 1, 0], [2, 1, 0, 0, 2, 1, 2, 0, 0, 1]]",config.readFile(
            "ModelOfSegregation/ModelOfSegregationDefault"

        ).toString());
    }
    @Test
    void readPercolationCSV() throws IOException, CSVDimensionsException, InvalidCellStateGivenException, SimulationNotSupportedException, MissingPropertyKeyException {
        CellConfig config = new CellConfig("Percolation/PercolationDefault");
        Assertions.assertEquals("[[0, 0, 0, 0, 0, 2, 0, 0, 0, 0], [0, 2, 0, 0, 0, 2, 2, 0, 0, 0], [0, 2, 0, 0, 0, 0, 0, 2, 2, 2], [0, 2, 0, 0, 0, 0, 0, 2, 0, 0], [0, 0, 2, 0, 0, 1, 0, 2, 0, 2], [0, 0, 2, 0, 0, 0, 0, 2, 0, 2], [0, 0, 2, 0, 0, 0, 0, 0, 2, 0], [2, 2, 0, 2, 0, 0, 0, 0, 0, 0], [0, 2, 0, 0, 0, 2, 2, 2, 0, 0], [0, 0, 2, 0, 0, 0, 0, 0, 2, 2]]", config.readFile(
            "Percolation/PercolationDefault").toString());
    }

    @Test
    void FileNotFoundExceptionTest() throws MissingPropertyKeyException, SimulationNotSupportedException, IOException {

        Assertions.assertThrows(FileNotFoundException.class, ()-> new CellConfig("DOESNT_EXIST"));
    }
    @Test
    void SimulationNotSupportedTest() throws MissingPropertyKeyException, SimulationNotSupportedException, IOException {

        Assertions.assertThrows(SimulationNotSupportedException.class, () -> new CellConfig("NotSupportedSimulation"));
    }
    @Test
    void RequiredKeyMissingTest() throws MissingPropertyKeyException, SimulationNotSupportedException, IOException {

        Assertions.assertThrows(MissingPropertyKeyException.class, () -> new CellConfig("MissingPropertyKey"));
    }
    @Test
    void CsvDimensionsIncorrectTest() throws MissingPropertyKeyException, SimulationNotSupportedException, IOException {
        CellConfig config = new CellConfig("CsvDimensionsIncorrect");
        Assertions.assertThrows(CSVDimensionsException.class, ()-> config.readFile("CsvDimensionsIncorrect"));
    }
    @Test
    void InvalidCellStateTest() throws MissingPropertyKeyException, SimulationNotSupportedException, IOException {
        CellConfig config = new CellConfig("InvalidCellState");
        Assertions.assertThrows(InvalidCellStateGivenException.class, () -> config.readFile("InvalidCellState"));
    }


}