package cs3500.threetrios.model.Grid;

import cs3500.threetrios.model.Card.Card;
import cs3500.threetrios.model.Cells.CellType;

public interface IGrid {
  public int getRows();
  public int getCols();
  public boolean isCellEmpty(int row, int col);
  public CellType getCellType(int row, int col);
  public Card getCard(int row, int col);
  public void placeCard (Card card, int row, int col);
  public boolean isValidMove(int row, int col);

}
