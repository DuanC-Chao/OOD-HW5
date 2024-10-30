package cs3500.threetrios.model.Cells;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Enums.CellType;
import cs3500.threetrios.model.Exception.CouldNotPlaceCardException;

/**
 * The CardCell, which is the cell that could hold a card.
 */
public class RegularCardCell implements ICell {

  /**
   * Represent the card this Cell currently holds.
   */
  private ICard card;

  /**
   * The default constructor.
   */
  public RegularCardCell() {

  }

  /**
   * The clone constructor.
   * @param card The card.
   */
  public RegularCardCell(ICard card) {
    this.card = card;
  }

  @Override
  public ICard getCard() {
    return card;
  }

  @Override
  public void setCard(ICard card) throws CouldNotPlaceCardException {
    if(card == null) {
      throw new IllegalArgumentException();
    }
    if(this.card != null) {
      throw new CouldNotPlaceCardException("Already placed card to this Cell");
    }
    this.card = card;
  }

  @Override
  public CellType getType() {
    return CellType.CARD_CELL;
  }

  @Override
  public ICell makeClone() {
    return new RegularCardCell(this.card.makeClone());
  }
}
