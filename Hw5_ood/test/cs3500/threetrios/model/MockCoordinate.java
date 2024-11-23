package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the {@link TripleTriadModel} for testing purposes.
 * This class allows tracking of inspected coordinates when legal moves are checked,
 * making it useful for verifying behavior in tests.
 */

public class MockCoordinate extends TripleTriadModel {
  List<Tuple<Integer, Integer>> inspectedCoordinates = new ArrayList<>();

  @Override
  public IGrid getGridClone() {
    return new IGrid() {
      @Override
      public IGrid makeClone() {
        return null;
      }

      @Override
      public int getColNumber() {
        return 3;
      }

      @Override
      public int getRowNumber() {
        return 3;
      }

      @Override
      public boolean isLegalMove(int col, int row) {
        inspectedCoordinates.add(new Tuple<>(col, row));
        return true;
      }

      @Override
      public EPlayer getCardOwner(int col, int row) {
        return null;
      }

      @Override
      public ICell[][] getGrid() {
        return new ICell[3][3];
      }

      @Override
      public ICard getCard(int col, int row) {
        return null;  // Stubbed for simplicity
      }

      @Override
      public ICell getCell(int col, int row) {
        return null;
      }

      @Override
      public void placeCard(int col, int row, ICard card) {
        // Intentionally left empty as this method is not needed in the mock implementation.
      }

      @Override
      public void flip(int col, int row, ICombatRule rule) {
        // Intentionally left empty as this method is not needed in the mock implementation.
      }
    };
  }

  @Override
  public List<ICard> getPlayerHand(int playerNum) {
    List<ICard> playerHand = new ArrayList<>();
    playerHand.add(new RegularCard());
    return playerHand;
  }

  @Override
  public int calculateFlips(EPlayer player, ICard card, int col, int row) {
    return 1;
  }

  public List<Tuple<Integer, Integer>> getInspectedCoordinates() {
    return inspectedCoordinates;
  }
}

