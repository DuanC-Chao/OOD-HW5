package cs3500.threetrios.model;

/**
 * A class representing a play.
 * A play contains a card to play to grid, and the destination, and a score.
 * Score represents the value of that play.
 */
public class Play {

  final int cardIdx;

  final int colToPlay;

  final int rowToPlay;

  double score;

  /**
   * Construct a Play, which includes the card to play, and index to play to.
   *
   * @param cardIdx   Card index on Bot's hand, 0-Based.
   * @param colToPlay The column to play to.
   * @param rowToPlay The row to play to.
   */
  public Play(int cardIdx, int colToPlay, int rowToPlay) {
    this.cardIdx = cardIdx;
    this.colToPlay = colToPlay;
    this.rowToPlay = rowToPlay;
    this.score = 10;
  }

  /**
   * Add the score with given amount.
   *
   * @param scoreOffset The amount to add.
   */
  public void addScore(double scoreOffset) {
    this.score += scoreOffset;
  }

  /**
   * Multiple iteration times, with index.
   *
   * @param index     The index.
   * @param iteration The iteratiom time.
   */
  public void multiplyScore(double index, int iteration) {
    for (int i = 0; i < iteration; i++) {
      this.score *= index;
    }
  }
}
