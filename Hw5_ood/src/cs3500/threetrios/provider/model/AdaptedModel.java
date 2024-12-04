package cs3500.threetrios.provider.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.Tuple;
import cs3500.threetrios.provider.controller.ThreeTriosController;

/**
 * A model class that adapts the provided {@link ThreeTriosModel} to work with our
 * own {@link ITripleTriadModel} implementation.
 */
public class AdaptedModel implements ThreeTriosModel {

  /**
   * The inner model being adapted, which is our own model implementation.
   */
  private final ITripleTriadModel innerModel;

  /**
   * Constructs an {@code AdaptedModel} using the given original model as its inner model.
   *
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

  /**
   * Convert col and row to our model's standard.
   * @param col The col to convert.
   * @param row The row to convert.
   * @return The converted coord.
   */
  private Tuple<Integer, Integer> convertCoord(int col, int row) {
    int totalRows = innerModel.getGridClone().getRowNumber();
    int newRow = totalRows - 1 - row;
    int newCol = col;
    return new Tuple<>(newCol, newRow);
  }

  @Override
  public GameCard getCardAt(int row, int col) {

    Tuple<Integer, Integer> convertedCoord = convertCoord(col, row);
    int newCol = convertedCoord.getFirst();
    int newRow = convertedCoord.getSecond();

    ICard preCard = innerModel.getGridClone().getCard(newCol, newRow);

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
    Tuple<Integer, Integer> convertedCoord = convertCoord(col, row);
    int newCol = convertedCoord.getFirst();
    int newRow = convertedCoord.getSecond();
    return innerModel.getGridClone().getCell(newCol, newRow).getType() == CellType.HOLE;
  }

  @Override
  public int howManyFlips(int cardIdxInHand, int row, int col) {
    Tuple<Integer, Integer> convertedCoord = convertCoord(col, row);
    int newCol = convertedCoord.getFirst();
    int newRow = convertedCoord.getSecond();
    return innerModel.calculateFlips(innerModel.getTurn(),
      innerModel.getPlayerHand(
        innerModel.getTurn() == EPlayer.PLAYER_ONE ? 1 : 2).get(cardIdxInHand), newCol, newRow);
  }

  @Override
  public boolean isValidMove(int cardIdxInHand, int row, int col) {
    Tuple<Integer, Integer> convertedCoord = convertCoord(col, row);
    int newCol = convertedCoord.getFirst();
    int newRow = convertedCoord.getSecond();
    return innerModel.isLegalMove(newCol, newRow);
  }

  @Override
  public void startGame(ThreeTriosController player1, ThreeTriosController player2) {
    return;
  }

  @Override
  public void playToGrid(int cardIdxInHand, int row, int col) {
    Tuple<Integer, Integer> convertedCoord = convertCoord(col, row);
    int newCol = convertedCoord.getFirst();
    int newRow = convertedCoord.getSecond();
    this.innerModel.playToGrid(innerModel.getTurn() == EPlayer.PLAYER_ONE ? 1 : 2, cardIdxInHand,
      newCol, newRow);
  }
}
