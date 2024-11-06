package cs3500.threetrios.model;

/**
 * The main Interface for the Triple Triad Game.
 */
public interface ITripleTriadModel extends ReadOnlyTripleTriadModel{

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
   * @param colToPlay   The column to play the card to, 0-based.
   * @param rowToPlay   The row to play the card to, 0-based.
   * @throws IllegalArgumentException If the playerNumber, CardIndex,
   *                                  col or row is invalid or less than 0.
   * @throws IllegalStateException    If the game is not started or already over.
   * @throws NotYourTurnException     If trying to play at the wrong turn.
   * @throws CouldNotPlaceCardException If the coordinate trying to place card is full or is Hole.
   */
  void playToGrid(int playerNumber, int cardIndex, int colToPlay, int rowToPlay);
}
