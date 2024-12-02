package cs3500.threetrios.provider.model;

import java.util.List;

import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;

/**
 * A model class, which adapt from Provided ReadOnlyThreeTriosModel with our own model.
 */
public class AdaptedReadOnlyModel implements ReadOnlyThreeTriosModel {

  /**
   * The inner model kept, which is our own model implementation.
   */
  private final ReadOnlyTripleTriadModel innerModel;

  /**
   * The constructor, takes a original model, as innerModel of the adapted model.
   * @param innerModel The original model to be adapted.
   */
  public AdaptedReadOnlyModel(ReadOnlyTripleTriadModel innerModel) {
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
    return
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
    return null;
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
    return null;
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
}
