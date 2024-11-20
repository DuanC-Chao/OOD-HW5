package cs3500.threetrios.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods to read card and board config files.
 */
public class ConfigReader {

  /**
   * Read the board config file with given path.
   *
   * @param filePath The config file path.
   * @return A grid.
   * @throws IOException              If error happen when trying to read file.
   * @throws IllegalArgumentException If format of file is not legal.
   * @throws IllegalArgumentException If col and row read from file is no legal.
   */
  public static ICell[][] loadGridConfig(String filePath) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      // Split the first line and read.
      String[] dimensions = reader.readLine().trim().split("\\s+");
      if (dimensions.length != 2) {
        throw new IllegalArgumentException("First line must contain exactly " +
          "two integers for row and col.");
      }

      int rowNum = Integer.parseInt(dimensions[0]);
      int colNum = Integer.parseInt(dimensions[1]);

      if (rowNum == 0 || colNum == 0) {
        throw new IllegalArgumentException("Row or Col could not be 0");
      }

      if ((rowNum * colNum) % 2 == 0) {
        throw new IllegalArgumentException("Cell number must be even");
      }

      // Initialize grid.
      ICell[][] grid = new ICell[colNum][rowNum];

      for (int row = grid[0].length - 1; row >= 0; row--) {
        String line = reader.readLine().trim();

        if (line.length() != colNum) {
          throw new IllegalArgumentException("Row length does not match col count.");
        }

        for (int col = 0; col < colNum; col++) {
          char cellType = line.charAt(col);

          // Add cell to grid based on symbol.
          switch (cellType) {
            case 'C':
              grid[col][row] = new RegularCardCell();
              break;
            case 'X':
              grid[col][row] = new RegularHole();
              break;
            default:
              throw new IllegalArgumentException("Unknown Cell type: " + cellType);
          }
        }
      }
      return grid;
    }
  }

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
