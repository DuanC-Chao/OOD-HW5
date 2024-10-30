package cs3500.threetrios.view;

import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.CellType;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.ICell;
import cs3500.threetrios.model.IPlayer;

import static cs3500.threetrios.model.EPlayer.PLAYER_ONE;
import static cs3500.threetrios.model.EPlayer.PLAYER_TWO;

public class TripleTriadTextView implements TripleTriadView {
  private final ITripleTriadModel model;

  public TripleTriadTextView(ITripleTriadModel model) {
    this.model = model;
  }

  @Override
  public void render() {
    StringBuilder boardView = new StringBuilder();
    ICell[][] grid = model.getGrid();

    for (ICell[] row : grid) {
      for (ICell cell : row) {
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
      boardView.append("\n"); // New line after each row
    }

    boardView.append("\n");

    IPlayer playerOne = model.getPlayerOneClone();
    IPlayer playerTwo = model.getPlayerTwoClone();

    // Displays numbers as N S W E
    boardView.append("Player One Cards: \n");
    for (ICard card : playerOne.getHand()) {
      boardView.append(card.getCardName()).append(" ").append(card.getNorth().toString()).
        append(" ").append(card.getSouth().toString()).append(" ").
        append(card.getWest().toString()).
        append(" ").append(card.getEast().toString()).append("\n");
    }

    boardView.append("\n");

    boardView.append("Player Two Cards: \n");
    for (ICard card : playerTwo.getHand()) {
      boardView.append(card.getCardName()).append(" ").append(card.getNorth().toString()).
        append(" ").append(card.getSouth().toString()).append(" ").
        append(card.getWest().toString()).
        append(" ").append(card.getEast().toString()).append("\n");
    }

    if (model.isGameWon()) {
      boardView.append("Winner: ").append(model.getWinningPlayerName()).append("\n");
    }

    System.out.println(boardView.toString());
  }
}