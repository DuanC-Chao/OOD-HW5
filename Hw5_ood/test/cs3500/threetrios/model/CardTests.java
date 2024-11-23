package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * A test class for testing the functionality of {@link RegularCard}, {@link RegularCardCell},
 * {@link ConfigReader}, and {@link CardFactory}.
 * This class includes unit tests for constructors, methods, and specific use cases.
 */

public class CardTests {

  // Combined testing class for RegularCard.
  // Contains examples, model interface tests, and implementation tests in one.
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

    Assert.assertNotSame(card, clone);
  }

  // Combined testing class for RegularCardCell.
  // Contains examples, model interface tests, and implementation tests in one class.

  @Test
  public void testDefaultConstructorForCell() {
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

  @Test(expected = IllegalStateException.class)
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

  // To test Card Config reader.
  @Test
  public void testValidCardConfig() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-valid.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("Card1 1 2 3 4\n");
      writer.write("Card2 A 1 5 3\n");
    }

    List<ICard> cards = ConfigReader.loadCardConfig(filePath);
    Assert.assertEquals(2, cards.size());
    Assert.assertEquals("Card1", cards.get(0).getCardName());
    Assert.assertEquals(CardNumber.ONE, cards.get(0).getNorth());
    Assert.assertEquals(CardNumber.TWO, cards.get(0).getSouth());
    Assert.assertEquals(CardNumber.THREE, cards.get(0).getEast());
    Assert.assertEquals(CardNumber.FOUR, cards.get(0).getWest());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFormat() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-invalid-format.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("InvalidCardFormat\n");
    }
    ConfigReader.loadCardConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectPartsCount() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-incorrect-parts.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("CardName 1 2 3\n");  // Missing one part
    }
    ConfigReader.loadCardConfig(filePath);
  }

  @Test
  public void testEmptyLinesIgnored() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-empty-lines.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("\n"); // Empty line
      writer.write("Card1 1 2 3 4\n");
      writer.write("\n"); // Another empty line
      writer.write("Card2 A 5 4 3\n");
    }

    List<ICard> cards = ConfigReader.loadCardConfig(filePath);
    Assert.assertEquals(2, cards.size());
  }

  // Combined testing class for CardFactory.
  // Contains example usage, model interface tests, and implementation tests in one.
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
