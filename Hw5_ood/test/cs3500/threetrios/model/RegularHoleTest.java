package cs3500.threetrios.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Combined testing class for RegularHole.
 * Contains examples, model interface tests, and implementation tests in one.
 */
public class RegularHoleTest {

  // Examples Section
  public static void main(String[] args) {
    // Example usage of RegularHole
    RegularHole exampleHole = new RegularHole();
    System.out.println("Example RegularHole:");
    System.out.println("Cell Type: " + exampleHole.getType());
    System.out.println("getCard(): " + exampleHole.getCard());
    System.out.println("Attempting to set a card in RegularHole (should throw an exception).");

    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Example Card", EPlayer.PLAYER_ONE);
    try {
      exampleHole.setCard(card);
    } catch (CouldNotPlaceCardException e) {
      System.out.println("Caught expected exception: " + e.getMessage());
    }
  }

  // Model Interface-Testing Section (Public Method Tests)
  @Test
  public void testGetCard() {
    RegularHole hole = new RegularHole();
    assertNull(hole.getCard());
  }

  @Test(expected = CouldNotPlaceCardException.class)
  public void testSetCardThrowsException() throws CouldNotPlaceCardException {
    RegularHole hole = new RegularHole();

    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    hole.setCard(card);
  }

  @Test
  public void testGetType() {
    RegularHole hole = new RegularHole();
    assertEquals(CellType.HOLE, hole.getType());
  }

  // Implementation-Testing Section (Testing Cloning Functionality)
  @Test
  public void testMakeClone() {
    RegularHole hole = new RegularHole();
    RegularHole clonedHole = (RegularHole) hole.makeClone();

    assertNotSame(hole, clonedHole);
    assertEquals(CellType.HOLE, clonedHole.getType());
    assertNull(clonedHole.getCard());
    try {
      RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
              CardNumber.FOUR, "Clone Test Card", EPlayer.PLAYER_TWO);
      clonedHole.setCard(card);
      fail("Expected CouldNotPlaceCardException was not thrown.");
    } catch (CouldNotPlaceCardException e) {
      // Expected exception, test passes
    }
  }
}

