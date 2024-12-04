package cs3500.threetrios.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import cs3500.threetrios.model.Tuple;

/**
 * This class holds some Utility methods different parts of view may need.
 */
public class ViewUtils {

  /**
   * Takes a panel, and refresh the panel.
   *
   * @param panel The panel to refresh.
   */
  public static void refreshPanel(JPanel panel) {
    panel.revalidate();
    panel.repaint();
  }

  /**
   * Takes a array[col][row], and convert into a 1D list.
   * Which could be used directly in GridLayout.
   * Return the T and its coord in the grid, 0-Based.
   * @param grid An 2D array[col][row].
   * @param <T>  A generic Type.
   * @return The result List, contains a T and its coordiante in Grid.
   */
  public static <T> List<Tuple<T,Tuple<Integer, Integer>>> toGridLayoutList(T[][] grid) {
    List<Tuple<T,Tuple<Integer, Integer>>> list = new ArrayList<>();
    int cols = grid[0].length;
    int rows = grid.length;

    for (int row = rows - 1; row >= 0; row--) {
      for (int col = 0; col < cols; col++) {
        T t = grid[row][col];
        Integer colInGrid = col;
        Integer rowInGrid = row;
        Tuple<Integer, Integer> tuple1 = new Tuple<>(colInGrid, rowInGrid);
        Tuple<T, Tuple<Integer, Integer>> tuple2 = new Tuple<>(t, tuple1);
        list.add(tuple2);
      }
    }
    return list;
  }

  /**
   * Helper method, load file with just name, and create temp file.
   * And return absolute path of that temp file.
   *
   * @param resourcePath Config file's name on JAR.
   * @return Absolute path of temp file.
   */
  public static String loadResourceFile(String resourcePath) {
    try (InputStream inputStream = ViewTestProgram.class.getResourceAsStream(resourcePath)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("Resource not found: " + resourcePath);
      }

      File tempFile = File.createTempFile("temp_", ".txt");
      tempFile.deleteOnExit();

      try (FileOutputStream outStream = new FileOutputStream(tempFile);
           Scanner scanner = new Scanner(inputStream)) {
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          outStream.write((line + "\n").getBytes());
        }
      }
      return tempFile.getAbsolutePath();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
