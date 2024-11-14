package cs3500.threetrios.view;

import java.util.function.Consumer;

/**
 * The Interface for hand panel, hand panel is the conponent that holds all cards.
 * There could be two hand panel in the scene, representing each player's hand.
 */
public interface IHandPanel extends Refreshable {

  /**
   * Takes a delegate, and to dispatch to all InHandCardButton.
   * @param delegate The delegate to be dispatched.
   */
  void takeToBeDispatchedDelegate(Consumer<Pick> delegate);
}
