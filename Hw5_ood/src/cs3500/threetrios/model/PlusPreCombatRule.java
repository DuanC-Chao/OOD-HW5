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
     * @param direction
     * @return
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
  }

  @Override
  public void flip(ICell[][] grid, int row, int col) {

    ICard startCard = TripleTriadUtilities.flipHelperOne(col, row, grid);

    EPlayer player = startCard.getOwner();

    TripleTriadUtilities.flipHelperOne(col, row, grid);

    ICell north = row > 0 ? grid[col][row - 1] : null;
    ICell south = row < grid.length - 1 ? grid[col][row + 1] : null;
    ICell west = col < grid[0].length - 1 ? grid[col - 1][row] : null;
    ICell east = col > 0 ? grid[col + 1][row] : null;

    ValueKeeper v = new ValueKeeper();

    // First, check if there are >= 2 cards around target card actually exists
    if (!checkIfExistMoreThanTwoSurroundingCards(north, south, west, east)) {
      return;
    }

    boolean shouldFlipNorth = false;
    boolean shouldFlipSouth = false;
    boolean shouldFlipWest = false;
    boolean shouldFlipEast = false;

    buildValueKeeper(north, startCard, v, player, Direction.NORTH);
    buildValueKeeper(south, startCard, v, player, Direction.SOUTH);
    buildValueKeeper(west, startCard, v, player, Direction.WEST);
    buildValueKeeper(east, startCard, v, player, Direction.EAST);


  }

  private void buildValueKeeper(ICell other, ICard startCard, ValueKeeper v, EPlayer player, Direction direction) {
    if (other != null) {
      if (other.getCard() != null) {
        Direction directionOfSelf = direction;
        Direction directionOfOther = TripleTriadUtilities.getOppositeDirection(direction);

        int otherCardNumber = TripleTriadUtilities.getCardValue(other.getCard(), directionOfOther).getNumber();
        int selfCardNumber = TripleTriadUtilities.getCardValue(startCard, directionOfSelf).getNumber();
        int result = otherCardNumber + selfCardNumber;
        v.north = result;

        if (other.getCard().getOwner() != player) {
          v.northFromOpposite = true;
        }
      }
    }
  }


}
