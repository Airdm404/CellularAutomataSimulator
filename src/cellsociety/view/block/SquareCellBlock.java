package cellsociety.view.block;

import cellsociety.SimulationController.CellState;
import cellsociety.view.CellBlock;

/**
 * cell block in the shape of square
 */

public class SquareCellBlock extends CellBlock {
  final public static double SQUARE_SIZE = 10;
  final public static double INDENT = 50;

  public SquareCellBlock(CellState cellState, Integer xCoordinate, Integer yCoordinate){
    super(cellState, xCoordinate, yCoordinate);
    drawShape();
    setColor(cellState);
  }

  @Override
  public void drawShape() {
    blockShape.getPoints().addAll(
        xCoordinate * SQUARE_SIZE + INDENT, yCoordinate * SQUARE_SIZE + INDENT,
        (xCoordinate + 1) * SQUARE_SIZE + INDENT, yCoordinate * SQUARE_SIZE + INDENT,
        (xCoordinate + 1) * SQUARE_SIZE + INDENT, (yCoordinate + 1) * SQUARE_SIZE + INDENT,
        xCoordinate * SQUARE_SIZE + INDENT, (yCoordinate + 1) * SQUARE_SIZE + INDENT
    );
  }

}
