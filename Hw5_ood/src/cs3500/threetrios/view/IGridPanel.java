package cs3500.threetrios.view;

import java.util.function.Consumer;

/**
 * The Interface for a Grid Panel.
 * Grid Panel is the panel that shows the status of gameGrid.
 */
public interface IGridPanel extends Refreshable, Discolorable {

  /**
   * Takes a delegate, and to dispatch.
   * @param delegate The delegate to be dispatched.
   */
  void takeToBeDispatchedDelegate(Consumer<Move> delegate);
}
