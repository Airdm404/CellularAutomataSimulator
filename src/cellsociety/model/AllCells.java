package cellsociety.model;

import cellsociety.exceptions.ClassOrMethodNotFoundException;

/**
 * interface for classes that handles all the cells
 */

public interface AllCells {

  void createAllCells() throws ClassOrMethodNotFoundException;

  void updateAllCells() throws ClassOrMethodNotFoundException;

  CellStateStructure getAllCellState();

  int getHeightCount();

  int getWidthCount();

  void updateAllCellState(CellStateStructure cellStateStructure);
}
