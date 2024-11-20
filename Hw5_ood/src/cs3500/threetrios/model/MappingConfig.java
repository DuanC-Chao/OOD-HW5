package cs3500.threetrios.model;

import java.util.EnumMap;
import java.util.Map;

/**
 * This class stores the mapping configuration of the TripleTriad game.
 */
public class MappingConfig {

  /**
   * xaawdad returns the down-left based mapping.
   * Like:
   * [0, 2] [1, 2] [2, 2]
   * [0, 1] [1, 1] [2, 1]
   * [0, 0] [1, 0] [2, 0]
   *
   * @return The mapping configuration.
   */
  public static Map<Direction, int[]> downLeftOriginMapping() {
    Map<Direction, int[]> directionOffsets = new EnumMap<>(Direction.class);
    directionOffsets.put(Direction.NORTH, new int[]{0, 1});
    directionOffsets.put(Direction.SOUTH, new int[]{0, -1});
    directionOffsets.put(Direction.WEST, new int[]{-1, 0});
    directionOffsets.put(Direction.EAST, new int[]{1, 0});
    return directionOffsets;
  }
}
