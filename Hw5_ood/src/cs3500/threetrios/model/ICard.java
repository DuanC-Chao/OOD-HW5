package cs3500.threetrios.model;

/**
 * Represent a Card in the game.
 */
public interface ICard extends ICloneable<ICard> {

  /**
   * Set the Numbers for the card.
   *
   * @param north The north number.
   * @param east  The east number.
   * @param south The south number.
   * @param west  The west number.
   */
  void setCardNumber(CardNumber north, CardNumber east, CardNumber south, CardNumber west);

  /**
   * Takes a String and set it as Card's name.
   * If given a NULL, there will be settled "Default Name".
   *
   * @param name The name wished to assign.
   */
  void setCardName(String name);

  /**
   * Takes a card belong enum, and set it as the owner of the card.
   * @param owner The owner as a CardBelong.
   * @return Card itself to support chain calling.
   */
  ICard setOwner(EPlayer owner);

  /**
   * Return the owner of the card, as a card belong.
   * @return The owner as CardBelong.
   * @throws IllegalStateException If owner is null.
   */
  EPlayer getOwner();

  /**
   * Return the card Name.
   * @return The name of the Card.
   */
  String getCardName();

  /**
   * Return the north number of the card.
   * @return The north number.
   */
  CardNumber getNorth();

  /**
   * Return the south number of the card.
   * @return The south number.
   */
  CardNumber getSouth();

  /**
   * Return the west number of the card.
   * @return The west number.
   */
  CardNumber getWest();

  /**
   * Return the East number of the card.
   * @return The Est number.
   */
  CardNumber getEast();
}
