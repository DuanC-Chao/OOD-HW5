package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An advanced bot, using more sophisticated, composable strategies.
 * Strategies: AdvancedBot could keep a set of strategies.
 * Those Strategies will contribute to Bot's final decision by a Scoring system.
 * For more detail of Scoring System: Check AdvancedBotDocument.txt
 * Each strategy class will determine The amount of score to be added or decreased for a play.
 */
public class AdvancedBot implements IBot {

  List<IStrategy> strategies;

  /**
   * Default constructor for AdvancedBot.
   * Notice: Sequence of Strategies in list DOES MATTER.
   *
   * @param strategies The strategies composed.
   */
  public AdvancedBot(List<IStrategy> strategies) {
    this.strategies = strategies;
  }

  @Override
  public void play(ITripleTriadModel model) {
    //First, Define needed structure.
    List<Tuple<Integer, Integer>> loAvailableCoord = getAvailableTryCoordinate(model);
    if (loAvailableCoord == null) {
      return;
    }
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
      for (IStrategy strategy : strategies) {
        strategy.adjustPlayScore(model, play);
      }
    }

    Play chosenPlay = loPlays.get(0);
    for (Play play : loPlays) {
      if (play.score > chosenPlay.score) {
        chosenPlay = play;
      }
    }

    /*
    for (Play play : loPlays) {
      System.out.println("\n");
      System.out.println("Card: " + play.cardIdx + "\n");
      System.out.println("Col: " + play.colToPlay + "\n");
      System.out.println("Row: " + play.rowToPlay + "\n");
      System.out.println("Score: " + play.score + "\n");
      System.out.println("\n");
    }
     */

    model.playToGrid(2, chosenPlay.cardIdx, chosenPlay.colToPlay, chosenPlay.rowToPlay);
  }

  /**
   * Get a list of all coordinates that allows bot to place a card on.
   *
   * @param model The model to check availability for.
   * @return A list of Tuple of coordinate.
   * @throws IllegalStateException If no avaliable coord to place card.
   */
  protected List<Tuple<Integer, Integer>> getAvailableTryCoordinate(ITripleTriadModel model) {
    List<Tuple<Integer, Integer>> result = new ArrayList<>();
    IGrid grid = model.getGridClone();
    for (int col = 0; col < grid.getColNumber(); col++) {
      for (int row = 0; row < grid.getRowNumber(); row++) {
        if (grid.isLegalMove(col, row)) {
          result.add(new Tuple<>(col, row));
        }
      }
    }
    if (result.isEmpty()) {
      return null;
    }
    return result;
  }

}
