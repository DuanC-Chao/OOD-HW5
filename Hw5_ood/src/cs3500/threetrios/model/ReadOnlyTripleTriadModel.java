package cs3500.threetrios.model;

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
}
