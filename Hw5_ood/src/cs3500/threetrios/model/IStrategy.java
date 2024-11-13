package cs3500.threetrios.model;

/**
 * Represent a Strategy for Bot to perform.
 */
public interface IStrategy {

  /**
   * Takes a play and a model, adjust that play's score.
   * Adjustment to Score is based on This Strategy's inner logic.
   * @param model The model of game.
   * @param play The play to be adjust score with.
   */
  void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play);
}
