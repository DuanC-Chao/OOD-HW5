package cs3500.threetrios.view;

import java.io.IOException;

import cs3500.threetrios.model.EPlayer;

import java.util.function.Consumer;

/**
 * The interface for TripleTriad game View.
 */
public interface TripleTriadView {

  /**
   * Render the model.
   * @param out The appendable.
   */
  void render(Appendable out) throws IOException;

  /**
   * Takes a delegate from controller, which should be the method handling the move logic.
   * @param delegate The fuctional object to pass to view.
   */
  void setMoveEventHandler(Consumer<Move> delegate);

  /**
   * An Inner class, represents a move operation, includes which card to move, and the destination.
   */
  class Move {
    public final EPlayer player;
    public final int cardIdx;
    public final int play_to_col;
    public final int play_to_row;

    /**
     * The constructor of Move.
     * @param player The player of chosen.
     * @param cardIdx The card index in player's hand/
     * @param play_to_col The col to play to.
     * @param play_to_row The row to play to.
     */
    public Move(EPlayer player, int cardIdx, int play_to_col, int play_to_row) {
      this.player = player;
      this.cardIdx = cardIdx;
      this.play_to_col = play_to_col;
      this.play_to_row = play_to_row;
    }
  }
}
