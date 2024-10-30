package cs3500.threetrios.model;

import java.util.List;

/**
 * The main Interface of Player.
 */
public interface IPlayer extends ICloneable<IPlayer> {

  /**
   * Takes a name, and set it as player's name.
   * @param name The name to set.
   * @throws IllegalArgumentException If name is null or empty string.
   */
  void setName(String name);

  /**
   * Get the name of the player.
   * @return The name of the player.
   */
  String getName();

  /**
   * Get a player's identity as an EPlayer.
   * @return Player's identity.
   */
  EPlayer getIdentity();

  /**
   * Takes a list of card, and draw for player's hand with those cards.
   * @param deck The list of card as deck.
   * @param numToDraw  The number of cards to draw for hand.
   * @throws IllegalArgumentException If number of cards in deck is less than numToDraw.
   * @throws IllegalArgumentException If deck is empty or null.
   */
  void setHand(List<ICard> deck, int numToDraw);

  /**
   * Return the player's hand, which is a List fo Card.
   * @return A List of Card.
   */
  List<ICard> getHand();

  /**
   * Takes a 0-based card index from hand, and pop the card from the hand.
   * Pop means return the card, and remove the card from hand.
   * @param cardIdx The 0-based index of the card.
   * @return A card.
   */
  ICard popCardFromHand(int cardIdx);
}
