package cs3500.threetrios.model;

/**
 * This rule is not Implemented in any way (Oct 26)
 * Under this rule, 1 > 2 > 3 > 4 > ... > A
 */
public class ReverseCombatRule implements ICombatRule {

  @Override
  public int compare(CardNumber num1, CardNumber num2) {
    return num2.compareTo(num1);
  }

  @Override
  public void flip(ICell[][] grid, int row, int col) {
    // Not implemented yet.
    throw new UnsupportedOperationException();
  }
}
