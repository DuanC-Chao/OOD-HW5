package cs3500.threetrios.view;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.smartcardio.Card;

/**
 * The Interface for a Grid Panel.
 * Grid Panel is the panel that shows the status of gameGrid.
 */
public interface IGridPanel extends Refreshable, Discolorable {

  /**
   * Takes a delegate, and to dispatch.
   * @param delegate The delegate to be dispatched.
   */
  void takeToBeDispatchedDelegate(Consumer<Move> delegate, Runnable viewRefreshDelegate);

  /**
   * Sets whether hints are enabled in this view.
   * @param hintsEnabled If hints are enabled.
   */
  void setHintsEnabled(boolean hintsEnabled);

  void setSelectedCardSupplier(Supplier<Integer> getSelectedCard);
}
