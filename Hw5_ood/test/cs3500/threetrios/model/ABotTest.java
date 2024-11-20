package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;


/**
 * Tests for the {@link AdvancedBot} class, verifying behavior of available move detection,
 * method interactions, and handling of scenarios with no available moves.
 */
public class ABotTest {

  private MockTripleTriadModel mockModel;
  private TestBot bot;

  @Before
  public void setUp() {
    mockModel = new MockTripleTriadModel();
    bot = new TestBot(null);  // Instantiate the concrete subclass of ABot for testing
  }

  /**
   * Test that getAvailableTryCoordinate correctly returns all legal coordinates.
   */
  @Test
  public void testGetAvailableCoordinates() {
    // Call getAvailableTryCoordinate and store the result
    List<Tuple<Integer, Integer>> availableCoords = bot.getAvailableTryCoordinate(mockModel);

    // Check the number of coordinates returned
    assertEquals("Expected 3 available coordinates", 3, availableCoords.size());

    // Verify that each expected coordinate is present in the list
    assertTrue("Expected (0, 0) to be in available coordinates", availableCoords.contains(new Tuple<>(0, 0)));
    assertTrue("Expected (1, 1) to be in available coordinates", availableCoords.contains(new Tuple<>(1, 1)));
    assertTrue("Expected (2, 2) to be in available coordinates", availableCoords.contains(new Tuple<>(2, 2)));

    // Print for confirmation
    System.out.println("Available coordinates returned: " + availableCoords);
  }

  /**
   * Test that getAvailableTryCoordinate throws an exception when no coordinates are available.
   */
  @Test(expected = IllegalStateException.class)
  public void testNoAvailableCoordinates() {
    // Modify the model to simulate a grid with no legal moves
    mockModel = new MockTripleTriadModel() {
      @Override
      public IGrid getGridClone() {
        return new NoLegalMovesGrid();
      }
    };

    bot.getAvailableTryCoordinate(mockModel);  // Should throw IllegalStateException
  }

  /**
   * Test that the methodLog in MockTripleTriadModel records the correct interactions.
   */
  @Test
  public void testMethodInteractions() {
    bot.getAvailableTryCoordinate(mockModel);

    assertTrue(mockModel.getMethodLog().contains("getGridClone called"));
  }

  private static class TestBot extends AdvancedBot {
    /**
     * Default constructor for AdvancedBot.
     * Notice: Sequence of Strategies in list DOES MATTER.
     *
     * @param strategies The strategies composed.
     */
    public TestBot(List<IStrategy> strategies) {
      super(strategies);
    }

    @Override
    public void play(ITripleTriadModel model) {
      // No-op for testing purposes
    }
  }

  private static class NoLegalMovesGrid implements IGrid {
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
      return false;  // No moves are legal in this grid
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
    public EPlayer getCardOwner(int col, int row) {
      return null;
    }

    @Override
    public IGrid makeClone() {
      return this;
    }
  }
}

