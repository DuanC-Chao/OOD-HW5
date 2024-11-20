package cs3500.threetrios.model;

/**
 * A strategy that evaluates potential moves based on corner positions.
 * Corners are generally advantageous positions as cards placed there
 * only expose two sides, making them harder to flip.
 * This strategy inspects all four corners of the grid to determine optimal
 * moves for placing cards.
 */
public class CornerCheckingStrategy implements IStrategy {
  @Override
  public void adjustPlayScore(ReadOnlyTripleTriadModel model, Play play) {
    // Check each corner
    model.isCorner(0, 0);
    model.isCorner(2, 0);
    model.isCorner(0, 2);
    model.isCorner(2, 2);

  }
}

