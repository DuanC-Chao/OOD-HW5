package cs3500.threetrios.controller;

import java.io.IOException;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.view.Move;
import cs3500.threetrios.view.Pick;
import cs3500.threetrios.view.TripleTriadView;

/**
 * The functional class for TripleTriad Controller.
 */
public class TripleTriadController implements ITripleTriadController {

  /**
   * Which player's controller it is.
   */
  private EPlayer player;

  /**
   * A buffer, which represent the card picked by player One view.
   */
  private int playerOnePickBuffer;

  /**
   * A buffer, represents the card picked by player two view.
   */
  private int playerTwoPickBuffer;

  /**
   * The game's model.
   */
  private ITripleTriadModel model;

  /**
   * The game's view.
   */
  private TripleTriadView view;

  /**
   * The view of another player.
   */
  private TripleTriadView secondaryView;

  /**
   * The default constructor for controller.
   *
   * @param player The player of the Controller.
   * @param model  The Read and Write model, since the controller could modify game status.
   * @param view   The view to pass delegate to.
   */
  public TripleTriadController(EPlayer player, ITripleTriadModel model, TripleTriadView view,
                               TripleTriadView secondaryView) {
    this.player = player;
    this.model = model;
    this.view = view;
    this.secondaryView = secondaryView;
    view.setMoveEventHandler(this::pickHandler, this::moveHandler);
  }

  @Override
  public void pickHandler(Pick pick) {
    if (pick == null) {
      throw new IllegalArgumentException("Pick cannot be null");
    }
    if (pick.player == EPlayer.PLAYER_ONE && this.player == EPlayer.PLAYER_ONE) {
      this.playerOnePickBuffer = pick.cardIdx;
      System.out.println("Current player one pick buffer is: " + this.playerOnePickBuffer);
    } else if (pick.player == EPlayer.PLAYER_TWO && this.player == EPlayer.PLAYER_TWO) {
      this.playerTwoPickBuffer = pick.cardIdx;
      System.out.println("Current player two pick buffer is: " + this.playerTwoPickBuffer);
    }
  }

  @Override
  public void moveHandler(Move move) {
    int pIdx;
    if (this.player == EPlayer.PLAYER_ONE) {
      pIdx = 1;
    } else if (this.player == EPlayer.PLAYER_TWO) {
      pIdx = 2;
    } else {
      pIdx = 0;
    }

    // If model the game has bot, and pIdx is 2, than do nothing, Human player should not be able
    // to move
    if (pIdx == 2 && model.haveBot()) {
      return;
    }

    try {
      this.model.playToGrid(pIdx, pIdx == 1 ? this.playerOnePickBuffer : this.playerTwoPickBuffer,
              move.playToCol, move.playToRow);
    } catch (IllegalArgumentException e) {
      System.out.println("Not your turn, Player: " + this.player.toString());
    }

    if (model.haveBot()) {
      new Thread(() -> {
        try {
          Thread.sleep(50);
          refreshView();
          showGameOverPopUp(pIdx, true);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    } else {
      refreshView();
      showGameOverPopUp(pIdx, false);
    }
  }

  /**
   * The Pop Up to show the game over info.
   */
  private void showGameOverPopUp(int pIdx, boolean botTurn) {
    if (!this.model.isGameWon()) {
      String playerSubject;
      if (pIdx == 1) {
        playerSubject = "Player Two";
      } else {
        playerSubject = "Player One";
      }

      if (!botTurn || pIdx == 2) {
        if(secondaryView != null) {
          this.secondaryView.showPopUp("Your Turn, " + playerSubject);
        }
      }

    } else {
      int winningPlayerNum = 0;
      String playerSubject;
      if (this.model.getWinningPlayer() == EPlayer.PLAYER_ONE) {
        winningPlayerNum = 1;
        playerSubject = "Player One";
      } else if (this.model.getWinningPlayer() == EPlayer.PLAYER_TWO) {
        winningPlayerNum = 2;
        playerSubject = "Player Two";
      } else {
        throw new IllegalStateException();
      }

      int winningPlayerScore = this.model.getPlayerScore(winningPlayerNum);

      this.secondaryView.showPopUp(playerSubject + " Won!, Score: " + winningPlayerScore);
    }
  }

  /**
   * The method to refresh the view.
   */
  private void refreshView() {
    try {
      this.view.render(null);
      this.secondaryView.render(null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
