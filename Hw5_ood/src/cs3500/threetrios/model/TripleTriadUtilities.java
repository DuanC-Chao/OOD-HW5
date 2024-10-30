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
}
