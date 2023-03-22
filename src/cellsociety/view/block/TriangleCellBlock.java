package cellsociety.view.block;


import cellsociety.SimulationController.CellState;
import cellsociety.view.CellBlock;

/**
 * cell block in the shape of triangle
 */

public class TriangleCellBlock extends CellBlock {
  public final static double TRIANGLE_SIZE = 20;
  public final static double INDENT = 60;

  public TriangleCellBlock(CellState cellState, Integer xCoordinate, Integer yCoordinate){
    super(cellState, xCoordinate, yCoordinate);
    drawShape();
    setColor(cellState);
  }

  @Override
  public void drawShape() {
    if (yCoordinate % 2 == 0){
      if ((yCoordinate/2) % 2 == 0){
        blockShape.getPoints().addAll(
            xCoordinate * TRIANGLE_SIZE + INDENT, yCoordinate/2 * TRIANGLE_SIZE + INDENT,
            (xCoordinate + 1) * TRIANGLE_SIZE + INDENT, yCoordinate/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE + TRIANGLE_SIZE/2 + INDENT, yCoordinate/2 * TRIANGLE_SIZE - Math.sqrt(3)/2 * TRIANGLE_SIZE + INDENT
        );
      }
      else{
        blockShape.getPoints().addAll(
            xCoordinate * TRIANGLE_SIZE - TRIANGLE_SIZE/2 + INDENT, yCoordinate/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE + TRIANGLE_SIZE/2 + INDENT, yCoordinate/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE - TRIANGLE_SIZE/2 + TRIANGLE_SIZE/2 + INDENT, yCoordinate/2 * TRIANGLE_SIZE - Math.sqrt(3)/2 * TRIANGLE_SIZE + INDENT
        );
      }
    }
    else{
      if (((yCoordinate - 1)/2) % 2 == 0){
        blockShape.getPoints().addAll(
          xCoordinate * TRIANGLE_SIZE - TRIANGLE_SIZE/2 + INDENT, yCoordinate/2 * TRIANGLE_SIZE - Math.sqrt(3)/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE + TRIANGLE_SIZE/2 + INDENT, yCoordinate/2 * TRIANGLE_SIZE - Math.sqrt(3)/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE + INDENT, (yCoordinate - 1)/2 * TRIANGLE_SIZE + INDENT
        );
      }
      else{
        blockShape.getPoints().addAll(
            xCoordinate * TRIANGLE_SIZE + INDENT, yCoordinate/2 * TRIANGLE_SIZE - Math.sqrt(3)/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE + TRIANGLE_SIZE + INDENT, yCoordinate/2 * TRIANGLE_SIZE - Math.sqrt(3)/2 * TRIANGLE_SIZE + INDENT,
            xCoordinate * TRIANGLE_SIZE + TRIANGLE_SIZE/2 + INDENT, (yCoordinate - 1)/2 * TRIANGLE_SIZE + INDENT
        );
      }
    }
  }
}
