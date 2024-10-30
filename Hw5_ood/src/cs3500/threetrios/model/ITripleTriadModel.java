package cs3500.threetrios.model;

import cs3500.threetrios.model.Card.ICard;
import cs3500.threetrios.model.Cells.ICell;
import cs3500.threetrios.model.Exception.CouldNotPlaceCardException;
import cs3500.threetrios.model.Exception.NoSuchConfigException;
import cs3500.threetrios.model.Exception.NotYourTurnException;
import cs3500.threetrios.model.Player.IPlayer;
import cs3500.threetrios.model.Rule.ICombatRule;

/**
 * The main Interface for the Triple Triad Game.
 */
public interface ITripleTriadModel {

  /**
   * The main method to start a Game.
   *
   * @param boardConfigPath The path of board configuration file.
   * @param cardConfigPath  The path of card configuration file.
   * @param playerOneName   The name of Player one.
   * @param playerTwoName   The name of Player two.
   * @param shuffle         If to shuffle card sets players going to pick from.
   * @param rule            The rule wished to apply for the game.
   * @throws IllegalArgumentException If col * row is not odd number.
   * @throws IllegalStateException    If the Game is already started or over.
   * @throws IllegalArgumentException If players' names are the same.
   * @throws IllegalArgumentException If any of player's name is null.
   * @throws IllegalArgumentException If any of config name is null or empty String.
   * @throws NoSuchConfigException If board or card config path is invalid.
   */
  void startGame(String boardConfigPath, String cardConfigPath, String playerOneName,
                 String playerTwoName, boolean shuffle, ICombatRule rule);

  /**
   * Play A card from player's hand to a position on the grid.
   *
   * @param playerNumber The 1-based player number representation, which could only be 1 or 2.
   * @param cardIndex    The 0-based CardIndex on player's hand.
   * @param col_toPlay   The column to play the card to, 0-based.
   * @param row_toPlay   The row to play the card to, 0-based.
   * @throws IllegalArgumentException If the playerNumber, CardIndex,
   *                                  col or row is invalid or less than 0.
   * @throws IllegalStateException    If the game is not started or already over.
   * @throws NotYourTurnException     If trying to play at the wrong turn.
   * @throws CouldNotPlaceCardException If the coordinate trying to place card is full or is Hole.
   */
  void playToGrid(int playerNumber, int cardIndex, int col_toPlay, int row_toPlay);

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
