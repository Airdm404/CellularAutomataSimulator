# Simulation Design Plan
### Team Number
11
### Names
Edem Ahorlu
Yixuan Li
Christian Welch


## Design Overview
* Abstract Cell class 
    * Specific Cell subclasses 
 
* Grid class: visualizing  
* Rule class: updating cells 
* Display class: more like a overall GUI
* Button class
* CellConfig class: reads in the configuration file and set the initial states of the cells 
* Main Class



## Design Details

* Grid class contains all the cells created by the Cell class, where CellConfig Class reads in the initial states of the cells, and the Rule class updates the cells.
* Display class will display the Grid, as well as all the buttons on the GUI, and display any possible status.
* Main class runs the game.


## Design Considerations
* To make the simulation run for considerably enough iterations, we need to research for the right initial patterns to start the simulations. 
* When making/updating rules for the game, we are considering having each of the cell subclasses contain the rules based on the type of simulation or have a Rule parent class which has children classes for rules for each type of the simulation.


## User Interface

* Homepage: choose simulations, start simulation (all buttons)
* Grid Page: Quit, Pause (resume)
Sketches of the UI are available in the doc folder. 


## Team Responsibilities

 * Team Member #1
 
Edem Ahorlu: Visualization and Simulation

 * Team Member #2

 Yixuan Li: Simulation

 * Team Member #3

 Christian Welch: Configuration

