package cs3500.threetrios.model;

/**
 * Represents a hole, which is a cell that could not hold card.
 */
public class RegularHole implements ICell {

  /**
   * The default constructor.
   */
  public RegularHole() {
    // Nothing to be initailized.
  }

  @Override
  public ICard getCard() {
    return null;
  }

  @Override
  public void setCard(ICard card) throws CouldNotPlaceCardException {
    throw new CouldNotPlaceCardException("Trying to place card to Hole.");
  }

  @Override
  public CellType getType() {
    return CellType.HOLE;
  }

  @Override
  public boolean couldPlace() {
    return false;
  }

  @Override
  public boolean myCompare(ICell other) {
    if (other == null) {
      return false;
    }
    return other instanceof RegularHole;
  }

  @Override
  public ICell makeClone() {
    return new RegularHole();
  }
}
