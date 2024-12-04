package cs3500.threetrios.provider.view;

import java.io.IOException;
import java.util.function.Consumer;

import cs3500.threetrios.view.Move;
import cs3500.threetrios.view.Pick;
import cs3500.threetrios.view.TripleTriadView;

/**
 * This class adapt provider's view to our view.
 */
public class ReverseAdaptedView implements TripleTriadView {

  private ThreeTriosGUI innerView;

  public ReverseAdaptedView(ThreeTriosGUI innerView) {
    this.innerView = innerView;
  }

  @Override
  public void render(Appendable out) throws IOException {
    innerView.refresh();
  }

  @Override
  public void setMoveEventHandler(Consumer<Pick> handCardDelegate, Consumer<Move> cellDelegate) {
    throw new UnsupportedOperationException("Method not supported");
  }

  @Override
  public void showPopUp(String message) {
    innerView.popUpMsg(message);
  }
}
