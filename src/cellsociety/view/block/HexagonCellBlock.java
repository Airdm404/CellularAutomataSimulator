package cellsociety.view.block;

import cellsociety.SimulationController.CellState;
import cellsociety.view.CellBlock;

/**
 * cell blocks in the shape of hexagon
 */

public class HexagonCellBlock extends CellBlock {
  final public static double HEXAGON_SIZE = 10;
  final public static double INDENT = 50;

  public HexagonCellBlock(CellState cellState, Integer xCoordinate, Integer yCoordinate){
    super(cellState, xCoordinate, yCoordinate);
    drawShape();
    setColor(cellState);
  }

  @Override
  public void drawShape() {
    if (yCoordinate % 2 == 0){
      blockShape.getPoints().addAll(
        xCoordinate * 3 * HEXAGON_SIZE + INDENT, yCoordinate/2 * Math.sqrt(3) * HEXAGON_SIZE + INDENT,
          (xCoordinate * 3 + 1) * HEXAGON_SIZE + INDENT, yCoordinate/2 * Math.sqrt(3) * HEXAGON_SIZE + INDENT,
          (xCoordinate * 3 + 1) * HEXAGON_SIZE + HEXAGON_SIZE/2 + INDENT, (yCoordinate + 1) * Math.sqrt(3)/2 * HEXAGON_SIZE + INDENT,
          (xCoordinate * 3 + 1) * HEXAGON_SIZE + INDENT, (yCoordinate/2 + 1) * Math.sqrt(3) * HEXAGON_SIZE + INDENT,
          xCoordinate * 3 * HEXAGON_SIZE + INDENT, (yCoordinate/2 + 1) * Math.sqrt(3) * HEXAGON_SIZE + INDENT,
          xCoordinate * 3 * HEXAGON_SIZE - HEXAGON_SIZE/2 + INDENT, (yCoordinate+ 1) * Math.sqrt(3)/2 * HEXAGON_SIZE + INDENT
      );
    }
    else{
      blockShape.getPoints().addAll(
          xCoordinate * 3 * HEXAGON_SIZE + 3*HEXAGON_SIZE/2 + INDENT, yCoordinate/2 * Math.sqrt(3) * HEXAGON_SIZE + Math.sqrt(3)/2*HEXAGON_SIZE + INDENT,
          (xCoordinate * 3 + 1) * HEXAGON_SIZE + 3*HEXAGON_SIZE/2 + INDENT, yCoordinate/2 * Math.sqrt(3) * HEXAGON_SIZE + Math.sqrt(3)/2*HEXAGON_SIZE + INDENT,
          (xCoordinate * 3 + 1) * HEXAGON_SIZE + HEXAGON_SIZE/2 + 3*HEXAGON_SIZE/2 + INDENT, (yCoordinate) * Math.sqrt(3)/2 * HEXAGON_SIZE + Math.sqrt(3)/2*HEXAGON_SIZE + INDENT,
          (xCoordinate * 3 + 1) * HEXAGON_SIZE + 3*HEXAGON_SIZE/2 + INDENT, (yCoordinate/2 + 1) * Math.sqrt(3) * HEXAGON_SIZE + Math.sqrt(3)/2*HEXAGON_SIZE + INDENT,
          xCoordinate * 3 * HEXAGON_SIZE + 3*HEXAGON_SIZE/2 + INDENT, (yCoordinate/2 + 1) * Math.sqrt(3) * HEXAGON_SIZE + Math.sqrt(3)/2*HEXAGON_SIZE + INDENT,
          xCoordinate * 3 * HEXAGON_SIZE - HEXAGON_SIZE/2 + 3*HEXAGON_SIZE/2 + INDENT, (yCoordinate) * Math.sqrt(3)/2 * HEXAGON_SIZE + Math.sqrt(3)/2*HEXAGON_SIZE + INDENT
      );
    }
  }
}
