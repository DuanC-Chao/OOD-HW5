package cs3500.threetrios.model.Grid;

import cs3500.threetrios.model.Card.Card;
import cs3500.threetrios.model.Cells.CardCell;
import cs3500.threetrios.model.Cells.CellType;
import cs3500.threetrios.model.Cells.Hole;
import cs3500.threetrios.model.Cells.ICell;
import java.util.List;
public class Grid implements IGrid{
  private final ICell[][] grid;
  private final int rows;
  private final int cols;

  // Class invariant: The number of CardCells must always be odd.
  public Grid(int rows, int cols, List<List<CellType>> cellTypes) {
    this.rows = rows;
    this.cols = cols;
    grid = new ICell[rows][cols];

    int cardCellCount = 0; // Track the number of CardCells

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (cellTypes.get(row).get(col) == CellType.CARD_CELL) {
          grid[row][col] = new CardCell();
          cardCellCount++;
        } else {
          grid[row][col] = new Hole();
        }
      }
    }

    // Enforce class invariant
    if (cardCellCount % 2 == 0) {
      throw new IllegalArgumentException("The grid must have an odd number of card cells.");
    }
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public int getCols() {
    return cols;
  }

  @Override
  public boolean isCellEmpty(int row, int col) {
    return false;
  }

  @Override
  public CellType getCellType(int row, int col) {
    return null;
  }

  @Override
  public Card getCard(int row, int col) {
    return null;
  }

  @Override
  public void placeCard(Card card, int row, int col) {

  }

  @Override
  public boolean isValidMove(int row, int col) {
    return false;
  }
}
