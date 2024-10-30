package cs3500.threetrios.model.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs3500.threetrios.model.Cells.ICell;
import cs3500.threetrios.model.Cells.RegularCardCell;
import cs3500.threetrios.model.Cells.RegularHole;

/**
 * This class is design to provide static methods that could read a board config file.
 */
public class BoardConfigReader {

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
      // read row and col, and check if legal.
      int rowNum = Integer.parseInt(reader.readLine().trim());
      int colNum = Integer.parseInt(reader.readLine().trim());

      if(rowNum == 0 || colNum == 0) {
        throw new IllegalArgumentException("Row or Col could not be 0");
      }

      if((rowNum * colNum) % 2 == 0) {
        throw new IllegalArgumentException("Cell number must be even");
      }

      // Initialize grid.
      ICell[][] grid = new ICell[colNum][rowNum];

      for (int row = 0; row < rowNum; row++) {
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
}
