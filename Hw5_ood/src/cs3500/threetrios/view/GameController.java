package cs3500.threetrios.view;

import cs3500.threetrios.model.CouldNotPlaceCardException;
import cs3500.threetrios.model.IBot;
import cs3500.threetrios.model.ICombatRule;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.NoSuchConfigException;
import cs3500.threetrios.model.NotYourTurnException;

public class GameController {
  private final IGameFrame view;
  private final ITripleTriadModel model;

  public GameController(IGameFrame view, ITripleTriadModel model) {
    this.view = view;
    this.model = model;
  }

  public void initializeGame(String boardConfigPath, String cardConfigPath,
                             String playerOneName, String playerTwoName,
                             boolean shuffle, ICombatRule rule, IBot bot) {
    try {
      System.out.println("Loading grid config from: " + boardConfigPath);
      System.out.println("Loading deck config from: " + cardConfigPath);
      model.startGame(boardConfigPath, cardConfigPath, playerOneName, playerTwoName, shuffle, rule, bot);
      System.out.println("Game initialized successfully.");
      view.updateView();
    } catch (Exception e) {
      System.out.println("Error starting game: " + e.getMessage());
    }
  }


  public void handleCellClick(int row, int col, int playerNumber, int cardIndex) {
    // Attempt to play a card to the grid
    try {
      model.playToGrid(playerNumber, cardIndex, col, row);
      view.updateView();
    } catch (IllegalArgumentException | IllegalStateException | NotYourTurnException |
             CouldNotPlaceCardException e) {
      System.out.println("Error playing card: " + e.getMessage());
    }
  }

  public void handleCardPlay(int playerNumber, int cardIndex, int row, int col) {
    handleCellClick(row, col, playerNumber, cardIndex);
  }

  private int getCurrentPlayerNumber() {
    // Example placeholder logic; replace with actual model state if available
    return model.isGameWon() ? -1 : 1;
  }
}

