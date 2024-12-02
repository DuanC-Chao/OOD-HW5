package cs3500.threetrios.provider.model;

import cs3500.threetrios.model.ICard;

/**
 * The adapted GameCard, adapt IGameCard with ICard.
 */
public class GameCard implements IGameCard {

  /**
   * The adapted ICard object.
   */
  private ICard innerCard;

  /**
   * The constructor, takes an ICard and set it as innerCard.
   * @param innerCard The adapted inner card.
   */
  public GameCard(ICard innerCard) {
    this.innerCard = innerCard;
  }


}
