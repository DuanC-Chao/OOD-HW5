package cs3500.threetrios.model.Cells;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Enums.CellType;
import cs3500.threetrios.model.Exception.CouldNotPlaceCardException;
import cs3500.threetrios.model.Player.ICloneable;

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
   * @param card The card wished to settle.
   * @throws IllegalArgumentException If Card is null.
   * @throws CouldNotPlaceCardException If Already Exist a card in the Cell or Cell is a Hole.
   */
  public void setCard(ICard card) throws CouldNotPlaceCardException;

  /**
   * Get the type of the cell.
   * @return The cell type.
   */
  public CellType getType();

}
