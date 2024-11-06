package cs3500.threetrios.model;

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
    this.card = null;
  }

  /**
   * The clone constructor.
   *
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
    if (card == null) {
      throw new IllegalArgumentException();
    }
    if (this.card != null) {
      throw new CouldNotPlaceCardException("Already placed card to this Cell, Card Name: " +
        this.card.getCardName() + " Card Owner: " + this.card.getOwner());
    }
    this.card = card;
  }

  @Override
  public CellType getType() {
    return CellType.CARD_CELL;
  }

  @Override
  public ICell makeClone() {
    if (this.card == null) {
      return new RegularCardCell();
    } else {
      return new RegularCardCell(this.card.makeClone());
    }
  }
}
