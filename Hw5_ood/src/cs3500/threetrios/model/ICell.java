package cs3500.threetrios.model;

/**
 * Represent a Cell on the Grid.
 */
public interface ICell extends ICloneable<ICell> {

  /**
   * Get the current card on the cell.
   * @return The card got, if no card exist, or Cell is Hole, return Null.
   */
  public ICard getCard();

  /**
   * Takes an ICard and set as the Cell's card.
   * throws CouldNotPlaceCardException If Already Exist a card in the Cell or Cell is a Hole.
   * @param card The card wished to settle.
   * @throws IllegalArgumentException If Card is null.
   */
  public void setCard(ICard card);

  /**
   * Get the type of the cell.
   * @return The cell type.
   */
  public CellType getType();

}
