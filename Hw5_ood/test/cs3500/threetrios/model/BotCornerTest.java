package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BotCornerTest {

  private AdvancedBot bot;
  private MockCoordinate model;

  @Before
  public void setUp() {
    model = new MockCoordinate();
    bot = new AdvancedBot(null);
  }

  @Test
  public void testBotChecksAllCorners() {
    bot.play(model);

    // Verify that all four corners were inspected
    assertTrue("Expected (0, 0) to be checked", model.getInspectedCoordinates().contains(new Tuple<>(0, 0)));
    assertTrue("Expected (0, 2) to be checked", model.getInspectedCoordinates().contains(new Tuple<>(0, 2)));
    assertTrue("Expected (2, 0) to be checked", model.getInspectedCoordinates().contains(new Tuple<>(2, 0)));
    assertTrue("Expected (2, 2) to be checked", model.getInspectedCoordinates().contains(new Tuple<>(2, 2)));
  }
}

