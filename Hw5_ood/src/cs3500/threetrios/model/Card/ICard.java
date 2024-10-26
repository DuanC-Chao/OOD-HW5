package cs3500.threetrios.model.Card;

import java.util.UUID;

import cs3500.threetrios.model.Enums.CardNumber;
import cs3500.threetrios.model.Enums.Direction;
import cs3500.threetrios.model.Enums.Faction;
import cs3500.threetrios.model.Rule.ICombatRule;

/**
 * Represent a Card in the game.
 */
public interface ICard {

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
   * Takes a Fiction and set it as Card's fiction.
   *
   * @param faction The fiction wished to set.
   */
  void setFaction(Faction faction);

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
   * Takes an IPlayer's UUID, and set it as the Card's owner id.
   *
   * @param owner The UUID of the owner.
   */
  void setOwner(UUID owner);
}
