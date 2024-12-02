package cs3500.threetrios.provider.model;

import cs3500.threetrios.provider.controller.ThreeTriosController;

/**
 * Behaviors for a game of ThreeTrios. Includes both observations and operations.
 * The game is played by two players who take turns placing cards on a regular grid.
 * The placement of cards on the board initiates battles, where players can win over
 * cards of the other player. The goal of the game is to end with the most cards in
 * total, including both in hand and on board, when the grid has been entirely filled.
 */
public interface ThreeTriosModel extends ReadOnlyThreeTriosModel {

  /**
   * Starts the game with the two given controllers as player1 and player2.
   * These controllers are player action listeners, in essence, this is like
   * setting the player action listeners for the model. After listeners are set,
   * the game is initiated.
   * Player1 is the red player, and takes their turn first.
   * @param player1 the red player
   * @param player2 the blue player
   * @throws IllegalArgumentException if either of the given players is null
   * @throws IllegalStateException if the game has already started
   */
  void startGame(ThreeTriosController player1, ThreeTriosController player2);

  /**
   * Play the given card from the hand to the chosen grid cell. The card is removed
   * from the hand and placed in the given cell. After the card is placed it will battle
   * against other cards which may result in cards on the grid switching ownership.
   * @param cardIdxInHand a 0-index number representing the card to play from the hand
   * @param row a 0-index number representing the row of the cell to be played to
   * @param col a 0-index number representing the col of the cell to be played to
   * @throws IllegalStateException if the game is over or the game has already started
   * @throws IllegalArgumentException if cardIdxInHand < 0 or >= to the number of cards in hand
   * @throws IllegalArgumentException if row is < 0 or >= to the number of rows in the game grid
   * @throws IllegalArgumentException if col is < 0 or >= to the number of columns in the game grid
   * @throws IllegalStateException if the cell referred to by row and col is already
   *      occupied by a card or is a hole
   */
  void playToGrid(int cardIdxInHand, int row, int col);

}
