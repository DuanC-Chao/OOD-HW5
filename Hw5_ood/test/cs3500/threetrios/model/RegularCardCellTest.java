package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Combined testing class for RegularCardCell.
 * Contains examples, model interface tests, and implementation tests in one class.
 */
public class RegularCardCellTest {

  // Model Interface-Testing Section (Public Method Tests)

  @Test
  public void testDefaultConstructor() {
    RegularCardCell cell = new RegularCardCell();
    Assert.assertNull(cell.getCard());
    Assert.assertEquals(CellType.CARD_CELL, cell.getType());
  }

  @Test
  public void testCloneConstructor() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    RegularCardCell cell = new RegularCardCell(card);
    Assert.assertEquals(card, cell.getCard());
  }

  @Test
  public void testSetCardInEmptyCell() throws Exception {
    RegularCardCell cell = new RegularCardCell();
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    cell.setCard(card);
    Assert.assertEquals(card, cell.getCard());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCardWithNull() throws Exception {
    RegularCardCell cell = new RegularCardCell();
    cell.setCard(null);
  }

  @Test(expected = Exception.class)
  public void testSetCardInOccupiedCell() throws Exception {
    RegularCard card1 = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Card 1", EPlayer.PLAYER_ONE);
    RegularCard card2 = new RegularCard(CardNumber.FIVE, CardNumber.SIX, CardNumber.SEVEN,
            CardNumber.EIGHT, "Card 2", EPlayer.PLAYER_TWO);
    RegularCardCell cell = new RegularCardCell(card1);
    cell.setCard(card2);
  }

  // Implementation-Testing Section (Testing Cloning Functionality)
  @Test
  public void testMakeCloneEmptyCell() {
    RegularCardCell cell = new RegularCardCell();
    RegularCardCell clonedCell = (RegularCardCell) cell.makeClone();

    Assert.assertNotSame(cell, clonedCell);
    Assert.assertNull(clonedCell.getCard());
  }

  @Test
  public void testMakeCloneWithCard() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Original Card", EPlayer.PLAYER_ONE);
    RegularCardCell cell = new RegularCardCell(card);

    RegularCardCell clonedCell = (RegularCardCell) cell.makeClone();
    Assert.assertNotSame(cell, clonedCell);
    Assert.assertNotSame(card, clonedCell.getCard());
    Assert.assertEquals(card.getOwner(), clonedCell.getCard().getOwner());
  }
}

