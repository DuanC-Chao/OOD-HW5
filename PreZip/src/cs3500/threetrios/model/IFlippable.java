package cs3500.threetrios.model;

/**
 * Interface used to support "flip" action.
 */
public interface IFlippable {
  /**
   * Takes a grid to operate, and a col & row to start flipping.
   *
   * @param grid The grid wished to operate,
   *             it must be the reference to the grid itself, not clone.
   * @param row  The row to start flipping with, 0-based.
   * @param col  The col to start flipping with, 0-based.
   * @throws IllegalArgumentException If row or col are not legal.
   * @throws IllegalStateException    If there is no card on the given row and col.
   */
  void flip(ICell[][] grid, int row, int col);
}
