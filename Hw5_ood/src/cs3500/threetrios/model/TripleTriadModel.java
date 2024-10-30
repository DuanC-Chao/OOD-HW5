package cs3500.threetrios.model;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static cs3500.threetrios.model.BoardConfigReader.loadGridConfig;
import static cs3500.threetrios.model.CardConfigReader.loadCardConfig;

/**
 * The functional class for the TripleTriad Model.
 */
public class TripleTriadModel implements ITripleTriadModel {

  /**
   * Represent the current game status.
   */
  private GameStatus gameStatus;

  /**
   * The player one.
   */
  private IPlayer playerOne;

  /**
   * The player two.
   */
  private IPlayer playerTwo;

  /**
   * Represent the game grid.
   */
  private IGrid gameGrid;

  /**
   * The rule for the game.
   */
  private ICombatRule rule;

  /**
   * The current turn.
   */
  private EPlayer currentTurn;

  /**
   * The default constructor.
   */
  public TripleTriadModel() {
    this.gameStatus = GameStatus.GAME_NOT_STARTED;
  }


  @Override
  public void startGame(String boardConfigPath, String cardConfigPath, String playerOneName, String playerTwoName, boolean shuffle, ICombatRule rule) {

    // Make sure game is not started.
    assertGameNotStarted();

    // Make sure player names are not same or null
    if (playerOneName == null || playerTwoName == null) {
      throw new IllegalArgumentException("playerOneName and playerTwoName cannot be null");
    }

    if (playerOneName.equals(playerTwoName)) {
      throw new IllegalArgumentException("playerOneName and playerTwoName cannot be the same");
    }

    // Make sure config file path are not null or empty
    if(boardConfigPath == null || boardConfigPath.isEmpty()) {
      throw new IllegalArgumentException("boardConfigPath cannot be null or empty");
    }

    if(cardConfigPath == null || cardConfigPath.isEmpty()) {
      throw new IllegalArgumentException("cardConfigPath cannot be null or empty");
    }

    // Try to get grid from config file.
    ICell[][] gridToAdd;
    try {
      gridToAdd = loadGridConfig(boardConfigPath);
    } catch (IOException e) {
      throw new NoSuchConfigException("Could not load grid config: " + boardConfigPath);
    }

    // Construct gameGrid
    this.gameGrid = new Grid(gridToAdd);

    // Try to get deck from config file.
    List<ICard> deckToAdd;
    try {
      deckToAdd = loadCardConfig(cardConfigPath);
    } catch (IOException e) {
      throw new NoSuchConfigException("Could not load card config: " + cardConfigPath);
    }

    // Construct players
    this.playerOne = new Player(EPlayer.PLAYER_ONE, playerOneName);
    this.playerTwo = new Player(EPlayer.PLAYER_TWO, playerTwoName);

    // Draw for player hand
    int drawCardNum = ((gridToAdd.length * gridToAdd[0].length) + 1) / 2;

    if (shuffle) {
      Collections.shuffle(deckToAdd);
    }

    this.playerOne.setHand(deckToAdd, drawCardNum);

    if (shuffle) {
      Collections.shuffle(deckToAdd);
    }

    this.playerTwo.setHand(deckToAdd, drawCardNum);

    // Set rule
    this.rule = rule;

    // Set the turn as Player one's turn
    this.currentTurn = EPlayer.PLAYER_ONE;

    // Set game as started
    markAsGameStart();

  }

  @Override
  public void playToGrid(int playerNumber, int cardIndex, int col_toPlay, int row_toPlay) {

    playToGridVariantChecker(playerNumber, cardIndex, col_toPlay, row_toPlay);

    IPlayer playerToOperate;

    if (playerNumber == 1) {
      playerToOperate = this.playerOne;
    } else {
      playerToOperate = this.playerTwo;
    }

    // Place card to grid, and start flipping with current rule.
    this.gameGrid.placeCard(col_toPlay, row_toPlay, playerToOperate.popCardFromHand(cardIndex));
    this.gameGrid.filp(col_toPlay, row_toPlay, this.rule);

    // After flipped, recalculate winning status
    calculateAndSetWinningStatus();

    // Proceed to next turn
    nextTurn();
  }

  /**
   * A helper method, check validity of variants for playToGrid().
   *
   * @param playerNumber Number of player.
   * @param cardIndex    Index of card, 0-based.
   * @param col_toPlay   Col to play to, 0-based.
   * @param row_toPlay   Row to play to, 0-based.
   */
  private void playToGridVariantChecker(int playerNumber, int cardIndex, int col_toPlay, int row_toPlay) {
    //First, check if variables are legal.
    assertGameInProgress();
    if (playerNumber != 1 && playerNumber != 2) {
      throw new IllegalArgumentException("Invalid player number");
    }
    if (cardIndex < 0) {
      throw new IllegalArgumentException("Invalid card index");
    }
    if (playerNumber == 1) {
      if (cardIndex >= playerOne.getHand().size()) {
        throw new IllegalArgumentException("Invalid card index");
      }
    } else {
      if (cardIndex >= playerTwo.getHand().size()) {
        throw new IllegalArgumentException("Invalid card index");
      }
    }
    if (col_toPlay >= this.gameGrid.getColNumber()) {
      throw new IllegalArgumentException("Invalid column number");
    }
    if (row_toPlay >= this.gameGrid.getRowNumber()) {
      throw new IllegalArgumentException("Invalid row number");
    }

    //Check if turn is right
    if (playerNumber == 1 && this.currentTurn == EPlayer.PLAYER_TWO) {
      throw new NotYourTurnException("Current turn is P2");
    }
    if (playerNumber == 2 && this.currentTurn == EPlayer.PLAYER_ONE) {
      throw new NotYourTurnException("Current turn is P1");
    }
  }

  @Override
  public IPlayer getPlayerOneClone() {
    return playerOne.makeClone();
  }

  @Override
  public IPlayer getPlayerTwoClone() {
    return playerTwo.makeClone();
  }

  @Override
  public ICell[][] getGrid() {
    return this.gameGrid.getGrid();
  }

  @Override
  public boolean isGameWon() {
    return this.gameStatus == GameStatus.PLAYER_ONE_WIN ||
      this.gameStatus == GameStatus.PLAYER_TWO_WIN;
  }

  @Override
  public String getWinningPlayerName() {
    if (this.gameStatus == GameStatus.PLAYER_ONE_WIN) {
      return playerOne.getName();
    } else if (this.gameStatus == GameStatus.PLAYER_TWO_WIN) {
      return playerTwo.getName();
    }
    return null;
  }

  /**
   * This method check if any of the player won the game, and change gamestatus.
   *
   * @throws IllegalStateException If encountered card with unknown owner.
   */
  private void calculateAndSetWinningStatus() {
    int playerOneCardNum = 0;
    int playerTwoCardNum = 0;
    int cardCellNum = 0;

    ICell[][] gridToCheck = this.gameGrid.getGrid();

    for (int i = 0; i < gridToCheck.length; i++) {
      for (int j = 0; j < gridToCheck[i].length; j++) {
        ICell cell = gridToCheck[i][j];
        if (cell.getType() == CellType.CARD_CELL) {
          cardCellNum++;
        }
        if (cell.getCard() != null) {
          if (cell.getCard().getOwner() == EPlayer.PLAYER_ONE) {
            playerOneCardNum++;
          } else if (cell.getCard().getOwner() == EPlayer.PLAYER_TWO) {
            playerTwoCardNum++;
          } else {
            throw new IllegalStateException("Unknown card owner: " + cell.getCard().getOwner());
          }
        }
      }
    }
    if (cardCellNum != 0) {
      if (playerOneCardNum + playerTwoCardNum == cardCellNum) {
        if (playerOneCardNum > playerTwoCardNum) {
          this.gameStatus = GameStatus.PLAYER_ONE_WIN;
        } else if (playerTwoCardNum > playerOneCardNum) {
          this.gameStatus = GameStatus.PLAYER_TWO_WIN;
        }
      }
    }
  }

  /**
   * Make the game enter next turn, swich between p1 and p2.
   */
  private void nextTurn() {
    if (currentTurn == EPlayer.PLAYER_ONE) {
      currentTurn = EPlayer.PLAYER_TWO;
    } else if (currentTurn == EPlayer.PLAYER_TWO) {
      currentTurn = EPlayer.PLAYER_ONE;
    }
  }

  /**
   * This method change the game status to in progress.
   */
  private void markAsGameStart() {
    this.gameStatus = GameStatus.GAME_IN_PROGRESS;
  }

  /**
   * Check if game is not started, if not, throw IllegalStateException.
   *
   * @throws IllegalStateException If game already started or is over.
   */
  private void assertGameNotStarted() {
    if (this.gameStatus != GameStatus.GAME_NOT_STARTED) {
      throw new IllegalStateException("Game Already started or is over");
    }
  }

  /**
   * Check if game is in progress, if not, throw IllegalStateException.
   *
   * @throws IllegalStateException If game not in progress.
   */
  private void assertGameInProgress() {
    if (this.gameStatus != GameStatus.GAME_IN_PROGRESS) {
      throw new IllegalStateException("Game Not in progress, game status: " + this.gameStatus);
    }
  }
}
