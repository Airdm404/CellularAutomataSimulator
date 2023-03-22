simulation
====

This project implements a cellular automata simulator.

Names:
Edem Ahorlu

Yixuan Li

Christian Welch

### Timeline

Start Date: 08/02/2020

Finish Date: 10/20/2020

Hours Spent:
Edem Ahorlu: ~42h 

Yixuan Li: ~40h

Christian Welch:  about 30 hours

### Primary Roles
Edem Ahorlu: Visualization

Yixuan Li: Simulation

Christian Welch: Configuration

### Resources Used
- StackOverflow
- W3Schools
- docs.oracle.com
- tutorialspoint.com
- geeksforgeeks


### Running the Program

Main class: Display

Data files needed: 
English.properties
Spanish.properties
German.properties
French.properties
Light.css
Dark.css
Duke.css

gameOfLife.csv (To configure the initial cell pattern)

Features implemented:
All features specified apart from:
- Allowing users to see two different views of the simulation(grid/graph)
- Choose colors to represent all cells of the same state displayed
- Choose images to represent all cells of the same state displayed
- Save the current state of the loaded simulation to both properties
 and CSV files that share one name entered by the user



### Notes/Assumptions

Assumptions or Simplifications:
data file names are correct and data file exists in the _data_ resources root.

Interesting data files:

Blinker, Beacon and Toad for Game of Life simulation iterates with a repetitive pattern. 

Known Bugs:

The file chooser is not working properly.

When click on the cell block, the cell state changed, but only updates till the next iteration



### Impressions
- Learning how to use Resource property files and CSS files made formatting and editing easier and more readily accessible.
- Design of the project in general needed to be flexible (obey the SOLID principles) to be expanded upon: we had to change some of our code to reflect these principles.
- Use of reflections to get rid of conditionals.
