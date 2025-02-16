package cs3500.threetrios.view;

import java.io.IOException;

import java.util.function.Consumer;
import java.util.function.Supplier;

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

  /**
   * Sets the key handler for this view.
   * @param keyHandler The key handler.
   */
  void setKeyHandler(Consumer<KeyPress> keyHandler);

  /**
   * A method, to create a popup window and show a certain message.
   *
   * @param message The message wished to show.
   */
  void showPopUp(String message);

  /**
   * Sets whether hints are enabled in this view.
   * @param enabled If hints are enabled.
   */
  void setHintsEnabled(boolean enabled);

  void setSelectedCardSupplier(Supplier<Integer> getSelectedCard);
}
