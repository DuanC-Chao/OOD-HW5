package cs3500.threetrios.view;

import cs3500.threetrios.model.ICard;

/**
 * The base interface of CardButtons.
 */
public interface ICardButton extends Discolorable {

  /**
   * Get the card that cardButton holds.
   * @return Return the logical card within the CardButton.
   */
  ICard getCard();

  /**
   * Set the preferred Y value for the CardButton.
   * This method is meant to adjust the
   * @param yValue The wished height of the CardButton.
   */
  void setPreferredYValue(int yValue);
}
