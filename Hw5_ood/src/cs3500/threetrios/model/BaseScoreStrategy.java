package cs3500.threetrios.model;

/**
 * The base score strategy, its purpose is simple.
 * To give the play it takes a base score of 10.
 */
public class BaseScoreStrategy implements IStrategy {

  @Override
  public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
    play.addScore(10);
  }
}
