package cs3500.threetrios.provider.controller;

import cs3500.threetrios.provider.model.ThreeTriosModel;
import cs3500.threetrios.provider.view.ThreeTriosGUI;

/**
 * Implementation of the {@link ThreeTriosController} interface. This class
 * handles interactions between the {@link ThreeTriosModel} and the {@link ThreeTriosGUI}.
 */
public class ThreeTriosControllerImpl implements ThreeTriosController {
  private final ThreeTriosGUI view;
  private final ThreeTriosModel model;

  /**
   * Constructs a {@code ThreeTriosControllerImpl} with the given model and view.
   *
   * @param model the model to use for game logic
   * @param view  the view to use for displaying the game
   * @throws IllegalArgumentException if the view is null
   */
  public ThreeTriosControllerImpl(ThreeTriosModel model, ThreeTriosGUI view) {
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    this.view = view;
    this.model = model;
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
    switch (model.getCurrentPlayer()) {
      case RED:
        view.popUpMsg("Blue made a move.");
        break;
      case BLUE:
        view.setFrameTitle("Red made a move.");
        break;
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
