package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The functional class for player.
 */
public class Player implements IPlayer {

  /**
   * The identity of the player.
   */
  private EPlayer identity;

  /**
   * The hand of the player.
   */
  private List<ICard> hand;

  /**
   * The backup for hand, as the original state of hand At the start of game.
   */
  private List<ICard> backupHand;

  /**
   * The player's name.
   */
  private String name;

  /**
   * Constructor that takes an identity and a name.
   *
   * @param identity The player's identity.
   * @param name     The player's name.
   */
  public Player(EPlayer identity, String name) {
    this.identity = identity;
    this.name = name;
    this.hand = new ArrayList<>();
    this.backupHand = new ArrayList<>();
  }

  /**
   * The Constructor used to clone.
   *
   * @param identity The identity.
   * @param name     The name.
   * @param hand     The hand.
   */
  public Player(EPlayer identity, String name, List<ICard> hand) {
    this.identity = identity;
    this.name = name;
    this.hand = hand;
    this.backupHand = new ArrayList<>();
    for (ICard card : hand) {
      this.backupHand.add(card.makeClone());
    }
  }

  @Override
  public IPlayer makeClone() {
    return new Player(identity, name, hand);
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public EPlayer getIdentity() {
    return this.identity;
  }

  @Override
  public void setHand(List<ICard> deck, int numToDraw) {

    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null");
    }

    if (deck.size() < numToDraw) {
      throw new IllegalArgumentException("Deck size cannot be less than " + numToDraw);
    }

    // Add clone, not cards reference in deck.
    int acc = 0;
    while (this.hand.size() < numToDraw) {
      this.hand.add(deck.get(acc++).makeClone().setOwner(identity));
    }
    acc = 0;
    while (this.backupHand.size() < numToDraw) {
      this.backupHand.add(deck.get(acc++).makeClone().setOwner(identity));
    }
  }

  @Override
  public List<ICard> getHand() {
    return hand;
  }

  @Override
  public List<ICard> getOriginalHand() {
    return backupHand;
  }

  @Override
  public ICard popCardFromHand(int cardIdx) {
    return hand.remove(cardIdx);
  }
}
