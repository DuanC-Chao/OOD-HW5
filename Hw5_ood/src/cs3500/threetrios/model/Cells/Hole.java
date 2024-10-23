package cs3500.threetrios.model.Cells;

import cs3500.threetrios.model.Card.Card;

public class Hole implements ICell{
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
