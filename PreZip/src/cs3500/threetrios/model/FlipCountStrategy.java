package cs3500.threetrios.model;

/**
 * Adjust a Play's Score by.
 * The number of cards it could flip.
 */
public class FlipCountStrategy implements IStrategy {

  @Override
  public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
    int flipCount;
    flipCount = model.calculateFlips(EPlayer.PLAYER_TWO, model.getPlayerHand(2).get(
      play.cardIdx), play.colToPlay, play.rowToPlay);
    play.multiplyScore(1.2, flipCount);
  }
}
