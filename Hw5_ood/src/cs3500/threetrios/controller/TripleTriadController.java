package cs3500.threetrios.controller;

import java.io.IOException;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.NotYourTurnException;
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
   * A buffer, which represent the card picked from the corresponding player's hand.
   */
  private int pickBuffer;

  /**
   * The game's model.
   */
  private ITripleTriadModel model;

  /**
   * The game's view.
   */
  private TripleTriadView view;

  /**
   * The default constructor for controller.
   *
   * @param player The player of the Controller.
   * @param model  The Read and Write model, since the controller could modify game status.
   * @param view The view to pass delegate to.
   */
  public TripleTriadController(EPlayer player, ITripleTriadModel model, TripleTriadView view) {
    this.player = player;
    this.model = model;
    view.setMoveEventHandler(this::pickHandler, this::moveHandler);
  }

  @Override
  public void pickHandler(Pick pick) {
    if (pick == null) {
      throw new IllegalArgumentException("Pick cannot be null");
    }
    if(pick.player == this.player) {
      this.pickBuffer = pick.cardIdx;
      System.out.println("From Controller of: " + this.player + ", Card With idx: " + pick.cardIdx + " Seelcted");
    }
  }

  @Override
  public void moveHandler(Move move) {
    int pIdx = 0;
    if(this.player == EPlayer.PLAYER_ONE) {
      pIdx = 1;
    } else if(this.player == EPlayer.PLAYER_TWO) {
      pIdx = 2;
    }

    try {
      this.model.playToGrid(pIdx, this.pickBuffer, move.play_to_col, move.play_to_row);
    } catch (NotYourTurnException e) {
      System.out.println("Not your turn, Player: " + this.player.toString());
    }
    refreshView();
  }

  /**
   * The method to refresh the view.
   */
  private void refreshView() {
    try {
      this.view.render(null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
