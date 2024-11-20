package cs3500.threetrios.model;

/**
 * The Interface represents a Grid.
 * In the grid, we map logical coordinate to physical coordinate as:
 * [0, 0] -> Down left corner
 * [maxCol, 0] -> Down right corner
 * [0, maxRow] -> Top left corner
 * [maxCol, maxRow] -> Top right corner
 */
public interface IGrid extends ICloneable<IGrid> {

  /**
   * Gets the total number of rows of the grid, start from 1.
   *
   * @return An int, the row number.
   */
  public int getRowNumber();

  /**
   * Gets the total number of cols, start from 1.
   *
   * @return An int, the col number.
   */
  public int getColNumber();

  /**
   * Get the grid as a ICell 2D array.
   * The grid returned is serialized and Deep-Copied.
   * Changes on the resulted grid will never affect the prototype.
   * @return A 2D array, as the grid.
   */
  public ICell[][] getGrid();

  /**
   * Takes two 0-Based int, as col and row, Gets the card on the cell of [col, row].
   *
   * @param col The 0-Based col to operate.
   * @param row The 0-Based row to operate.
   * @return An ICard, which is the card got, or NULL, if Cell is empty or hole.
   * @throws IllegalArgumentException If col > maxCol or/and row > maxRow (0-Based)
   *                                  or row or/and col < 0
   */
  public ICard getCard(int col, int row);

  /**
   * Takes two 0-Based int, as col and row, gets the ICell on that coordinate.
   * If col or row is out of bound, return null instead of throwing exception.
   * @param col The 0-Based col to operate.
   * @param row The 0-Based row to operate.
   * @return An ICell.
   */
  public ICell getCell(int col, int row);

  /**
   * Takes two 0-Based int, as col and row, and an ICard, try to place the card.
   * To the cell at [col, row].
   * throws CouldNotPlaceCardException If the cell wished to place card to is hole, or is full.
   *
   * @param col  The 0-Based col to operate.
   * @param row  The 0-Based row to operate.
   * @param card The ICard wished to place.
   * @throws IllegalArgumentException   If col > maxCol or/and row > maxRow (0-Based)
   *                                    or row or/and col < 0
   */
  public void placeCard(int col, int row, ICard card);

  /**
   * Takes 0-Based coordinate of starting card and a rule, start filpping with the rule.
   * @param col the col of start card, 0-based.
   * @param row the row of start card, 0-based.
   * @param rule The rule wished to use.
   */
  public void flip(int col, int row, ICombatRule rule);

  /**
   * Takes a 0-Based coordinate, and check if could place card on the Cell of that coordinate.
   * @param col The col to check.
   * @param row The row to check.
   * @return A boolean represent weather could place card.
   */
  public boolean isLegalMove(int col, int row);

  /**
   * Takes a o-Based coordinate, and return the owner of the card on that coord.
   * @param col The col to check.
   * @param row The row to check.
   * @return An EPlayer, represent the owner of the card, return null if no card exist.
   * @throws IllegalArgumentException If col or row is illegal.
   */
  public EPlayer getCardOwner(int col, int row);

}
