# Simulation Design Final
### Names

## Team Roles and Responsibilities


 * Team Member #1 Edem Ahorlu
 Helped construct the MVC structure of the overall project. 
 Responsible for everything frontend. Built all the classes in view packages.
 Responsible for aesthetics and User Interaction. 
 Wrote some tests for each simulation and their variations with different edge type, location shape type and neighbor type.


 * Team Member #2 Yixuan Li 
 Responsibilities: mainly implemented the simulation specifications and all the cell visualization. Built all the classes in model packages. 
 Helped construct the MVC structure of the overall project. 
 Wrote some tests for each simulation and their variations with different edge type, location shape type and neighbor type. 
 
 
 * Team Member #3 Christian Welch 
 Responsibilities: mainly implemented the configuration aspects of the program, suching as reading csv and property files and preparing the information to be used in the simulation.
 This included robust error checking of the input data.



## Design goals

#### What Features are Easy to Add

- new location shape: create a class in the name of `ShapeTypeCellBlock` and extends `CellBlock` class. Add the neighbor configuration properties files to the resources packages under model packages and configure the neighboring data for the new shape type. 

- new edge type: add method that implement the edge type in `NeighborFinder` class. 

- new simulation type: create a new class for the simulation cell and make it extends `Cell` class. 

## High-level Design

#### Core Classes

The overall structure of our project follows MVC principle, where the model, view and controller implementation are separated. Code from model and view are independent of each other. 

#### Model 

  - `AllCells` interface 
  
    An interface that encapsulate all the operations that will possibly be done on all the cells.

  - `SimulationCells`  class

    `SimulationCells` implements `AllCells` interface. It loads all the initial cell states of a simulation, create all the cells, update all the cells according to the simulation update rule, and return the cell state of each cell in each update iteration. 

  - `Cell` class

    `Cell` is an abstract class. All the specific cell class inherits from the `Cell` class. The class has some of the shared methods among all the cells, for example getter and setter for the cell state of a cell, and `findSurroundingCells` method that use reflection to find neighboring cells for different shape location type and edge type. 

    - `GameOfLifeCell` class

        Specific cell class for game of life simulation and it extends `Cell` class. Its `updateCell` method overrides the abstract method `udpateCell` in `Cell` class and implement the updating rules for game of life simulation.

    - `PrecolationCell` class

        Specific cell class for percolation simulation and it extends `Cell` class. Its `updateCell` method overrides the abstract method `udpateCell` in `Cell` class and implement the updating rules for percolation simulation.

    - `SpreadingOfFire` class

        Specific cell class for spreading of fire simulation and it extends `Cell` class. Its `updateCell` method overrides the abstract method `udpateCell` in `Cell` class and implement the updating rules for spreading of fire simulation.

    - `RockPaperScissorCell` class

        Specific cell class for rock paper scissor simulation and it extends `Cell` class. Its `updateCell` method overrides the abstract method `udpateCell` in `Cell` class and implement the updating rules for rock paper scissor simulation.

    - `ModelOfSegregationCell` class

        Specific cell class for model of segregation simulation and it extends `Cell` class. Its `updateCell` method overrides the abstract method `udpateCell` in `Cell` class and implement the updating rules for model of segregation simulation.

    - `WaTorWorldCell` class

        Specific cell class for wa-tor world simulation and it extends `Cell` class. Its `updateCell` method overrides the abstract method `udpateCell` in `Cell` class and implement the updating rules for wa-tor world simulation.                    

- `CellStructure` class 
  
  `CellStructure` class encapsulates the data structure of all the cells. It also contains some methods that deal with horizontal and vertical coordinate of a cell in the data structure, and the size of the data structure as well.

- `CellStateStructure` class 

    `CellStateStructure` class encapsulates the data structure of all the cells' state. It also contains getter and setter methods of the cell states.

- `NeighborFinder` class

    `NeighborFinder` class loads the neighbor configuration information from resource bundle and get the center cell's neighbors according to the given edge type and cell location type. It contains `findNeighborFinite`, `findNeighborToroidal` and `findNeighborUnbounded` method for finite, toroidal and unbounded edge type. The methods follows the same naming convention so that the methods can be invoked using reflection in higher-level classes. 

- Resources Bundles

    * There are 7 resources bundles in total for neighboring configuration for different shape location type. The configuration enables the `NeighborFinder` class to load the correct neighbors for different shape location type. The neighbors are configurated by relative location of horizontal and vertical location of the center cell. For example, in SquareNeighborConfiguration file, if the center cell's coordinate is (x, y), then its first layer corner neighbor is (x-1, y-1), (x-1, y+1), (x+1, y-1) and (x+1, y+1).
    * There are 4 resources bundles responsible for languages: English, French, German and Spanish.

#### View 

- `Grid` class

    `Grid` class receives the updated cell states from controller in each update iteration and updates all the cell view on the screen.

- `CellBlock` class

    `CellBlock` class is an abstract class, all the specific cell block classes inherits from `CellBlock` class. The color code for each cell state is stored in this class, it has an abstract method `drawShape()`, which requires the specific cell block classes to place the desired shape of the cell.

    - `SquareCellBlock` class 

        `SquareCellBlock` class extends `CellBlock` class. Its `drawShape()` method overrides the abstract method in `CellBlock` class and draw a square shape at the desired location.

    - `TriangleCellBlock` class 

        `TriangleCellBlock` class extends `CellBlock` class. Its `drawShape()` method overrides the abstract method in `CellBlock` class and draw a triangle shape at the desired location.    

    - `HexagonCellBlock` class 

        `HexagonCellBlock` class extends `CellBlock` class. Its `drawShape()` method overrides the abstract method in `CellBlock` class and draw a hexagon shape at the desired location.     

- `CellBlockStructure` class 

    `CellBlockStructure` class encapsulates the data structure of all cellBlocks in a simulation. It also contains some getter and setter method, and methods that update the cell state of cell blocks. 


#### Controller 

- `SimulationController` class 
  
  `SimulationController` class plays the role of a controller that connects the model and view of the project, It get the updated cell states of each iteration and input it to view model to display the states of the cell. It also takes input from the interface and send those input to model. 

- `Display` class 

    `Display` class is the main class in this project. Basically it display the GUI and enables the user to interact and control the simulations. Also it loads the GUI design from CSS files. 


#### Exceptions

- `ClassOrMethodNotFoundException` class

    `ClassOrMethodNotFoundException` class handles errors where in a reflection the class or method is not found in this project. 

- `CSVDimensionException` class

    `CSVDimensionException` class handles errors where a CSV file of a simulation initial pattern configuration's dimension is incorrect. 

- `InvalidCellStateGivenException` class

    `InvalidCellStateGivenException` class handles errors where a non-existing cell state is given. 

- `MissingPropertyKeyException` class

    `MissingPropertyKeyException` class handles errors where a property file is missing a required key. 

- `SimulationNotSupportedException` class 
    
    `SimulationNotSupportedException` class handles errors where a given simulation is not supported by this project. 
    

#### Buttons

- `SimulationButton` class

    `SimulationButton` class is an abstract class, all the button class inherits from this class. It set the font and color of each buttons.

    - `DoubleSpeedSimulationButton` class 

        This class extends `SimulationButton` class. User can speed up the simulation by twice by pressing this button. 

    - `EndSimulationButton` class 

        This class extends `SimulationButton` class. User can end the simulation by pressing this button. 

    - `HalfSpeedSimulationButton` class 

        This class extends `SimulationButton` class. User can slow down the simulation by half by pressing this button. 

    - `LoadConfigurationButton` class 

        This class extends `SimulationButton` class. User can load a new configuration file by pressing this button. 

    - `PauseSimulationButton` class 

        This class extends `SimulationButton` class. User can pause the simulation by pressing this button. 

    - `RunThroughSimulationButton` class

        This class extends `SimulationButton` class. User can run through the simulation (animate the simulation) by pressing this button. 

    - `StartSimulationButton` class

        This class extends `SimulationButton` class. User can start the simulation by pressing this button.

    - `StepThroughSimulation` class

        This class extends `SimulationButton` class. User can step to the next iteration of the simulation by pressing this button.

### Cell Configuration
 
- `CellConfiguration` class 
  
  This class has all the methods that load data and information from both resource bundle property files of a simulation initiation pattern and the csv file of a simulation initial pattern. 


## Assumptions that Affect the Design

- CSV files should not have empty lines 

#### Features Affected by Assumptions

- When creating a new CSV files, watch out for empty lines because it will crash the code. 

## Significant differences from Original Plan

Originally we planned to configure updating rule of each simulation to data file and simply load rules 
from data files. Later we found out it was  not feasible. 

## New Features HowTo

#### Easy to Add Features

- new edge type
- new initiation pattern of a specific simulation 
- new neighbor type 
- new location shape type 
- new kinds of simulation 

#### Other Features not yet Done
Features that weren't completed:
 * Choose colors to represent all cells of the same state displayed in the grid
 * Choose images to represent all cell states that will be drawn on top of the grid's location for each cell with that state
 * Allow users to see two different Views of the simulation: a grid or graph
 * Caught Exceptions displayed on the GUI
 
 For displaying Exceptions on the GUI, I can create a method which takes the message to be displayed as a string.
 This method will have an Alert object which creates and displays the error pop-up window. This method will then be
 called in the catch portion of the try catch statements.
 
 For the rest of the other features not implemented, I didn't have enough time to think about it and therefore
 have zero knowledge about how they will be implemented. I'll, however, find out from my TA on how I can go about it
 during my Code Review meeting.
 
