package cs3500.threetrios.model.Card;

import cs3500.threetrios.model.Enums.EPlayer;
import cs3500.threetrios.model.Enums.CardNumber;
import cs3500.threetrios.model.Enums.Direction;
import cs3500.threetrios.model.Player.ICloneable;
import cs3500.threetrios.model.Rule.ICombatRule;

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
   * Compare Self with Other, compares the corresponding numbers.
   * Which, EAST to WEST, NORTH to SOUTH.
   * If self > other: return 1
   * If self = other: return 0
   * If self < other: return -1
   *
   * @param other     Another ICard object.
   * @param direction A direction, which is Other's direction relative to Self.
   *                  For Example, The following relative direction will be NORTH
   *                  [Other] [xxx]
   *                  [Self] [xxx]
   * @param rule      The rule to determine the size relation between CardNumbers
   * @return An int, weather self > other or the opposite.
   */
  int compare(ICard other, Direction direction, ICombatRule rule);

  /**
   * Takes a card belong enum, and set it as the owner of the card.
   * @param owner The owner as a CardBelong.
   */
  void setOwner(EPlayer owner);

  /**
   * Return the owner of the card, as a card belong.
   * @return The owner as CardBelong.
   * @throws IllegalStateException If owner is null.
   */
  EPlayer getOwner();

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
