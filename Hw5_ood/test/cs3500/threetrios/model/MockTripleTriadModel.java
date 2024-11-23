package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A mock implementation of the ITripleTriadModel interface used for testing purposes.
 * This mock model allows control over specific game behaviors and logs method calls for inspection.
 * It provides custom configurations for flip counts and inspected coordinates, simulating various
 * board scenarios for strategies to evaluate.
 */
public class MockTripleTriadModel implements ITripleTriadModel {
  public final List<String> methodLog = new ArrayList<>();
  private final Map<Tuple<Integer, Integer>, Integer> flipCounts = new HashMap<>();

  public final List<Tuple<Integer, Integer>> inspectedCoordinates = new ArrayList<>();

  private String player1Name;
  private String player2Name;
  private String player1Deck;
  private String player2Deck;
  private ICombatRule combatRule;
  private IBot bot;
  private boolean gameStarted;

  @Override
  public List<ICard> getPlayerHand(int player) {
    methodLog.add("getPlayerHand called for player: " + player);
    return new ArrayList<>();
  }

  @Override
  public EPlayer getCardOwner(int col, int row) {
    return null;
  }

  @Override
  public int getPlayerScore(int playerNumber) {
    return 0;
  }

  @Override
  public IGrid getGridClone() {
    methodLog.add("getGridClone called");
    return new MockGrid();  // Return the mock grid with predefined legal moves
  }

  @Override
  public boolean isGameWon() {
    return false;
  }

  @Override
  public EPlayer getWinningPlayer() {
    return null;
  }

  @Override
  public int isCorner(int col, int row) {
    // Record each inspected coordinate
    inspectedCoordinates.add(new Tuple<>(col, row));

    int maxCol = 2;
    int maxRow = 2;

    // Return unique integer values for each corner for testing purposes
    if (col == 0 && row == 0) {
      return 1; // Top-left corner
    } else if (col == maxCol && row == 0) {
      return 2; // Top-right corner
    } else if (col == 0 && row == maxRow) {
      return 3; // Bottom-left corner
    } else if (col == maxCol && row == maxRow) {
      return 4; // Bottom-right corner
    } else {
      return 0; // Not a corner
    }
  }

  public List<Tuple<Integer, Integer>> getInspectedCoordinates() {
    return inspectedCoordinates;
  }

  public void setFlipCount(int col, int row, int flips) {
    flipCounts.put(new Tuple<>(col, row), flips);
  }

  @Override
  public int calculateFlips(EPlayer player, ICard card, int col, int row) {
    methodLog.add("calculateFlips called for col: " + col + ", row: " + row);
    return flipCounts.getOrDefault(new Tuple<>(col, row), 0);
  }

  @Override
  public boolean isLegalMove(int col, int row) {
    return false;  // Unused at the top-level mock
  }

  @Override
  public void playToGrid(int player, int cardIdx, int col, int row) {
    methodLog.add("playToGrid called for player: " + player + ", cardIdx: " + cardIdx + ", col: " + col + ", row: " + row);
  }

  @Override
  public ICombatRule getCombatRule() {
    methodLog.add("getCombatRule called");
    return null;
  }

  @Override
  public boolean haveBot() {
    return false;
  }

  @Override
  public void startGame(String player1, String player2, String player1Deck, String player2Deck, boolean shuffled, ICombatRule combatRule, IBot bot) {
    methodLog.add("startGame called with player1: " + player1 + ", player2: " + player2 + ", shuffled: " + shuffled);
    this.player1Name = player1;
    this.player2Name = player2;
    this.player1Deck = player1Deck;
    this.player2Deck = player2Deck;
    this.combatRule = combatRule;
    this.bot = bot;
    this.gameStarted = true;
  }

  @Override
  public IPlayer getPlayerOneClone() {
    methodLog.add("getPlayerOneClone called");
    return null;
  }

  @Override
  public IPlayer getPlayerTwoClone() {
    return null;
  }

  @Override
  public ICell[][] getGrid() {
    return new ICell[0][];
  }

  public List<String> getMethodLog() {
    return methodLog;
  }

  private static class MockGrid implements IGrid {
    @Override
    public int getColNumber() {
      return 3;  // Grid is 3 columns wide
    }

    @Override
    public boolean isLegalMove(int col, int row) {
      // Only allow moves at (0, 0), (1, 1), and (2, 2)
      return (col == 0 && row == 0) || (col == 1 && row == 1) || (col == 2 && row == 2);
    }

    @Override
    public ICell[][] getGrid() {
      return new ICell[0][];
    }

    @Override
    public int getRowNumber() {
      return 3;  // Grid is 3 rows high
    }

    @Override
    public ICard getCard(int col, int row) {
      return null;
    }

    @Override
    public ICell getCell(int col, int row) {
      return null;
    }

    @Override
    public void placeCard(int col, int row, ICard card) {
      // No-op for mock
    }

    @Override
    public void flip(int col, int row, ICombatRule rule) {
      // No-op for mock
    }

    @Override
    public EPlayer getCardOwner(int col, int row) {
      return null;
    }

    @Override
    public IGrid makeClone() {
      return this;  // Return itself as a simple clone
    }
  }
}
