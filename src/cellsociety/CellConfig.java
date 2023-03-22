package cellsociety;

import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.exceptions.MissingPropertyKeyException;
import cellsociety.exceptions.SimulationNotSupportedException;

import java.io.*;
import java.util.*;

import java.io.IOException;
import java.util.Properties;

/**
 * Class handles file reading for use in initialization of a desired simulation with parameters given in properties and csv files
 */

public class CellConfig {

    private TreeMap<String, String> myPropertyMap;


    public CellConfig(String propertyFileName) throws MissingPropertyKeyException, SimulationNotSupportedException, IOException {
        this.myPropertyMap = getPropValues(propertyFileName);
    }

    //creates a scanner object to use in reading the CSV file
    private Scanner getCellConfigFileScanner(String dataSource) throws IOException {


            InputStream textFile = Objects.requireNonNull(CellConfig.class.getClassLoader().getResource(dataSource))
                    .openStream();


        return new Scanner(Objects.requireNonNull(textFile));
    }

    /**
     * reads in the CSV file and returns an arraylist of arraylist of integers that represents the state of each cell
     * @param dataSource data source
     * @return 2D data structure of cell configuration
     * @throws CSVDimensionsException The dimensions given in the CSV File do not match data
     * @throws IOException The file cannot be read in for some reason
     * @throws InvalidCellStateGivenException A cell state given in the csv file was not valid
     * @throws SimulationNotSupportedException a simulation given in the property file was not valid
     * @throws MissingPropertyKeyException a required property was missing in the property file
     */

    public ArrayList<ArrayList<Integer>> readFile(String dataSource) throws CSVDimensionsException, IOException, InvalidCellStateGivenException, SimulationNotSupportedException, MissingPropertyKeyException {
        Scanner scan = getCellConfigFileScanner(dataSource + ".csv");
        getPropValues(dataSource);

        //first line of CSV is dimensions of grid
        String[] dimensions = scan.nextLine().split(",");
        List<String> blocks;
        ArrayList<ArrayList<Integer>> allRows = new ArrayList<>();

        while ( scan.hasNextLine()){
            //each line is an arrayList of integers
            blocks =  Arrays.asList(scan.nextLine().split(","));
            ArrayList<Integer> rowOfBlocks = getIntegerArray(blocks);

            // check that values given in file are valid states
            if(!checkCellState(rowOfBlocks)){
                throw new InvalidCellStateGivenException("Invalid Cell State value given in CSV file");
            }
            // add each arraylist
            allRows.add(rowOfBlocks);
        }
        //check that given CSV dimensions match up with dimensions of data read in
        if(! checkCSVdimensions(dimensions, allRows)){
            throw new CSVDimensionsException("The dimensions given in the CSV File do not match data");
        }



        return allRows;
    }

    //check that given dimensions match up with dimensions of data read in
    private boolean checkCSVdimensions(String[] dimensions, ArrayList<ArrayList<Integer>> allBlocks){
        int numberColumns = allBlocks.get(0).size();
        int numberRows = allBlocks.size();
        int numberRowsGiven = Integer.parseInt(dimensions[0]);
        int numberColumnsGiven = Integer.parseInt(dimensions[1]);

        return (numberColumns == numberColumnsGiven) && numberRows == numberRowsGiven;
    }

    private ArrayList<Integer> getIntegerArray(List<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<>();
        for(String stringValue : stringArray) {

                result.add(Integer.parseInt(stringValue));

        }
        return result;
    }


    /**
     * reads property files and returns Map where Keys are name of property and values are value of that property in file given
     * @param propFileName property file name
     * @return the data in property files
     * @throws IOException
     * @throws SimulationNotSupportedException
     * @throws MissingPropertyKeyException
     */
    public TreeMap<String, String> getPropValues(String propFileName) throws IOException, SimulationNotSupportedException, MissingPropertyKeyException {

            Properties prop = new Properties();
            InputStream inputStream = CellConfig.class.getClassLoader().getResourceAsStream(propFileName + ".properties");

            // check that property file exists
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            TreeMap<String,String> propertiesMap = new TreeMap(prop);


            // check that given simulation is supported
            if (!checkGivenSimulationType(propertiesMap.get("SIMULATION"))){
                throw new SimulationNotSupportedException(String.format("%s is not a supported simulation",prop.getProperty("SIMULATION")));
            }

            // check that no required properties are null
            if (!checkRequiredPropertiesGiven(propertiesMap)){
                throw new MissingPropertyKeyException("A required property is missing from the properties file");
            }

        return propertiesMap;
    }


    /**
     * methods for retrieving individual types of properties
     * @return title/simulation type/author/description/csv name
     */
    public  String getTitle(){
        return myPropertyMap.get("GUI_TITLE");
    }
    public  String getSimulationType(){
        return myPropertyMap.get("SIMULATION");
    }
    public  String getAuthor(){
        return myPropertyMap.get("AUTHOR");
    }
    public  String getDescription(){
        return myPropertyMap.get("DESCRIPTION");
    }
    public  String getCSVName(){
        return myPropertyMap.get("CSV_NAME");
    }
    public  String getParameter1(){
        return myPropertyMap.get("PARAMETER_1");
    }

    //check that all required properties are given in the property file
    private boolean checkRequiredPropertiesGiven(Map<String, String> propertiesMap){
        ArrayList<String> requiredProperties = new ArrayList<>();
        requiredProperties.add("GUI_TITLE");
        requiredProperties.add("SIMULATION");
        requiredProperties.add("AUTHOR");
        requiredProperties.add("CSV_NAME");
        requiredProperties.add("DESCRIPTION");

        for (String property: requiredProperties){
            // if property is not in propertyMap or value of property is null, return false
            if (!propertiesMap.containsKey(property) || (propertiesMap.get(property).equals(""))){
                return false;
            }
        }

        return true;
    }

    private boolean checkGivenSimulationType(String givenSimulation){
        for (SimulationTypes simulation: SimulationTypes.values()){
            if (simulation.getValue().equals(givenSimulation)){
                return true;
            }
        }
        return false;
    }

    public enum SimulationTypes {

        GAME_OF_LIFE("GAME OF LIFE"),
        MODEL_OF_SEGREGATION("MODEL OF SEGREGATION"),
        PERCOLATION("PERCOLATION"),
        ROCK_PAPER_SCISSORS("ROCK PAPER SCISSORS"),
        SPREADING_OF_FIRE("SPREADING OF FIRE"),
        WATOR_WORLD("WaTor World");

        private final String value;

        SimulationTypes(final String newValue) {
            value = newValue;
        }

        public String getValue() { return value; }

        }





    //check that value given in CSV is a valid cell state
    private boolean checkCellState(List<Integer> rowOfCells){
        if (this.getSimulationType().equals("GAME OF LIFE")){
            for(Integer cell :  rowOfCells){
                if (!(cell == 0 | cell ==  1)){
                    return false;
                }
            }
        }
        // if other type of simulation; they all use 0,1,2 for states
        else {
            for (Integer cell: rowOfCells){
                if (!(cell == 0 | cell == 1 | cell == 2)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * creates a grid of randomly generated states based on dimensions passed in
     * @param numberRows number of rows in desired random state grid
     * @param numColumns number of columns in desired random state grid
     * @return ArrayList<ArrayList<Integer>>
     *
     * </Integer>
     */
    public ArrayList<ArrayList<Integer>> createRandomGrid(Integer numberRows, Integer numColumns){
        ArrayList<ArrayList<Integer>> allRows = new ArrayList<>();
        for (int row = 0; row < numberRows; row ++){
            ArrayList<Integer> currentRow = new ArrayList<>();
            for (int col = 0; col < numColumns; col ++){
                currentRow.add(getRandomCellState());
            }
            allRows.add(currentRow);
        }
        return allRows;
    }
    /*

    public ArrayList<ArrayList<Integer>> createGridWithNumberOccupied(Integer numberRows, Integer numColumns, Integer numOccupied) {
        int totalCells = numberRows*numColumns;


    }

     */

    // returns a random state to use in creating random initial configurations
    private int getRandomCellState(){
        int randomState;

        if (this.getSimulationType().equals("GAME OF LIFE")){
            randomState = getRandomNumberUsingNextInt(0,2);
        }
        else randomState = getRandomNumberUsingNextInt(0,3);

        return randomState;
    }

    private int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }






}




