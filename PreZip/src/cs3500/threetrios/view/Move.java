package cs3500.threetrios.view;

/**
 * A class, represents a move operation, includes the destination of move.
 */
public class Move {
  public final int playToCol;
  public final int playToRow;

  /**
   * The constructor of Move.
   *
   * @param playToCol The col to play to.
   * @param playToRow The row to play to.
   */
  public Move(int playToCol, int playToRow) {
    this.playToCol = playToCol;
    this.playToRow   = playToRow;
  }
}
