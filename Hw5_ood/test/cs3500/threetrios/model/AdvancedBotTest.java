package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the functionality of the AdvancedBot class using a mock model and strategies.
 * Each test case examines specific behaviors such as selecting moves, handling no available moves,
 * and combining multiple strategies for decision-making.
 */
public class AdvancedBotTest {

  private MockTripleTriadModel mockModel;
  private List<IStrategy> mockStrategies;
  private AdvancedBot bot;

  @Before
  public void setUp() {
    // Initialize a mock model for testing
    mockModel = new MockTripleTriadModel();
    mockStrategies = new ArrayList<>();
  }

  /**
   * Test that AdvancedBot can play a move successfully.
   */
  @Test
  public void testBasicPlayFunctionality() {
    mockStrategies.add(new NeutralStrategy());
    bot = new AdvancedBot(mockStrategies);

    bot.play(mockModel);
    assertTrue(mockModel.getMethodLog().contains("playToGrid called for player: 2"));
  }

  /**
   * Test that AdvancedBot selects the highest scoring move based on mock strategies.
   */
  @Test
  public void testSelectsHighestScoringMove() {
    // Add strategies that will increase scores for specific coordinates
    mockStrategies.add(new FavorCoordinateStrategy(1, 1, 10));  // Add 10 points for (1, 1)
    mockStrategies.add(new FavorCoordinateStrategy(2, 2, 5));   // Add 5 points for (2, 2)
    bot = new AdvancedBot(mockStrategies);

    bot.play(mockModel);

    // Check that playToGrid was called with the highest-scoring coordinate (1, 1)
    assertTrue(mockModel.getMethodLog().contains("playToGrid called for player: 2, cardIdx: 0, col: 1, row: 1"));
  }

  /**
   * Test that AdvancedBot combines multiple strategies to make a decision.
   */
  @Test
  public void testCombinesStrategiesCorrectly() {
    // Define strategies that will add scores in a combined way
    mockStrategies.add(new FavorCoordinateStrategy(0, 0, 3));    // +3 for (0, 0)
    mockStrategies.add(new FavorCoordinateStrategy(1, 1, 4));    // +4 for (1, 1)
    mockStrategies.add(new FavorCoordinateStrategy(2, 2, 8));    // +8 for (2, 2)
    bot = new AdvancedBot(mockStrategies);

    bot.play(mockModel);

    // Expect the bot to choose (2, 2) as it has the highest combined score
    assertTrue(mockModel.getMethodLog().contains("playToGrid called for player: 2," +
            " cardIdx: 0, col: 2, row: 2"));
  }

  /**
   * Test that AdvancedBot handles cases with no available moves gracefully.
   */
  @Test(expected = IllegalStateException.class)
  public void testNoAvailableMoves() {
    // Create an empty mock grid with no legal moves
    mockModel = new MockTripleTriadModel() {
      public List<Tuple<Integer, Integer>> getAvailableTryCoordinate() {
        return new ArrayList<>();  // Return an empty list
      }
    };

    bot = new AdvancedBot(mockStrategies);
    bot.play(mockModel);
  }

  // Define mock strategy classes within the test for simplicity

  /**
   * Mock strategy that doesn't modify the score.
   */
  private class NeutralStrategy implements IStrategy {
    @Override
    public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
      // No effect on play score
    }
  }

  /**
   * Mock strategy to favor a specific coordinate by boosting the score.
   */
  private class FavorCoordinateStrategy implements IStrategy {
    private final int favoredCol;
    private final int favoredRow;
    private final int scoreBoost;

    public FavorCoordinateStrategy(int col, int row, int boost) {
      this.favoredCol = col;
      this.favoredRow = row;
      this.scoreBoost = boost;
    }

    @Override
    public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
      if (play.colToPlay == favoredCol && play.rowToPlay == favoredRow) {
        play.addScore(scoreBoost);
      }
    }
  }
}
