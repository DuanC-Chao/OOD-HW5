package cs3500.threetrios.provider.model;

import java.util.List;

/**
 * Observations for a read-only version of the ThreeTriosModel.
 */
public interface ReadOnlyThreeTriosModel {

  /**
   * Returns if the game is over as specified by the implementation.
   * @return true if the game has ended and false otherwise
   */
  boolean isGameOver();

  /**
   * Returns the color of the currently winning Player.
   * @return the color of the winning player, or null if it is a tie
   * @throws IllegalStateException if the game has not ended
   */
  PlayerColor winningPlayer();

  /**
   * Returns a copy of the given players hand in the game.
   * @param color the color associated with the player whose hand is to be copied
   * @return a new list containing the cards in the given player's
   *          hand in the same order as in the current state of the game.
   * @throws IllegalArgumentException if color is null
   */
  List<GameCard> getPlayerHand(PlayerColor color);

  /**
   * Returns the number of rows in the game grid.
   * @return the number of rows
   */
  int getNumGridRows();

  /**
   * Returns the number of columns in the game grid.
   * @return the number of columns
   */
  int getNumGridCols();

  /**
   * Returns the card at the cell corresponding to the given row and column,
   * or null if there is no card in the given cell.
   * @return the card
   */
  GameCard getCardAt(int row, int col);

  /**
   * Returns the color of the current player.
   * @return color of the current player
   */
  PlayerColor getCurrentPlayer();

  /**
   * Returns the color of the player that currently owns the given card.
   * @param card the player's card
   * @return the color of the player
   * @throws IllegalArgumentException if the given card is null or is not in either
   *          of the players hands or not on the grid (meaning the card is not a part of the game)
   */
  PlayerColor cardOwner(GameCard card);

  /**
   * Determines whether the grid cell at the given row and column is a hole.
   * @param row the row of the cell
   * @param col the row of the column
   * @return true if the cell is a hole, false if not
   * @throws IllegalArgumentException if the given row and col do not correspond
   *          to a cell in the grid
   */
  boolean isHole(int row, int col);

  /**
   * Determines the number of cards that the current player can flip if they
   * play the card at the given hand index to the grid cell at the given row and column.
   * Will return 0 if the card could not be played at the specified location for whatever reason.
   * @param cardIdxInHand the index of the card to theoretically be played
   * @param row the row of the cell to theoretically play to
   * @param col the column of the cell to theoretically play to
   * @return the number of cards the player can flip
   * @throws IllegalArgumentException if cardIdxInHand < 0 or >= to the number of cards in hand
   * @throws IllegalArgumentException if row is < 0 or >= to the number of rows in the game grid
   * @throws IllegalArgumentException if col is < 0 or >= to the number of columns in the game grid
   * @throws IllegalStateException if the cell referred to by row and col is already
   *          occupied by a card or is a hole
   */
  int howManyFlips(int cardIdxInHand, int row, int col);

  /**
   * Determines whether playing to grid with the card referred to by the given cardIdxInHand
   * to the cell at the specified row and col is a valid move.
   * @param cardIdxInHand the index of the card in hand to be checked
   * @param row the row of the cell
   * @param col the column of the cell
   * @return true if the play is valid and false if not
   */
  boolean isValidMove(int cardIdxInHand, int row, int col);

}
