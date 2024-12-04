package cs3500.threetrios.provider.controller;

import java.io.IOException;

import cs3500.threetrios.provider.model.ThreeTriosModel;
import cs3500.threetrios.provider.view.ThreeTriosGUI;
import cs3500.threetrios.view.TripleTriadView;

/**
 * Implementation of the {@link ThreeTriosController} interface. This class
 * handles interactions between the {@link ThreeTriosModel} and the {@link ThreeTriosGUI}.
 */
public class ThreeTriosControllerImpl implements ThreeTriosController {
  private final ThreeTriosGUI view;
  private final TripleTriadView secondaryView;
  private final ThreeTriosModel model;

  /**
   * Constructs a {@code ThreeTriosControllerImpl} with the given model and view.
   *
   * @param model the model to use for game logic
   * @param view  the view to use for displaying the game
   * @throws IllegalArgumentException if the view is null
   */
  public ThreeTriosControllerImpl(ThreeTriosModel model, ThreeTriosGUI view, TripleTriadView
          secondaryView) {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    this.view = view;
    this.model = model;
    this.secondaryView = secondaryView;
  }

  @Override
  public void startGUI() {
    view.display(true);
  }

  @Override
  public void notifyOfTurn() {
    String player = model.getCurrentPlayer().toString();
    view.setFrameTitle("Three Trios - " + player + "'s Turn");
    view.popUpMsg(player + "'s turn");
  }

  @Override
  public void notifyStateUpdate() {
    String currentPlayer = model.getCurrentPlayer().toString();

    if ("RED".equals(currentPlayer)) {
      view.popUpMsg("Blue made a move.");
    } else if ("BLUE".equals(currentPlayer)) {
      view.setFrameTitle("Red made a move.");
    }
  }


  @Override
  public void currentPlayerMakeMove(int cardIdxInHand, int row, int col) {
    try {
      model.playToGrid(cardIdxInHand, row, col);
    }
    catch (IllegalArgumentException e) {
      view.popUpMsg(e.getMessage());
    }
    view.refresh();

    try {
      secondaryView.render(null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void selectCard(int cardIdxInHand) {
    view.selectCard(model.getCurrentPlayer(), cardIdxInHand);
  }

  @Override
  public void quit() {
    view.display(false);
  }
}
