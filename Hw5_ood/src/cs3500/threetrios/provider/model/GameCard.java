package cs3500.threetrios.provider.model;

import cs3500.threetrios.model.CardNumber;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;

/**
 * The adapted GameCard, adapt IGameCard with ICard.
 */
public class GameCard implements IGameCard {

  /**
   * The adapted card number, to store card number of each directions.
   * Could be used directly by provider's model.
   */
  public class AdaptedCardNumber {

    private CardNumber number;

    public AdaptedCardNumber(CardNumber number) {
      this.number = number;
    }

    public String getAbbrev() {
      return this.number.toString();
    }
  }

  /**
   * The adapted ICard object.
   */
  private ICard innerCard;

  public AdaptedCardNumber north;

  public AdaptedCardNumber south;

  public AdaptedCardNumber west;

  public AdaptedCardNumber east;

  /**
   * The constructor, takes an ICard and set it as innerCard.
   * @param innerCard The adapted inner card.
   */
  public GameCard(ICard innerCard) {
    this.innerCard = innerCard;

    this.north = new AdaptedCardNumber(innerCard.getNorth());
    this.south = new AdaptedCardNumber(innerCard.getSouth());
    this.west = new AdaptedCardNumber(innerCard.getWest());
    this.east = new AdaptedCardNumber(innerCard.getEast());
  }

  /**
   * Get the owner of this GameCard as an EPlayer.
   * @return The owner.
   */
  public EPlayer getCardOwner() {
    return this.innerCard.getOwner();
  }

}
