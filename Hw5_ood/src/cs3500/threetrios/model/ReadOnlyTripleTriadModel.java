package cs3500.threetrios.model;

import java.util.List;

/**
 * The readonly interface for our game model.
 */
public interface ReadOnlyTripleTriadModel {

  /**
   * Get a clone of player one, which was deep-copied, except for ICard instance within it.
   * Modify the clone will not affect the player itself in any way.
   *
   * @return An IPlayer.
   */
  IPlayer getPlayerOneClone();

  /**
   * Get a clone of player two, which was deep-copied, except for ICard instance within it.
   * Modify the clone will not affect the player itself in any way.
   *
   * @return An IPlayer.
   */
  IPlayer getPlayerTwoClone();

  /**
   * Get the grid.
   *
   * @return A 2D array, representing the grid.
   */
  ICell[][] getGrid();

  /**
   * Get the clone of the grid as an IGrid.
   *
   * @return The clone of grid as IGrid.
   */
  IGrid getGridClone();

  /**
   * return a boolean, represents weather the game is won by any player.
   *
   * @return A boolean.
   */
  boolean isGameWon();

  /**
   * Return the name of the player of winning.
   * If no player is winning, return null.
   *
   * @return The name of winning player.
   */
  String getWinningPlayerName();

  /**
   * Calculate how many cards can be flipped if the player plays at the given coordinate.
   *
   * @param player Which player's card to play on given coordinate.
   * @param card   The card the player intends to play.
   * @param row    The row to play the card, 0-based.
   * @param col    The column to play the card, 0-based.
   * @return the number of cards that can be flipped.
   */
  int calculateFlips(EPlayer player, ICard card, int col, int row);

  /**
   * Check if it's legal for the current player to play at a given coordinate.
   *
   * @param row The row to play, 0-based.
   * @param col The column to play, 0-based.
   * @return true if legal, false otherwise.
   */
  boolean isLegalMove(int col, int row);

  /**
   * Get the contents of the specified player's hand.
   *
   * @param playerNumber The 1-based player number (1 or 2).
   * @return the list of cards in the player's hand.
   * @throws IllegalArgumentException If player number is not 1 or 2.
   */
  List<ICard> getPlayerHand(int playerNumber);

  /**
   * Determine which player owns the card in a given cell.
   *
   * @param row The row of the cell, 0-based.
   * @param col The column of the cell, 0-based.
   * @return the player that own the card on given coord.
   * @throws IllegalArgumentException If col or row are illegal.
   */
  EPlayer getCardOwner(int col, int row);

  /**
   * Get the current score of the specified player.
   *
   * @param playerNumber The 1-based player number (1 or 2).
   * @return the player's score.
   * @throws IllegalArgumentException If playerNumber is not 1 or 2.
   */
  int getPlayerScore(int playerNumber);

  /**
   * Takes a 0-Based coordinate, and check weather that coord is on a corner of the grid.
   * Return 1 for the upper-right corner.
   * Return 2 for the lower-right corner.
   * Return 3 for the lower-left corner.
   * Return 4 for the upper-left corner.
   * Return 0 for non-corner.
   *
   * @param col The col to check.
   * @param row The row to check.
   * @return An int represents weather coord is a corner.
   */
  int isCorner(int col, int row);

  /**
   * Return the current using combat rule.
   *
   * @return The rule.
   */
  ICombatRule getCombatRule();
}
