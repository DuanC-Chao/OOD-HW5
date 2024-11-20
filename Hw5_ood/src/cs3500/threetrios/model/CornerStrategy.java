package cs3500.threetrios.model;

import java.util.List;

import static cs3500.threetrios.model.TripleTriadUtilities.getCardValue;

/**
 * This strategy increase/decrease score based on two things.
 * One, if the play is to play in a corner.
 * Two, if play to corner will hide a card's advantages sides.
 * For more information, check AdvancedBotDocument.txt
 */
public class CornerStrategy implements IStrategy {

  /**
   * Check if the plays a card on corner will hide a card's advantage.
   *
   * @param rule    The rule to apply.
   * @param hand    The hand of player2.
   * @param cardIdx The index of card in hand.
   * @param corner  The integer representation of corner.
   * @return A boolean, weather advantage will be hide.
   */
  private boolean checkIfWillHideAdvantagePlayingToCorner(ICombatRule rule,
                                                          List<ICard> hand,
                                                          int cardIdx, int corner) {
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

  @Override
  public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
    // Assign corner score, and deduction, if apply
    if (model.isCorner(play.colToPlay, play.rowToPlay) != 0) {
      play.addScore(10);
      if (checkIfWillHideAdvantagePlayingToCorner(model.getCombatRule(),
          model.getPlayerHand(2), play.cardIdx,
          model.isCorner(play.colToPlay, play.rowToPlay))) {
        play.addScore(-10);
      }
    }
  }
}
