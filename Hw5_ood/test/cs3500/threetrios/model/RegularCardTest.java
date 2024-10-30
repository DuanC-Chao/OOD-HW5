package cs3500.threetrios.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegularCardTest {

  @Test
  public void testDefaultConstructor() {
    RegularCard card = new RegularCard();
    assertEquals("Default Name", card.getCardName());
    assertNull(card.getOwner());
  }

  @Test
  public void testParameterizedConstructor() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);

    assertEquals(CardNumber.ONE, card.getNorth());
    assertEquals(CardNumber.TWO, card.getSouth());
    assertEquals(CardNumber.THREE, card.getEast());
    assertEquals(CardNumber.FOUR, card.getWest());
    assertEquals("Test Card", card.getCardName());
    assertEquals(EPlayer.PLAYER_ONE, card.getOwner());
  }

  @Test
  public void testSetCardNumber() {
    RegularCard card = new RegularCard();
    card.setCardNumber(CardNumber.FIVE, CardNumber.SIX, CardNumber.SEVEN, CardNumber.EIGHT);

    assertEquals(CardNumber.FIVE, card.getNorth());
    assertEquals(CardNumber.SIX, card.getSouth());
    assertEquals(CardNumber.SEVEN, card.getEast());
    assertEquals(CardNumber.EIGHT, card.getWest());
  }

  @Test
  public void testSetCardName() {
    RegularCard card = new RegularCard();
    card.setCardName("New Name");
    assertEquals("New Name", card.getCardName());
  }

  @Test
  public void testSetCardNameNull() {
    RegularCard card = new RegularCard();
    card.setCardName(null); // Should ignore null name
    assertEquals("Default Name", card.getCardName());
  }

  @Test
  public void testSetOwner() {
    RegularCard card = new RegularCard();
    card.setOwner(EPlayer.PLAYER_TWO);
    assertEquals(EPlayer.PLAYER_TWO, card.getOwner());
  }

  @Test
  public void testMakeClone() {
    RegularCard card = new RegularCard(CardNumber.THREE, CardNumber.FOUR, CardNumber.FIVE,
            CardNumber.SIX, "Clone Test", EPlayer.PLAYER_ONE);
    RegularCard clone = (RegularCard) card.makeClone();

    assertEquals(CardNumber.THREE, clone.getNorth());
    assertEquals(CardNumber.FOUR, clone.getSouth());
    assertEquals(CardNumber.FIVE, clone.getEast());
    assertEquals(CardNumber.SIX, clone.getWest());
    assertEquals("Clone Test", clone.getCardName());
    assertEquals(EPlayer.PLAYER_ONE, clone.getOwner());

    assertNotSame(card, clone);
  }
}


