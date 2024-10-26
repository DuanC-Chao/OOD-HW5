package cs3500.threetrios.model.Game;

import java.util.AbstractMap;
import java.util.List;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Player.IPlayer;

/**
 * The main Interface for the Triple Triad Game.
 */
public interface ITripleTriadGame {

  /**
   * The main method to start a Game.
   *
   * @param row The wished row number of the grid.
   * @param col The wished column number of the grid.
   * @param p1  Player one.
   * @param p2  Player two.
   * @throws IllegalArgumentException If col * row is not odd number.
   * @throws IllegalStateException If the Game is already started or over.
   * @throws IllegalArgumentException If two players are the same instance.
   */
  void startGame(int col, int row, IPlayer p1, IPlayer p2);

  /**
   * Play A card from player's hand to a position on the grid.
   *
   * @param playerID   The ID of the player of choose.
   * @param cardID     The CardID on player's hand.
   * @param col_toPlay The column to play the card to.
   * @param row_toPlay The row to play the card to.
   * @throws IllegalArgumentException If the playerID, CardID,
   *                                  col or row is invalid or less than 0.
   * @throws IllegalStateException If the game is not started or already over.
   */
  void playToGrid(int playerID, int cardID, int col_toPlay, int row_toPlay);

  /**
   * Get a List of ICard, which is the Player One's hand.
   * @return A List of ICard.
   */
  List<ICard> getPlayerOneHand();

  /**
   * Get Player two's hand.
   * @return A List of ICard.
   */
  List<ICard> getPlayerTwoHand();

  /**
   * return a boolean, represents weather if the game is over.
   * @return A boolean.
   */
  boolean isGameOver();

  /**
   * Check if P1 won the game.
   * @return A boolean.
   */
  boolean isPlayerOneWon();

  /**
   * Check if P2 won the game.
   * @return A boolean.
   */
  boolean isPlayerTwoWon();

  /**
   * Check weather the game is draw.
   * @return A boolean.
   */
  boolean isDraw();

  /**
   * Get the grid.
   * @return A 2D array, representing the grid.
   */
  ICard[][] getGrid();
}
