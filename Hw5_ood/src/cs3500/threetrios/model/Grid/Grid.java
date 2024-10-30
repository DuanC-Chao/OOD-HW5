package cs3500.threetrios.model.Grid;

import java.util.Arrays;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Cells.RegularCardCell;
import cs3500.threetrios.model.Cells.ICell;
import cs3500.threetrios.model.Cells.RegularHole;
import cs3500.threetrios.model.Enums.CellType;
import cs3500.threetrios.model.Exception.CouldNotPlaceCardException;
import cs3500.threetrios.model.Rule.ICombatRule;

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
   * Simple constructor, takes a ICell[][].
   * @param grid The grid, represent as a ICell[][].
   * @throws IllegalArgumentException If row * col is even.
   * @throws IllegalArgumentException If Card Cell number is even.
   */
  public Grid(ICell[][] grid) {
    checkIfGridLegal(grid);
    this.grid = grid;
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
    ICell[][] result = new ICell[getRowNumber()][getColNumber()];
    for (int row = 0; row < result.length; row++) {
      for (int col = 0; col < result[row].length; col++) {
        result[row][col] = grid[col][row].makeClone();
      }
    }
    return result;
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

  @Override
  public void filp(int col, int row, ICombatRule rule) {
    rule.flip(this.grid, col, row);
  }

  /**
   * Check if the grid is legal.
   * @param grid A grid, as ICell[][].
   */
  private void checkIfGridLegal(ICell[][] grid) {
    if((grid.length * grid[0].length) % 2 == 0) {
      throw new IllegalArgumentException("Total Cell number must be odd");
    }

    int cardCellNum = 0;
    for(int row = 0; row < grid.length; row++) {
      for(int col = 0; col < grid[row].length; col++) {
        ICell cell = grid[row][col];
        if(cell.getType() == CellType.CARD_CELL) {
          cardCellNum++;
        }
      }
    }
    if(cardCellNum % 2 == 0) {
      throw new IllegalArgumentException("Card Cell number must be odd");
    }
  }

}
