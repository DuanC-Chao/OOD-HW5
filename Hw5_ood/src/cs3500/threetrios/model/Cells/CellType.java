package cs3500.threetrios.model.Cells;

import cs3500.threetrios.model.Card.Card;

public enum CellType implements ICell{
  HOLE,
  CARD_CELL;

  @Override
  public boolean isOccupied() {
    return false;
  }

  @Override
  public Card getCard() {
    return null;
  }

  @Override
  public void setCard(Card card) {

  }
}
