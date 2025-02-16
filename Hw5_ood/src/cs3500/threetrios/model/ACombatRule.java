package cs3500.threetrios.model;

import java.util.Map;
import java.util.Stack;

import static cs3500.threetrios.model.Direction.downLeftOriginMapping;
import static cs3500.threetrios.model.TripleTriadUtilities.getCardValue;
import static cs3500.threetrios.model.TripleTriadUtilities.getOppositeDirection;

/**
 * The abstract class for CombatRule, provides shared method among all subclasses.
 */
public abstract class ACombatRule implements ICombatRule {

  /**
   * The inner combat rule to operatr on.
   */
  protected ICombatRule innerCombatRule;

  /**
   * The pre-combat rule, takes action before the actual flipping.
   */
  protected IPreCombatRule preCombatRule;

  /**
   * The default constructor, takes an ICombatRule and assign value as innerCombatRule.
   * @param innerCombatRule The inner combat rule to assign.
   */
  public ACombatRule(ICombatRule innerCombatRule) {
    this.innerCombatRule = innerCombatRule;
  }

  @Override
  public void takePreCombatRule(IPreCombatRule preCombatRule) {
    this.preCombatRule = preCombatRule;
  }

  @Override
  public void flip(ICell[][] grid, int col, int row) {

    // If has any pre-combat rule, use it to flip first.
    if (preCombatRule != null) {
      preCombatRule.getCombatRule(this);
      preCombatRule.flip(grid, col, row);
    }

    ICard startCard = TripleTriadUtilities.flipHelperOne(col, row, grid);

    // Get owner of start card.
    EPlayer player = startCard.getOwner();

    // Use stack to do DFS
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[]{col, row});

    // Mapping each enum Direction to an int[x, y]
    Map<Direction, int[]> directionOffsets = downLeftOriginMapping();

    while (!stack.isEmpty()) {
      int[] position = stack.pop();
      int currentCol = position[0];
      int currentRow = position[1];
      ICard currentCard = grid[currentCol][currentRow].getCard();

      // For each of the card added to the stack, set its' owner as startCard's owner.
      currentCard.setOwner(player);

      // Iterate all directions, and check if Card on the new coordinate could be added
      // To the stack, the sequence is: E, W, N, S
      for (Direction direction : Direction.values()) {
        int[] offset = directionOffsets.get(direction);
        int newCol = currentCol + offset[0];
        int newRow = currentRow + offset[1];

        // Making sure the new coordinate to check is within the grid.
        if (newCol >= 0 && newRow >= 0 && newCol < grid.length && newRow < grid[0].length) {
          ICell neighborCell = grid[newCol][newRow];
          ICard neighborCard = neighborCell.getCard();

          // If exist neighbor card, and belong to opposite.
          if (neighborCard != null && neighborCard.getOwner() != player) {
            // Use compare to calculate if should flip.
            CardNumber currentCardValue = getCardValue(currentCard, direction);
            CardNumber neighborCardValue = getCardValue(neighborCard,
              getOppositeDirection(direction));

            if (compare(currentCardValue, neighborCardValue) > 0) {
              neighborCard.setOwner(player);
              stack.push(new int[]{newCol, newRow});
            }
          }
        }
      }
    }
  }
}
