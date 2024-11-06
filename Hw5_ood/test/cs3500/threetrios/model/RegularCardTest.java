package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Combined testing class for RegularCard.
 * Contains examples, model interface tests, and implementation tests in one.
 */
public class RegularCardTest {

  // Model Interface-Testing Section (Public Method Tests)
  @Test
  public void testDefaultConstructor() {
    RegularCard card = new RegularCard();
    Assert.assertEquals("Default Name", card.getCardName());
    Assert.assertNull(card.getOwner());
  }

  @Test
  public void testParameterizedConstructor() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);

    Assert.assertEquals(CardNumber.ONE, card.getNorth());
    Assert.assertEquals(CardNumber.TWO, card.getSouth());
    Assert.assertEquals(CardNumber.THREE, card.getEast());
    Assert.assertEquals(CardNumber.FOUR, card.getWest());
    Assert.assertEquals("Test Card", card.getCardName());
    Assert.assertEquals(EPlayer.PLAYER_ONE, card.getOwner());
  }

  @Test
  public void testSetCardNumber() {
    RegularCard card = new RegularCard();
    card.setCardNumber(CardNumber.FIVE, CardNumber.SIX, CardNumber.SEVEN, CardNumber.EIGHT);

    Assert.assertEquals(CardNumber.FIVE, card.getNorth());
    Assert.assertEquals(CardNumber.SIX, card.getSouth());
    Assert.assertEquals(CardNumber.SEVEN, card.getEast());
    Assert.assertEquals(CardNumber.EIGHT, card.getWest());
  }

  @Test
  public void testSetCardName() {
    RegularCard card = new RegularCard();
    card.setCardName("New Name");
    Assert.assertEquals("New Name", card.getCardName());
  }

  @Test
  public void testSetCardNameNull() {
    RegularCard card = new RegularCard();
    card.setCardName(null); // Should ignore null name
    Assert.assertEquals("Default Name", card.getCardName());
  }

  @Test
  public void testSetOwner() {
    RegularCard card = new RegularCard();
    card.setOwner(EPlayer.PLAYER_TWO);
    Assert.assertEquals(EPlayer.PLAYER_TWO, card.getOwner());
  }

  // Implementation-Testing Section (Testing Cloning Functionality)
  @Test
  public void testMakeClone() {
    RegularCard card = new RegularCard(CardNumber.THREE, CardNumber.FOUR, CardNumber.FIVE,
            CardNumber.SIX, "Clone Test", EPlayer.PLAYER_ONE);
    RegularCard clone = (RegularCard) card.makeClone();

    Assert.assertEquals(CardNumber.THREE, clone.getNorth());
    Assert.assertEquals(CardNumber.FOUR, clone.getSouth());
    Assert.assertEquals(CardNumber.FIVE, clone.getEast());
    Assert.assertEquals(CardNumber.SIX, clone.getWest());
    Assert.assertEquals("Clone Test", clone.getCardName());
    Assert.assertEquals(EPlayer.PLAYER_ONE, clone.getOwner());

    Assert.assertNotSame(card, clone);  // Ensure clone is a different instance
  }
}



