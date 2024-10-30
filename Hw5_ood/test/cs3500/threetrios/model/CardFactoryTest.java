package cs3500.threetrios.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Combined testing class for CardFactory.
 * Contains example usage, model interface tests, and implementation tests in one.
 */
public class CardFactoryTest {

  // Examples Section
  public static void main(String[] args) {
    RegularCard exampleCard = CardFactory.createRegularCard("ExampleCard", "1", "2", "3", "A");
    System.out.println("Example Card:");
    System.out.println("Name: " + "ExampleCard"); // Assuming there's a getName() in your model
    System.out.println("North: " + exampleCard.getNorth());
    System.out.println("East: " + exampleCard.getEast());
    System.out.println("South: " + exampleCard.getSouth());
    System.out.println("West: " + exampleCard.getWest());
  }

  // Model Interface-Testing Section (Public Method Tests)
  @Test
  public void testCreateRegularCardValid() {
    RegularCard card = CardFactory.createRegularCard("TestCard", "1", "2", "3", "A");

    assertEquals(CardNumber.ONE, card.getNorth());
    assertEquals(CardNumber.TWO, card.getSouth());
    assertEquals(CardNumber.THREE, card.getEast());
    assertEquals(CardNumber.A, card.getWest());
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
