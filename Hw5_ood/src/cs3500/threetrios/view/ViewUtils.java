package cs3500.threetrios.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

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
    int rows = grid[0].length;
    int cols = grid.length;

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
}
