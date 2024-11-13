package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

public class MockTripleTriadModel implements ITripleTriadModel {
  private final List<String> methodLog = new ArrayList<>();
  private String player1Name;
  private String player2Name;
  private String player1Deck;
  private String player2Deck;
  private ICombatRule combatRule;
  private IBot bot;
  private boolean gameStarted;

  public List<Tuple<Integer, Integer>> getAvailableTryCoordinate() {
    methodLog.add("getAvailableTryCoordinate called");
    List<Tuple<Integer, Integer>> availableCoords = new ArrayList<>();
    availableCoords.add(new Tuple<>(0, 0));
    availableCoords.add(new Tuple<>(1, 1));
    return availableCoords;
  }
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
    return new MockGrid();
  }

  @Override
  public boolean isGameWon() {
    return false;
  }

  @Override
  public String getWinningPlayerName() {
    return null;
  }

  @Override
  public int isCorner(int col, int row) {
    methodLog.add("isCorner called with col: " + col + ", row: " + row);
    return (col == 0 && row == 0) ? 1 : 0;
  }

  @Override
  public int calculateFlips(EPlayer player, ICard card, int col, int row) {
    methodLog.add("calculateFlips called for player: " + player + " at col: " + col + ", row: " + row);
    return 1;
  }

  @Override
  public boolean isLegalMove(int col, int row) {
    return false;
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

  private class MockGrid implements IGrid {
    @Override
    public int getColNumber() {
      return 3;
    }

    @Override
    public ICell[][] getGrid() {
      return new ICell[0][];
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

    }

    @Override
    public void filp(int col, int row, ICombatRule rule) {

    }

    @Override
    public int getRowNumber() {
      return 3;  // Mocked row count
    }

    @Override
    public boolean isLegalMove(int col, int row) {
      return (col + row) % 2 == 0;  // Example: allow moves only on "even" coordinates
    }

    @Override
    public EPlayer getCardOwner(int col, int row) {
      return null;
    }

    @Override
    public IGrid makeClone() {
      return null;
    }
  }
}
