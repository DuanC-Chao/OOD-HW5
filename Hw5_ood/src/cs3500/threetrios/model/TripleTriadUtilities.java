package cs3500.threetrios.model;

/**
 * The class that holds Utilities methods for TripleTriad game.
 */
public class TripleTriadUtilities {

  /**
   * Helper method to get the value of the card based on the direction.
   *
   * @param card      The card to get the value from.
   * @param direction The direction.
   * @return The CardNumber of the specified direction.
   */
  public static CardNumber getCardValue(ICard card, Direction direction) {
    switch (direction) {
      case NORTH:
        return card.getNorth();
      case EAST:
        return card.getEast();
      case SOUTH:
        return card.getSouth();
      case WEST:
        return card.getWest();
      default:
        throw new IllegalArgumentException("Invalid direction");
    }
  }

  /**
   * Helper method to get the opposite direction for a given direction.
   *
   * @param direction The current direction.
   * @return The opposite direction.
   */
  public static Direction getOppositeDirection(Direction direction) {
    switch (direction) {
      case NORTH:
        return Direction.SOUTH;
      case SOUTH:
        return Direction.NORTH;
      case EAST:
        return Direction.WEST;
      case WEST:
        return Direction.EAST;
      default:
        throw new IllegalArgumentException("Invalid direction");
    }
  }

  /**
   * The helper method utilized by flip method.
   *
   * @param col  The col to flip.
   * @param row  The row to flip.
   * @param grid The grid as ICell[][].
   */
  public static ICard flipHelperOne(int col, int row, ICell[][] grid) {
    //Check if variables legal
    if (col < 0 || row < 0 || col >= grid.length || row >= grid[0].length) {
      throw new IllegalArgumentException("Row or col out of bounds.");
    }

    ICard startCard = grid[col][row].getCard();
    if (startCard == null) {
      throw new IllegalStateException("No card exists on the given col and row.");
    }

    return startCard;
  }
}
