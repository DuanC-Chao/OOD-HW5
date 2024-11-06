package cs3500.threetrios.model;

import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the BoardConfigReader class.
 */
public class BoardConfigReaderTest {

  @Test
  public void testValidGridConfig() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-valid.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("3 3\n");
      writer.write("CXC\n");
      writer.write("XCX\n");
      writer.write("CXC\n");
    }

    ICell[][] grid = BoardConfigReader.loadGridConfig(filePath);
    assertEquals(3, grid[0].length);
    assertEquals(3, grid.length);
    assertTrue(grid[0][0] instanceof RegularCardCell);
    assertTrue(grid[1][0] instanceof RegularHole);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowOrColZero() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-zero.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("0 3\n");
    }
    BoardConfigReader.loadGridConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnevenGridDimensions() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-even-dimensions.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("2 2\n");
      writer.write("CC\n");
      writer.write("CC\n");
    }
    BoardConfigReader.loadGridConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnknownCellType() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-unknown-cell.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("3 3\n");
      writer.write("CYC\n");
      writer.write("XCX\n");
      writer.write("CXC\n");
    }
    BoardConfigReader.loadGridConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRowLengthMismatch() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-row-mismatch.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("3 3\n");
      writer.write("CC\n");
      writer.write("XCX\n");
      writer.write("CXC\n");
    }
    BoardConfigReader.loadGridConfig(filePath);
  }
}

