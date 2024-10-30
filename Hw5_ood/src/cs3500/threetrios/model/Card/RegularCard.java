package cs3500.threetrios.model.Card;

import cs3500.threetrios.model.Enums.EPlayer;
import cs3500.threetrios.model.Enums.CardNumber;
import cs3500.threetrios.model.Enums.Direction;
import cs3500.threetrios.model.Rule.ICombatRule;

public class RegularCard implements ICard {

  /**
   * The north number of the card.
   */
  private CardNumber north;

  /**
   * The south number of the card.
   */
  private CardNumber south;

  /**
   * The east number of the card.
   */
  private CardNumber east;

  /**
   * The west number of the card.
   */
  private CardNumber west;

  /**
   * The name of the card.
   */
  private String name;

  /**
   * The owner of the card.
   */
  private EPlayer owner;

  /**
   * Default constructor.
   */
  public RegularCard() {
    this.owner = null;
    this.name = "Default Name";
  }

  /**
   * The Clone constructor.
   * @param north The north number of the card.
   * @param south The south number of the card.
   * @param east The east number of the card.
   * @param west The west number of the card.
   * @param name The name.
   * @param owner The owner.
   */
  public RegularCard(CardNumber north, CardNumber south, CardNumber east,
                     CardNumber west, String name, EPlayer owner) {
    this.north = north;
    this.south = south;
    this.east = east;
    this.west = west;
    this.name = name;
    this.owner = owner;
  }

  @Override
  public void setCardNumber(CardNumber north, CardNumber south, CardNumber east, CardNumber west) {
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }

  @Override
  public void setCardName(String name) {
    if (name == null) {
      return;
    }
    this.name = name;
  }

  @Override
  public int compare(ICard other, Direction direction, ICombatRule rule) {
    switch (direction) {
      case NORTH:
        return rule.compare(this.north, other.getSouth());
      case SOUTH:
        return rule.compare(this.south, other.getNorth());
      case EAST:
        return rule.compare(this.east, other.getWest());
      case WEST:
        return rule.compare(this.west, other.getEast());
      default:
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }
  }

  @Override
  public void setOwner(EPlayer owner) {

  }

  @Override
  public EPlayer getOwner() {
    return null;
  }

  @Override
  public CardNumber getNorth() {
    return this.north;
  }

  @Override
  public CardNumber getSouth() {
    return this.south;
  }

  @Override
  public CardNumber getWest() {
    return this.west;
  }

  @Override
  public CardNumber getEast() {
    return this.east;
  }

  @Override
  public ICard makeClone() {
    return new RegularCard(north, south, east, west, name, owner);
  }
}
