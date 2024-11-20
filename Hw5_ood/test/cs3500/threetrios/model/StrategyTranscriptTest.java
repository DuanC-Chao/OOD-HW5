package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StrategyTranscriptTest {

  private MockTripleTriadModel mockModel;
  private CornerCheckingStrategy cornerStrategy;

  @Before
  public void setUp() {
    // Initialize the mock model and the corner strategy
    mockModel = new MockTripleTriadModel();
    cornerStrategy = new CornerCheckingStrategy();
  }

  /**
   * Test that the CornerCheckingStrategy inspects all four corners
   * and generate a transcript of the coordinates checked.
   */
  @Test
  public void testCornerCheckingTranscript() {
    Play testPlay = new Play(0, 0, 0);  // Placeholder play object
    cornerStrategy.adjustPlayScore(mockModel, testPlay);

    // Expected corners for a 3x3 board
    List<Tuple<Integer, Integer>> expectedCorners = Arrays.asList(
      new Tuple<>(0, 0),
      new Tuple<>(2, 0),
      new Tuple<>(0, 2),
      new Tuple<>(2, 2)
    );

    // Check if each expected corner was inspected
    for (Tuple<Integer, Integer> corner : expectedCorners) {
      assertTrue("Expected corner " + corner + " to be inspected",
        mockModel.getInspectedCoordinates().contains(corner));
    }

    // Print the transcript
    System.out.println("Transcript of inspected coordinates:");
    mockModel.getInspectedCoordinates().forEach(coord -> System.out.println("Checked coordinate: " + coord));
  }
}

