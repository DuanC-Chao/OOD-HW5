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
   *
   * @param out The appendable.
   */
  void render(Appendable out) throws IOException;

  /**
   * Takes Two delegates from controller, which are methods handling selecting and playing card.
   *
   * @param handCardDelegate The delegate to be passed to hand Cards.
   * @param cellDelegate     The delegate to be passed to Cells.
   */
  void setMoveEventHandler(Consumer<Pick> handCardDelegate, Consumer<Move> cellDelegate);
}
