package cs3500.threetrios.model.Rule;

import java.util.List;

import cs3500.threetrios.model.Cells.ICell;
import cs3500.threetrios.model.Enums.CardNumber;
import cs3500.threetrios.model.Grid.IGrid;

/**
 * Represents a combat rule.
 */
public interface ICombatRule {

  /**
   * Takes two CardNumber, return an int, represents their size relation under CURRENT RULE.
   *
   * @param num1 The first CardNumber.
   * @param num2 The second CardNumber.
   * @return An int, If:
   * num1 > num2: return 1
   * num1 < num2: return -1
   * num1 = num2: return 0
   */
  int compare(CardNumber num1, CardNumber num2);

  /**
   * Takes a grid to operate, and a col & row to start flipping.
   *
   * @param grid The grid wished to operate,
   *             it must be the reference to the grid itself, not clone.
   * @param row The row to start flipping with, 0-based.
   * @param col The col to start flipping with, 0-based.
   * @throws IllegalArgumentException If row or col are not legal.
   * @throws IllegalStateException If there is no card on the given row and col.
   */
  void flip(ICell[][] grid, int row, int col);
}
