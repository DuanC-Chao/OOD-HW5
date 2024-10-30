package cs3500.threetrios.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains static mehod to read card configuration file.
 */
public class CardConfigReader {
  /**
   * Read the card config file of given path, and return a List of Card.
   * Mapping: N S E W
   *
   * @param filePath The config file path.
   * @return List of card, which includes all cards.
   * @throws IOException              If unexpected I/O error happens.
   * @throws IllegalArgumentException If file format is not legal.
   */
  public static List<ICard> loadCardConfig(String filePath) throws IOException {
    List<ICard> cards = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;

      // Read each line
      while ((line = reader.readLine()) != null) {
        line = line.trim();

        if (line.isEmpty()) {
          continue;
        }

        // Try to split line
        String[] parts = line.split("\\s+");

        // Make sure splited line contains 5 parts
        if (parts.length != 5) {
          throw new IllegalArgumentException("Invalid format: " + line);
        }

        String name = parts[0];
        String north = parts[1];
        String south = parts[2];
        String east = parts[3];
        String west = parts[4];

        ICard card = CardFactory.createRegularCard(name, north, south, east, west);
        cards.add(card);
      }
    }
    return cards;
  }
}
