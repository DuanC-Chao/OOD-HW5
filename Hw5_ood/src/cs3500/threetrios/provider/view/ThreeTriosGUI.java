package cs3500.threetrios.provider.view;

import cs3500.threetrios.provider.model.PlayerColor;

/**
 * Behaviors for rendering a GUI of a ThreeTrios model.
 */
public interface ThreeTriosGUI {

  /**
   * Makes this GUI visible when given true, hides it otherwise.
   * @param show whether the GUI should be visible or not
   */
  void display(boolean show);

  /**
   * Sets the features for this interface.
   * @param features the features
   */
  void setFeatureListeners(PlayerActions features);

  /**
   * Refreshes all components of the UI including the hands, title, and grid.
   */
  void refresh();

  /**
   * Select a card in the player's hand.
   */
  void selectCard(PlayerColor color, int cardIdxInHand);

  /**
   * Sets the title of the gui.
   * @param msg the message to be displayed in the title
   */
  void setFrameTitle(String msg);

  /**
   * Creates a pop-up with the given message.
   * @param msg the message to be displayed by the pop-up
   */
  void popUpMsg(String msg);

}
