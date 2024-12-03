package cs3500.threetrios.provider.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.TripleTriadModel;
import cs3500.threetrios.provider.controller.ThreeTriosController;

/**
 * A model class, which adapt from Provided ReadOnlyThreeTriosModel with our own model.
 */
public class AdaptedModel implements ThreeTriosModel {

  /**
   * The inner model kept, which is our own model implementation.
   */
  private final ITripleTriadModel innerModel;

  /**
   * The constructor, takes a original model, as innerModel of the adapted model.
   * @param innerModel The original model to be adapted.
   */
  public AdaptedModel(ITripleTriadModel innerModel) {
    this.innerModel = innerModel;
  }

  @Override
  public boolean isGameOver() {
    return innerModel.isGameWon();
  }

  @Override
  public PlayerColor winningPlayer() {
    if (innerModel.getWinningPlayer() == null) {
      throw new IllegalStateException("Game not over");
    }

    if (innerModel.getWinningPlayer() == EPlayer.PLAYER_ONE) {
      return PlayerColor.RED;
    } else {
      return PlayerColor.BLUE;
    }
  }

  @Override
  public List<GameCard> getPlayerHand(PlayerColor color) {

    int playerIdx = 0;
    if (color == PlayerColor.RED) {
      playerIdx = 1;
    } else if (color == PlayerColor.BLUE) {
      playerIdx = 2;
    }

    List<ICard> playerHand = innerModel.getPlayerHand(playerIdx);

    List<GameCard> hand = new ArrayList<>(playerHand.size());
    for (ICard card : playerHand) {
      hand.add(new GameCard(card));
    }
    return hand;
  }

  @Override
  public int getNumGridRows() {
    return innerModel.getGridClone().getRowNumber();
  }

  @Override
  public int getNumGridCols() {
    return innerModel.getGridClone().getColNumber();
  }

  @Override
  public GameCard getCardAt(int row, int col) {
    ICard preCard = innerModel.getGridClone().getCard(col, row);

    if (preCard == null) {
      return null;
    }
    return new GameCard(preCard);
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    if (innerModel.getWinningPlayer() == EPlayer.PLAYER_ONE) {
      return PlayerColor.RED;
    } else {
      return PlayerColor.BLUE;
    }
  }

  @Override
  public PlayerColor cardOwner(GameCard card) {
    if (card.getCardOwner() == EPlayer.PLAYER_ONE) {
      return PlayerColor.RED;
    } else {
      return PlayerColor.BLUE;
    }
  }

  @Override
  public boolean isHole(int row, int col) {
    return innerModel.getGridClone().getCell(col, row).getType() == CellType.HOLE;
  }

  @Override
  public int howManyFlips(int cardIdxInHand, int row, int col) {
    return innerModel.calculateFlips(innerModel.getTurn(),
      innerModel.getPlayerHand(
        innerModel.getTurn() == EPlayer.PLAYER_ONE ? 1 : 2).get(cardIdxInHand), col, row);
  }

  @Override
  public boolean isValidMove(int cardIdxInHand, int row, int col) {
    return innerModel.isLegalMove(col, row);
  }

  @Override
  public void startGame(ThreeTriosController player1, ThreeTriosController player2) {
    return;
  }

  @Override
  public void playToGrid(int cardIdxInHand, int row, int col) {
    this.innerModel.playToGrid(innerModel.getTurn() == EPlayer.PLAYER_ONE ? 1 : 2, cardIdxInHand, col, row);
  }
}
