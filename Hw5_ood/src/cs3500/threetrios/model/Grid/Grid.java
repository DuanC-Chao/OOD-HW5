package cs3500.threetrios.model.Grid;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Cells.RegularCardCell;
import cs3500.threetrios.model.Cells.ICell;
import cs3500.threetrios.model.Cells.RegularHole;
import cs3500.threetrios.model.Exception.CouldNotPlaceCardException;

/**
 * The Functional class of Grid.
 * <p>
 * Class Invariant:
 * 1, Field grid is always a Matrix, not a jagged array in any form
 * 2, the Number of the Cells in grid is always odd
 */
public class Grid implements IGrid {

  /**
   * The actual grid, as a 2D array.
   */
  private final ICell[][] grid;

  /**
   * The constructor of Grid.
   * Takes two Arrays of int to place holes, takes the form as below:
   * If we have grid like:
   * [Hole][Card][Hole]
   * [Card][Hole][Hole]
   * We will have holes_col and hole_row like:
   * holes_col: [0, 1, 2, 2]
   * holes_row: [1, 0, 0, 1]
   *
   * @param cols      The cols of the grid, in 0-Based form.
   * @param rows      The rows of the grid, in 0-Based form.
   * @param holes_col The col coordinates of all holes to place.
   * @param holes_row The row coordinates of all holes to place.
   * @throws IllegalArgumentException If (cols + 1) * (rows + 1) is even
   * @throws IllegalArgumentException If cols < 0 or/and rows < 0
   * @throws IllegalArgumentException If coordinates in holes_col or/and holes_row
   *                                  Exceeded col and/or row, or < 0.
   */
  public Grid(int cols, int rows, int[] holes_col, int[] holes_row) {

    if (cols == 0 || rows == 0) {
      throw new IllegalArgumentException("col and row must be greater than 0");
    }

    if ((cols + 1) * (rows + 1) % 2 == 0) {
      throw new IllegalArgumentException("Total Cell number must be odd");
    }

    // Follow the form of [cols][rows]
    grid = new ICell[cols][rows];

    for (int i = 0; i < holes_col.length; i++) {
      int col = holes_col[i];
      int row = holes_row[i];

      // Check if hole coordinate valid
      if (col < 0 || col >= cols || row < 0 || row >= rows) {
        throw new IllegalArgumentException("Hole coordinates are out of grid bounds");
      }

      grid[col][row] = new RegularHole();
    }

    for (int col = 0; col < cols; col++) {
      for (int row = 0; row < rows; row++) {
        if (grid[col][row] == null) {
          grid[col][row] = new RegularCardCell();
        }
      }
    }

  }

  @Override
  public int getRowNumber() {
    return grid[0].length;
  }

  @Override
  public int getColNumber() {
    return grid.length;
  }

  @Override
  public ICell[][] getGrid() {
    return grid;
  }

  @Override
  public ICard getCard(int col, int row) {

    if(col < 0 || col >= getColNumber() || row < 0 || row >= getRowNumber()) {
      throw new IllegalArgumentException();
    }

    return grid[col][row].getCard();
  }

  @Override
  public void placeCard(int col, int row, ICard card) throws CouldNotPlaceCardException {

    if(col < 0 || col >= getColNumber() || row < 0 || row >= getRowNumber()) {
      throw new IllegalArgumentException();
    }

    grid[col][row].setCard(card);
  }
}
