package cs3500.threetrios.view;

import java.util.function.Consumer;

/**
 * The Interfaace for HandCardButton that is in a Player Hand panel.
 */
public interface IinHandCardButton extends ICardButton {

  /**
   * Get the card's index in hand.
   *
   * @return An int as card index.
   */
  int getCardIndex();

  /**
   * Takes a Delegate that takes a Pick as variable.
   * The Delegate should be passed from Controller.
   * @param delegate The picking delegate.
   */
  void passPickDelegate(Consumer<Pick> delegate);
}
