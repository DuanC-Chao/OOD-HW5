package cs3500.threetrios.provider.view;

/**
 * Represents a features interface for the ClassicThreeTriosGUI.
 */
public interface PlayerActions {

  /**
   * Plays to grid and updates the view accordingly.
   * @param cardIdxInHand the card played to the grid
   * @param row the row of the cell that the card is being played to
   * @param col the column of the cell that the card is being played to
   */
  void currentPlayerMakeMove(int cardIdxInHand, int row, int col);

  /**
   * Player selects a card.
   * @param cardIdxInHand the index of the selected card in the player's hand
   */
  void selectCard(int cardIdxInHand);

  /**
   * Exits the game.
   */
  void quit();
}
