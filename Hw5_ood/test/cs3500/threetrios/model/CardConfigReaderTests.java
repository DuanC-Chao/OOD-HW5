package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * To test Card Config reader.
 */
public class CardConfigReaderTests {

  @Test
  public void testValidCardConfig() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-valid.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("Card1 1 2 3 4\n");
      writer.write("Card2 A 1 5 3\n");
    }

    List<ICard> cards = CardConfigReader.loadCardConfig(filePath);
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
    CardConfigReader.loadCardConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectPartsCount() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-incorrect-parts.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("CardName 1 2 3\n");  // Missing one part
    }
    CardConfigReader.loadCardConfig(filePath);
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

    List<ICard> cards = CardConfigReader.loadCardConfig(filePath);
    Assert.assertEquals(2, cards.size());
  }
}
