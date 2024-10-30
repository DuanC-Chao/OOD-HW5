package cs3500.threetrios.view;

import java.io.IOException;

/**
 * The interface for TripleTriad game View.
 */
public interface TripleTriadView {

  /**
   * Render the model.
   * @param out The appendable.
   */
  void render(Appendable out) throws IOException;
}
