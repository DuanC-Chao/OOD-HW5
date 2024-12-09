package cs3500.threetrios.model;

/**
 * The plus pre combat rule.
 * As described in Level2 description.
 */
public class PlusPreCombatRule extends APreCombatRule {

  /**
   * A value keeper, keeps the sum of all four direction CardNumbers of currentCard.
   */
  public class ValueKeeper {

    public int north = 0;
    public int south = 0;
    public int west = 0;
    public int east = 0;

    public boolean northFromOpposite = false;
    public boolean southFromOpposite = false;
    public boolean westFromOpposite = false;
    public boolean eastFromOpposite = false;

    /**
     * Takes a direction, and check if that direction's card could be flipped.
     * @param direction The direction wished to check on.
     * @return A boolean, weather the direction could be flipped.
     */
    boolean ifCouldBeFlipped(Direction direction) {
      if (direction == Direction.NORTH) {
        if (!northFromOpposite || north == 0) {
          return false;
        }
        return north == south || north == east || north == west;
      } else if (direction == Direction.SOUTH || south == 0) {
        if (!southFromOpposite) {
          return false;
        }
        return south == north || south == east || south == west;
      } else if (direction == Direction.WEST || west == 0) {
        if (!westFromOpposite) {
          return false;
        }
        return west == north || west == east || west == south;
      } else if (direction == Direction.EAST || east == 0) {
        if (!eastFromOpposite) {
          return false;
        }
        return east == north || east == south || east == west;
      }
      return false;
    }

    /**
     * Takes a direction, and set the corresponding direction in ValueKeeper as true as belonging to the opposite side.
     * @param direction The direction to be operated on.
     */
    public void setDirectionAsBelongToOpposite(Direction direction) {
      if (direction == Direction.NORTH) {
        northFromOpposite = true;
      } else if (direction == Direction.SOUTH) {
        southFromOpposite = true;
      } else if (direction == Direction.WEST) {
        westFromOpposite = true;
      } else if (direction == Direction.EAST) {
        eastFromOpposite = true;
      }
    }

    /**
     * Takes a direction, and set the corresponding direction value as the value.
     * @param value The value to settle.
     * @param direction The direction to set.
     */
    public void setDirectionValue(int value, Direction direction) {
      if (direction == Direction.NORTH) {
        north = value;
      } else if (direction == Direction.SOUTH) {
        south = value;
      } else if (direction == Direction.WEST) {
        west = value;
      } else if (direction == Direction.EAST) {
        east = value;
      }
    }
  }

  @Override
  public void flip(ICell[][] grid, int col, int row) {

    ICard startCard = TripleTriadUtilities.flipHelperOne(col, row, grid);

    EPlayer player = startCard.getOwner();

    TripleTriadUtilities.flipHelperOne(col, row, grid);

    ICell north = row > grid[0].length - 1 ? grid[col][row + 1] : null;
    ICell south = row > 0 ? grid[col][row - 1] : null;
    ICell west = col > 0 ? grid[col - 1][row] : null;
    ICell east = col < grid.length - 1 ? grid[col + 1][row] : null;

    ValueKeeper v = new ValueKeeper();

    // First, check if there are >= 2 cards around target card actually exists
    if (!checkIfExistMoreThanTwoSurroundingCards(north, south, west, east)) {
      return;
    }

    buildValueKeeper(north, startCard, v, player, Direction.NORTH);
    buildValueKeeper(south, startCard, v, player, Direction.SOUTH);
    buildValueKeeper(west, startCard, v, player, Direction.WEST);
    buildValueKeeper(east, startCard, v, player, Direction.EAST);

    if (v.ifCouldBeFlipped(Direction.NORTH)) {
      if (north != null) {
        if (north.getCard() != null) {
          north.getCard().setOwner(player);
        }
      }
    }
    if (v.ifCouldBeFlipped(Direction.SOUTH)) {
      if (south != null) {
        if (south.getCard() != null) {
          south.getCard().setOwner(player);
        }
      }
    }
    if (v.ifCouldBeFlipped(Direction.WEST)) {
      if (west != null) {
        if (west.getCard() != null) {
          west.getCard().setOwner(player);
        }
      }
    }
    if (v.ifCouldBeFlipped(Direction.EAST)) {
      if (east != null) {
        if (east.getCard() != null) {
          east.getCard().setOwner(player);
        }
      }
    }
  }

  private void buildValueKeeper(ICell other, ICard startCard, ValueKeeper v, EPlayer player, Direction direction) {
    if (other != null) {
      if (other.getCard() != null) {
        Direction directionOfSelf = direction;
        Direction directionOfOther = TripleTriadUtilities.getOppositeDirection(direction);

        int otherCardNumber = TripleTriadUtilities.getCardValue(other.getCard(), directionOfOther).getNumber();
        int selfCardNumber = TripleTriadUtilities.getCardValue(startCard, directionOfSelf).getNumber();
        int result = otherCardNumber + selfCardNumber;
        v.setDirectionValue(result, direction);

        if (other.getCard().getOwner() != player) {
          v.setDirectionAsBelongToOpposite(direction);
        }
      }
    }
  }


}
