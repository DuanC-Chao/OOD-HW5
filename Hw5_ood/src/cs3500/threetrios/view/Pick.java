package cs3500.threetrios.view;

import cs3500.threetrios.model.EPlayer;

/**
 * Represent a pick, which should be passing the action of "Clicking a hand card".
 */
public class Pick {

  public final EPlayer player;

  public final int cardIdx;

  public Pick(EPlayer player, int cardIdx) {
    this.player = player;
    this.cardIdx = cardIdx;
  }
}
