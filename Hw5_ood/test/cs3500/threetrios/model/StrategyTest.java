package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;

/**
 * Tests the behavior of strategies applied in the game model,
 * specifically to confirm that the corner-checking strategy inspects
 * all four corners as expected.
 */
public class StrategyTest {

  private MockTripleTriadModel mockModel;
  private CornerCheckingStrategy cornerStrategy;

  @Before
  public void setUp() {
    mockModel = new MockTripleTriadModel();
    cornerStrategy = new CornerCheckingStrategy();
  }

  @Test
  public void testCornerCheckingStrategyInspectsAllCorners() {
    Play testPlay = new Play(0, 0, 0);

    cornerStrategy.adjustPlayScore(mockModel, testPlay);

    List<Tuple<Integer, Integer>> expectedCorners = Arrays.asList(
            new Tuple<>(0, 0),
            new Tuple<>(2, 0),
            new Tuple<>(0, 2),
            new Tuple<>(2, 2)
    );

    for (Tuple<Integer, Integer> corner : expectedCorners) {
      assertTrue("Expected corner " + corner + " to be inspected",
              mockModel.getInspectedCoordinates().contains(corner));
    }
  }
}

