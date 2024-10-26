package cs3500.threetrios.model.Cells;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Exception.CouldNotPlaceCardException;

/**
 * Represents a hole, which is a cell that could not hold card.
 */
public class RegularHole implements ICell {

  /**
   * The default constructor.
   */
  public RegularHole() {

  }

  @Override
  public ICard getCard() {
    return null;
  }

  @Override
  public void setCard(ICard card) throws CouldNotPlaceCardException {

  }
}