package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class of Bot.
 */
public abstract class ABot implements IBot {

  @Override
  public abstract void play(ITripleTriadModel model);

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
      throw new IllegalStateException("No available try coordinate found");
    }
    return result;
  }
}
