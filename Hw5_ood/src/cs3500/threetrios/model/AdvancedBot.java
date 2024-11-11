package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

import static cs3500.threetrios.model.TripleTriadUtilities.getCardValue;
import static cs3500.threetrios.model.TripleTriadUtilities.getOppositeDirection;

/**
 * An advanced bot, using more sophisticated strategies.
 * Strategies: Keep a scoring system, choose the Play with highest score at the end.
 * BaseScore: 10
 * If on corner: Score + 10
 */
public class AdvancedBot extends ABot {

  /**
   * A class representing a play.
   */
  private class Play {

    final int cardIdx;

    final int colToPlay;

    final int rowToPlay;

    double score;

    /**
     * Construct a Play, which includes the card to play, and index to play to.
     *
     * @param cardIdx   Card index on Bot's hand, 0-Based.
     * @param colToPlay The column to play to.
     * @param rowToPlay The row to play to.
     */
    public Play(int cardIdx, int colToPlay, int rowToPlay) {
      this.cardIdx = cardIdx;
      this.colToPlay = colToPlay;
      this.rowToPlay = rowToPlay;
      this.score = 10;
    }

    /**
     * Add the score with given amount.
     *
     * @param scoreOffset The amount to add.
     */
    public void addScore(double scoreOffset) {
      this.score += scoreOffset;
    }

    /**
     * Multiple iteration times, with index.
     * @param index The index.
     * @param iteration The iteratiom time.
     */
    public void multiplyScore(double index, int iteration) {
      for (int i = 0; i < iteration; i++) {
        this.score *= index;
      }
    }
  }

  /**
   * Represent an adjacent indicator, which takes a Play and the grid, and calculate.
   * The Expectation value of that Play.
   * For more details, check: ~/src/docs/AdvancedBotDocument.txt
   * Class Invariant:
   * expectation >= -15
   * expectation <= 15
   */
  private static class AdjacentIndicator {

    /**
     * Takes a play, and calculate and modify that play's expectation.
     *
     * @param model The model wished to operate.
     * @param play  The play wished to operate.
     */
    static void modifyPlayExpectation(ITripleTriadModel model, Play play) {
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
    static int getSingleDirectionAdjustmentIndex(ITripleTriadModel model, int cardIdx, ICell neighbor, Direction direction) {

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

  @Override
  public void play(ITripleTriadModel model) {
    //First, Define needed structure.
    List<Tuple<Integer, Integer>> loAvailableCoord = getAvailableTryCoordinate(model);
    List<Play> loPlays = new ArrayList<>();

    for (int cardIdx = 0; cardIdx < model.getPlayerHand(2).size(); cardIdx++) {
      for (Tuple<Integer, Integer> coord : loAvailableCoord) {
        Play playToAdd = new Play(cardIdx, coord.getFirst(), coord.getSecond());
        loPlays.add(playToAdd);
      }
    }

    if (loPlays.isEmpty()) {
      throw new IllegalStateException("There should be at least one avaliable coordinate to play.");
    }

    // Iterate each play and change their scores
    for (Play play : loPlays) {
      // Assign basic scores
      play.addScore(10);

      // Assign corner score, and deduction, if apply
      if (model.isCorner(play.colToPlay, play.rowToPlay) != 0) {
        play.addScore(10);
        if (checkIfWillHideAdvantagePlayingToCorner(model.getCombatRule(), model.getPlayerHand(2), play.cardIdx, model.isCorner(play.colToPlay, play.rowToPlay))) {
          play.addScore(-10);
        }
      }

      // Modify value with expectation value
      AdjacentIndicator.modifyPlayExpectation(model, play);

      // Modify with flipping number
      int flipCount;
      flipCount = model.calculateFlips(EPlayer.PLAYER_TWO, model.getPlayerHand(2).get(play.cardIdx), play.colToPlay, play.rowToPlay);
      play.multiplyScore(1.2, flipCount);
    }

    Play chosenPlay = loPlays.get(0);

    for (Play play : loPlays) {
      if(play.score > chosenPlay.score) {
        chosenPlay = play;
      }
    }

    for (Play play : loPlays) {
      System.out.println("\n");
      System.out.println("Card: " + play.cardIdx + "\n");
      System.out.println("Col: " + play.colToPlay + "\n");
      System.out.println("Row: " + play.rowToPlay + "\n");
      System.out.println("Score: " + play.score + "\n");
      System.out.println("\n");
    }

    model.playToGrid(2, chosenPlay.cardIdx, chosenPlay.colToPlay, chosenPlay.rowToPlay);
  }

  /**
   * Check if the plays a card on corner will hide a card's advantage.
   *
   * @param rule    The rule to apply.
   * @param hand    The hand of player2.
   * @param cardIdx The index of card in hand.
   * @param corner  The integer representation of corner.
   * @return A boolean, weather advantage will be hide.
   */
  private boolean checkIfWillHideAdvantagePlayingToCorner(ICombatRule rule, List<ICard> hand, int cardIdx, int corner) {
    if (corner != 1 && corner != 2 && corner != 3 && corner != 4) {
      throw new IllegalArgumentException("The corner must be 1 or 2 or 3 or 4");
    }
    ICard card = hand.get(cardIdx);
    CardNumber northNum = getCardValue(card, Direction.NORTH);
    CardNumber eastNum = getCardValue(card, Direction.EAST);
    CardNumber southNum = getCardValue(card, Direction.SOUTH);
    CardNumber westNum = getCardValue(card, Direction.WEST);
    int alpha = 0;
    if (corner == 1) {
      alpha += rule.compare(northNum, westNum);
      alpha += rule.compare(northNum, southNum);
      alpha += rule.compare(eastNum, westNum);
      alpha += rule.compare(eastNum, southNum);
    } else if (corner == 2) {
      alpha += rule.compare(southNum, northNum);
      alpha += rule.compare(southNum, westNum);
      alpha += rule.compare(eastNum, westNum);
      alpha += rule.compare(eastNum, northNum);
    } else if (corner == 3) {
      alpha += rule.compare(westNum, northNum);
      alpha += rule.compare(westNum, eastNum);
      alpha += rule.compare(southNum, northNum);
      alpha += rule.compare(southNum, eastNum);
    } else {
      alpha += rule.compare(northNum, southNum);
      alpha += rule.compare(northNum, eastNum);
      alpha += rule.compare(westNum, eastNum);
      alpha += rule.compare(westNum, southNum);
    }

    return alpha >= 1;
  }
}
