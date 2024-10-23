package cs3500.threetrios.model.Cells;

import cs3500.threetrios.model.Card.Card;

public interface ICell {
  public boolean isOccupied();
  public Card getCard();
  public void setCard(Card card);

}
