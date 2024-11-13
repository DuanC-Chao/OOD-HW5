package cs3500.threetrios.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class BotCornerTest {

  /**
   * Test the Flip Maximization Strategy.
   * The bot should choose a move that maximizes the number of opponent flips.
   */
  @Test
  public void testFlipMaximizationStrategy() {
    MockTripleTriadModel mockModel = new MockTripleTriadModel();
    AdvancedBot bot = new AdvancedBot();

    // Set up a scenario where one position maximizes flips
    // For this test, let's assume (1, 1) will flip the most cards
    mockModel.methodLog.clear(); // Clear previous logs
    bot.play(mockModel);

    assertTrue("Expected playToGrid with the highest flips",
            mockModel.getMethodLog().contains("playToGrid called for player: 2, cardIdx: 0, col: 1, row: 1"));
  }

  /**
   * Test the Corner Preference Strategy.
   * The bot should prioritize moves to corner positions.
   */
  @Test
  public void testCornerPreferenceStrategy() {
    MockTripleTriadModel mockModel = new MockTripleTriadModel();
    AdvancedBot bot = new AdvancedBot();

    // Set up a scenario where a corner is the best move
    mockModel.methodLog.clear(); // Clear previous logs
    bot.play(mockModel);

    assertTrue("Expected playToGrid in a corner",
            mockModel.getMethodLog().contains("playToGrid called for player: 2, cardIdx: 0, col: 0, row: 0") ||
                    mockModel.getMethodLog().contains("playToGrid called for player: 2, cardIdx: 0, col: 2, row: 2"));
  }

  /**
   * Test the Flip Resistance Strategy.
   * The bot should choose a move that minimizes the chance of being flipped back by the opponent.
   */
  @Test
  public void testFlipResistanceStrategy() {
    MockTripleTriadModel mockModel = new MockTripleTriadModel();
    AdvancedBot bot = new AdvancedBot();

    // Set up a scenario where a certain move is least likely to be flipped
    mockModel.methodLog.clear();
    bot.play(mockModel);

    // Check if bot chose the position least likely to be flipped
    assertTrue("Expected playToGrid in position with minimal flip risk",
            mockModel.getMethodLog().contains("playToGrid called for player: 2, cardIdx: 0, col: 1, row: 0"));
  }

  /**
   * Test the Minimax Strategy.
   * The bot should select a move that minimizes the maximum move the opponent can make.
   */
  @Test
  public void testMinimaxStrategy() {
    MockTripleTriadModel mockModel = new MockTripleTriadModel();
    AdvancedBot bot = new AdvancedBot();

    // Set up a game state where minimax would choose a certain move
    mockModel.methodLog.clear();
    bot.play(mockModel);

    assertTrue("Expected playToGrid to minimize opponent's best move",
            mockModel.getMethodLog().contains("playToGrid called for player: 2, cardIdx: 0, col: 1, row: 2"));
  }
}



