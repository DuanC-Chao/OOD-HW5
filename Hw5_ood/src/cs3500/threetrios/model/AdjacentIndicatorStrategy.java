package cs3500.threetrios.model;

import static cs3500.threetrios.model.TripleTriadUtilities.getCardValue;
import static cs3500.threetrios.model.TripleTriadUtilities.getOppositeDirection;

/**
 * Represent an adjacent indicator, which takes a Play and the grid, and calculate.
 * The Expectation value of that Play.
 * For more details, check: ~/src/docs/AdvancedBotDocument.txt
 * Class Invariant:
 * expectation >= -15
 * expectation <= 15
 */
public class AdjacentIndicatorStrategy implements IStrategy {

  /**
   * Takes a play, and calculate and modify that play's expectation.
   *
   * @param model The model wished to operate.
   * @param play  The play wished to operate.
   */
  @Override
  public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
    int preResult = 0;
    int cardIdx = play.cardIdx;
    IGrid grid = model.getGridClone();
    ICell northNeighbor = grid.getCell(play.colToPlay - 1, play.rowToPlay);
    ICell southNeighbor = grid.getCell(play.colToPlay + 1, play.rowToPlay);
    ICell westNeighbor = grid.getCell(play.colToPlay, play.rowToPlay - 1);
    ICell eastNeighbor = grid.getCell(play.colToPlay, play.rowToPlay + 1);

    preResult += getSingleDirectionAdjustmentIndex(model, cardIdx, northNeighbor, Direction.NORTH);
    preResult += getSingleDirectionAdjustmentIndex(model, cardIdx, southNeighbor, Direction.SOUTH);
    preResult += getSingleDirectionAdjustmentIndex(model, cardIdx, westNeighbor, Direction.WEST);
    preResult += getSingleDirectionAdjustmentIndex(model, cardIdx, eastNeighbor, Direction.EAST);

    if (preResult > 20 || preResult < -40) {
      throw new IllegalStateException("Total adjustment index should be with in [-40, 20]");
    }

    play.addScore(normalize(preResult));
  }

  /**
   * Takes a model, a card index on player2's hand a neighbor ICell, and a direction.
   * Calculate the Adjustment index of the neighbor towards the Playing card.
   * Mapping: Safe: 5, Medium: 0, Dangerous: -10.
   *
   * @param cardIdx   The index of card on player 2's hand, 0-Based.
   * @param neighbor  The neighbor Cell of current card to check.
   * @param direction The direction of neighbor cell onto current checking card.
   * @return An Integer, ans adjustment index.
   */
  static int getSingleDirectionAdjustmentIndex(ReadOnlyTripleTriadModel model, int cardIdx, ICell neighbor, Direction direction) {

    // If neighbor is Boundary (no neighbor), return 5.
    if (neighbor == null) {
      return 5;
    }
    // If neighbor is hole, return 5
    if (neighbor.getType() == CellType.HOLE) {
      return 5;
    }
    // If neighbor is empty cell, return 0.
    if (neighbor.getCard() == null) {
      return 0;
    }

    ICombatRule ruleToUse = model.getCombatRule();
    CardNumber selfNumberOfDirection = getCardValue(model.getPlayerTwoClone().getHand().get(cardIdx), direction);
    CardNumber opponentNumberOfDirection = getCardValue(neighbor.getCard(), getOppositeDirection(direction));

    // If card belongs to self (Player2).
    if (neighbor.getCard().getOwner() == EPlayer.PLAYER_TWO) {

      // When greater or smaller than friend card, is considered dangerous, because may be flipped by combo.
      if (ruleToUse.compare(selfNumberOfDirection, opponentNumberOfDirection) != 0) {
        return -10;
      } else {
        return 5;
      }
    } else {
      // If card belongs to an enemy
      // When greater than enemy card, considered could flip, so should be motivated.
      if (ruleToUse.compare(selfNumberOfDirection, opponentNumberOfDirection) > 0) {
        return 5;
        // Otherwise, is neither dangerous nor motivated, react nothing.
      } else if (ruleToUse.compare(selfNumberOfDirection, opponentNumberOfDirection) <= 0) {
        return 0;
      }
    }
    throw new IllegalStateException("This line should never be executed.");
  }

  /**
   * Normalize a num within [-40, 20] to [-15, 15].
   *
   * @param num The number to be normalized.
   * @return A double.
   */
  static double normalize(int num) {
    return ((double) num + 40) / 2 - 15;
  }
}
