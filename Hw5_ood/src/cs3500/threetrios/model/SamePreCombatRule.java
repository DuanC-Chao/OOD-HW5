package cs3500.threetrios.model;

/**
 * The "same" pre-combat rule.
 * As described in Level2.
 * The first one.
 */
public class SamePreCombatRule implements IPreCombatRule{

  @Override
  public void flip(ICell[][] grid, int row, int col) {

    ICard startCard = null;

    TripleTriadUtilities.flipHelperOne(col, row, grid, startCard);

    ICell north = row > 0 ? grid[col][row - 1]: null;
    ICell south = row < grid.length - 1 ? grid[col + 1][row]: null;
  }
}
