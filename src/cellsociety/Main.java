package cellsociety;

import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.exceptions.MissingPropertyKeyException;
import cellsociety.exceptions.SimulationNotSupportedException;

import java.io.IOException;


public class Main {
    public static void main (String[] args) throws IOException, SimulationNotSupportedException, MissingPropertyKeyException, CSVDimensionsException, InvalidCellStateGivenException {
        CellConfig config = new CellConfig("Percolation/PercolationDefault");
        //System.out.println(config.readFile("Percolation/PercolationDefault.csv").toString());
        System.out.println(config.createRandomGrid(6,6));


    }

}
