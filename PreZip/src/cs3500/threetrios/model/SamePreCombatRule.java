package cs3500.threetrios.model;

/**
 * The "same" pre-combat rule.
 * As described in Level2.
 * The first one.
 */
public class SamePreCombatRule extends APreCombatRule {

  @Override
  public void flip(ICell[][] grid, int col, int row) {

    ICard startCard = TripleTriadUtilities.flipHelperOne(col, row, grid);

    EPlayer player = startCard.getOwner();

    TripleTriadUtilities.flipHelperOne(col, row, grid);

    ICell north = row > grid[0].length - 1 ? grid[col][row + 1] : null;
    ICell south = row > 0 ? grid[col][row - 1] : null;
    ICell west = col > 0 ? grid[col - 1][row] : null;
    ICell east = col < grid.length - 1 ? grid[col + 1][row] : null;

    FourIndicator fourIndicator = new FourIndicator();

    // First, check if there are >= 2 cards around target card actually exists
    if (!checkIfExistMoreThanTwoSurroundingCards(north, south, west, east)) {
      return;
    }

    // Check if any of the card belong to the opposite side, and has same value as
    // current card's value
    if (!checkIfExistAnyOppositeCardWithSameValue(startCard, fourIndicator, north, south, west, east)) {
      return;
    }

    if (fourIndicator.north) {
      if (north != null) {
        if (north.getCard() != null) {
          north.getCard().setOwner(player);
        }
      }
    }
    if (fourIndicator.south) {
      if (south != null) {
        if (south.getCard() != null) {
          south.getCard().setOwner(player);
        }
      }
    }
    if (fourIndicator.west) {
      if (west != null) {
        if (west.getCard() != null) {
          west.getCard().setOwner(player);
        }
      }
    }
    if (fourIndicator.east) {
      if (east != null) {
        if (east.getCard() != null) {
          east.getCard().setOwner(player);
        }
      }
    }
  }

  private boolean checkIfExistAnyOppositeCardWithSameValue(ICard currentCard, FourIndicator fourIndicator, ICell north, ICell south, ICell west, ICell east) {
    return sameRuleFlipHelperThree(currentCard, east, Direction.EAST, true, fourIndicator) ||
      sameRuleFlipHelperThree(currentCard, west, Direction.WEST, true, fourIndicator) ||
      sameRuleFlipHelperThree(currentCard, south, Direction.SOUTH, true, fourIndicator) ||
      sameRuleFlipHelperThree(currentCard, north, Direction.NORTH, true, fourIndicator);
  }

  /**
   * Takes a currentCard, and a cell, a direction.
   * Evaluate that, if the ICell has any card, and, if so,
   * check if the card from ICell belong to different player as current Card
   * If so, check, if the card from ICell has same CardNumber as the current card in corresponding
   * Directions, if so, return true, otherwise, return false.
   *
   * @param currentCard The current card to check.
   * @param cell        The cell containing the another card to check on.
   * @param direction   The direction of the cell, from current card's perspective.
   * @return A boolean.
   */
  private boolean sameRuleFlipHelperThree(ICard currentCard, ICell cell, Direction direction, boolean opposite, FourIndicator fourIndicator) {
    boolean flag = false;
    if (cell != null) {
      if (cell.getCard() != null) {
        if (cell.getCard().getOwner() != currentCard.getOwner() && opposite) {
          Direction otherDirection = TripleTriadUtilities.getOppositeDirection(direction);
          CardNumber otherNumberOfDirection = TripleTriadUtilities.getCardValue(cell.getCard(), otherDirection);
          CardNumber currentNumberOfDirection = TripleTriadUtilities.getCardValue(currentCard, direction);
          if (this.rule.compare(otherNumberOfDirection, currentNumberOfDirection) == 0) {
            flag = true;
            fourIndicator.setTrue(direction);
          }
        } else if (cell.getCard().getOwner() == currentCard.getOwner() && !opposite) {
          Direction otherDirection = TripleTriadUtilities.getOppositeDirection(direction);
          CardNumber otherNumberOfDirection = TripleTriadUtilities.getCardValue(cell.getCard(), otherDirection);
          CardNumber currentNumberOfDirection = TripleTriadUtilities.getCardValue(currentCard, direction);
          if (this.rule.compare(otherNumberOfDirection, currentNumberOfDirection) == 0) {
            flag = true;
          }
        }
      }
    }
    return flag;
  }
}
