package cs3500.threetrios.view;

import cs3500.threetrios.model.EPlayer;

/**
 * A class, represents a move operation, includes the destination of move.
 */
public class Move {
  public final int play_to_col;
  public final int play_to_row;

  /**
   * The constructor of Move.
   *
   * @param play_to_col The col to play to.
   * @param play_to_row The row to play to.
   */
  public Move(int play_to_col, int play_to_row) {
    this.play_to_col = play_to_col;
    this.play_to_row = play_to_row;
  }
}
