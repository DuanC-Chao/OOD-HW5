package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Combined testing class for CardFactory.
 * Contains example usage, model interface tests, and implementation tests in one.
 */
public class CardFactoryTest {

  // Model Interface-Testing Section (Public Method Tests)
  @Test
  public void testCreateRegularCardValid() {
    RegularCard card = CardFactory.createRegularCard("TestCard", "1", "2", "3", "A");

    Assert.assertEquals(CardNumber.ONE, card.getNorth());
    Assert.assertEquals(CardNumber.TWO, card.getSouth());
    Assert.assertEquals(CardNumber.THREE, card.getEast());
    Assert.assertEquals(CardNumber.A, card.getWest());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateRegularCardInvalidNumber() {
    CardFactory.createRegularCard("TestCard", "1", "invalid", "3", "A");
  }

  // Implementation-Testing Section (Testing Card Number Mapping Logic)
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardNumberMappingInvalidString() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "invalid");
  }

  @Test
  public void testGetCardNumberMappingA() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "A");
    Assert.assertEquals(CardNumber.A, cardNumbers[0]);
  }

  @Test
  public void testGetCardNumberMappingValidNumber() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "5");
    Assert.assertEquals(CardNumber.FIVE, cardNumbers[0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardNumberMappingOutOfRange() {
    CardNumber[] cardNumbers = new CardNumber[4];
    CardFactory.getCardNumberMapping(cardNumbers, 0, "11");
  }
}
