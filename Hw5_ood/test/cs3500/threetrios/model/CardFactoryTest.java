package cs3500.threetrios.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardFactoryTest {

  @Test
  public void testCreateRegularCardValid() {
    RegularCard card = CardFactory.createRegularCard
            ("TestCard", "1", "2", "3", "A");

    assertEquals(CardNumber.ONE, card.getNorth());
    assertEquals(CardNumber.TWO, card.getEast());
    assertEquals(CardNumber.THREE, card.getSouth());
    assertEquals(CardNumber.A, card.getWest());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRegularCardInvalidNumber() {
    CardFactory.createRegularCard
            ("TestCard", "1", "invalid", "3", "A");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardNumberMappingInvalidString() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "invalid");
  }

  @Test
  public void testGetCardNumberMappingA() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "A");
    assertEquals(CardNumber.A, cardNumbers[0]);
  }

  @Test
  public void testGetCardNumberMappingValidNumber() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "5");
    assertEquals(CardNumber.FIVE, cardNumbers[0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardNumberMappingOutOfRange() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "11");
  }
}
