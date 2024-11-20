package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Combined testing class for RegularHole.
 * Contains examples, model interface tests, and implementation tests in one.
 */
public class RegularHoleTest {

  // Model Interface-Testing Section (Public Method Tests)
  @Test
  public void testGetCard() {
    RegularHole hole = new RegularHole();
    Assert.assertNull(hole.getCard());
  }

  @Test(expected = Exception.class)
  public void testSetCardThrowsException() throws Exception {
    RegularHole hole = new RegularHole();

    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    hole.setCard(card);
  }

  @Test
  public void testGetType() {
    RegularHole hole = new RegularHole();
    Assert.assertEquals(CellType.HOLE, hole.getType());
  }

  // Implementation-Testing Section (Testing Cloning Functionality)
  @Test
  public void testMakeClone() {
    RegularHole hole = new RegularHole();
    RegularHole clonedHole = (RegularHole) hole.makeClone();

    Assert.assertNotSame(hole, clonedHole);
    Assert.assertEquals(CellType.HOLE, clonedHole.getType());
    Assert.assertNull(clonedHole.getCard());
    try {
      RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
              CardNumber.FOUR, "Clone Test Card", EPlayer.PLAYER_TWO);
      clonedHole.setCard(card);
      Assert.fail("Expected CouldNotPlaceCardException was not thrown.");
    } catch (Exception e) {
      // Expected exception, test passes
    }
  }
}

