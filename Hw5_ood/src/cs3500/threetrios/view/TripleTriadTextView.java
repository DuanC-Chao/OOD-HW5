package cs3500.threetrios.view;

import java.io.IOException;
import java.util.function.Consumer;

import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.ICell;
import cs3500.threetrios.model.IPlayer;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;

import static cs3500.threetrios.model.EPlayer.PLAYER_ONE;
import static cs3500.threetrios.model.EPlayer.PLAYER_TWO;

/**
 * The pure text view for TripleTriad game.
 */
public class TripleTriadTextView implements TripleTriadView {

  /**
   * The model, stored as a field in view.
   */
  private final ReadOnlyTripleTriadModel model;

  /**
   * The default constructor.
   *
   * @param model The model to be assigned to the view.
   */
  public TripleTriadTextView(ReadOnlyTripleTriadModel model) {
    this.model = model;
  }

  @Override
  public void render(Appendable out) throws IOException {
    Appendable boardView = out;
    ICell[][] grid = model.getGrid();

    for (int row = grid.length - 1; row >= 0; row--) {
      for (ICell cell : grid[row]) {
        String cellSymbol;

        if (cell.getType() == CellType.HOLE) {
          cellSymbol = " X ";
        } else {
          ICard cardToCheck = cell.getCard();
          if (cardToCheck == null) {
            cellSymbol = " _ ";
          } else if (cardToCheck.getOwner() == PLAYER_ONE) {
            cellSymbol = " B ";
          } else if (cardToCheck.getOwner() == PLAYER_TWO) {
            cellSymbol = " R ";
          } else {
            cellSymbol = " ? ";
          }
        }

        boardView.append(cellSymbol).append(" ");
      }
      boardView.append("\n");
    }

    boardView.append("\n");

    IPlayer playerOne = model.getPlayerOneClone();
    IPlayer playerTwo = model.getPlayerTwoClone();

    // Displays numbers as N S E W
    boardView.append("Player One Cards: \n");
    for (ICard card : playerOne.getHand()) {
      boardView.append(card.getCardName()).append(" ").append(card.getNorth().toString()).
        append(" ").append(card.getSouth().toString()).append(" ").
        append(card.getEast().toString()).
        append(" ").append(card.getWest().toString()).append("\n");
    }

    boardView.append("\n");

    boardView.append("Player Two Cards: \n");
    for (ICard card : playerTwo.getHand()) {
      boardView.append(card.getCardName()).append(" ").append(card.getNorth().toString()).
        append(" ").append(card.getSouth().toString()).append(" ").
        append(card.getEast().toString()).
        append(" ").append(card.getWest().toString()).append("\n");
    }


    if (model.isGameWon()) {
      boardView.append("Winner: ").append(model.getWinningPlayer().toString()).append("\n");
    }

    System.out.println(boardView.toString());
  }

  @Override
  public void setMoveEventHandler(Consumer<Pick> handCardDelegate, Consumer<Move> cellDelegate) {
    return;
  }

  @Override
  public void showPopUp(String message) {
    return;
  }
}