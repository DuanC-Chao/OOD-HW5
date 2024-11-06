package cs3500.threetrios.model;

/**
 * The functional class for RegularCard.
 */
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
  public ICard setOwner(EPlayer owner) {
    this.owner = owner;
    return this;
  }

  @Override
  public EPlayer getOwner() {
    return this.owner;
  }

  @Override
  public String getCardName() {
    return this.name;
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
