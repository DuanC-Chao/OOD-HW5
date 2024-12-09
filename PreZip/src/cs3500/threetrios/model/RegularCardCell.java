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
  public void setCard(ICard card) throws IllegalStateException {
    if (card == null) {
      throw new IllegalStateException("Card cannot be null");
    }
    if (this.card != null) {
      throw new IllegalStateException("Already placed card to this Cell, Card Name: "
              + this.card.getCardName() + " Card Owner: " + this.card.getOwner());
    }
    this.card = card;
  }

  @Override
  public CellType getType() {
    return CellType.CARD_CELL;
  }

  @Override
  public boolean couldPlace() {
    return this.card == null;
  }

  @Override
  public boolean myCompare(ICell other) {
    if (other == null) {
      return false;
    }
    if (!(other instanceof RegularCardCell)) {
      return false;
    }
    RegularCardCell otherCell = (RegularCardCell) other;
    if (this.card == null) {
      return otherCell.card == null;
    }
    return this.card.equals(otherCell.card);
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
