package cs3500.threetrios.model;

/**
 * The abstract class for APreCombatRule, defined some shared field and behaviors by PreCombatRules.
 */
public abstract class APreCombatRule implements IPreCombatRule {

  /**
   * A class helps with indicating which cards among dour directions should be flipped.
   * Always indicates the other side cells that need to be flipped.
   */
  public class FourIndicator {

    public boolean north;

    public boolean south;

    public boolean west;

    public boolean east;

    public FourIndicator() {
      this.north = false;
      this.south = false;
      this.west = false;
      this.east = false;
    }

    public void setTrue(Direction direction) {
      if (direction == Direction.NORTH) {
        this.north = true;
      } else if (direction == Direction.SOUTH) {
        this.south = true;
      } else if (direction == Direction.WEST) {
        this.west = true;
      } else if (direction == Direction.EAST) {
        this.east = true;
      }
    }
  }

  /**
   * The ICombatRule, the pre-combat rule relies on it to determine how to behave.
   */
  protected ICombatRule rule;

  @Override
  public void getCombatRule(ICombatRule combatRule) {
    this.rule = combatRule;
  }

  /**
   * Takes four Cells, and check if they contains cards.
   * If more than or equal to two cells contains any card, return true.
   *
   * @param north The north cell.
   * @param south The south cell.
   * @param west  The west cell.
   * @param east  The east cell.
   * @return weather there exist >= 2 cards.
   */
  protected boolean checkIfExistMoreThanTwoSurroundingCards(ICell north, ICell south, ICell west, ICell east) {
    int acc = 0;

    ICard northCard = null;
    if (north != null) {
      northCard = north.getCard();
    }
    ICard southCard = null;
    if (south != null) {
      southCard = south.getCard();
    }
    ICard westCard = null;
    if (west != null) {
      westCard = west.getCard();
    }
    ICard eastCard = null;
    if (east != null) {
      eastCard = east.getCard();
    }

    if (northCard != null) {
      acc++;
    }

    if (southCard != null) {
      acc++;
    }

    if (westCard != null) {
      acc++;
    }

    if (eastCard != null) {
      acc++;
    }

    return acc >= 2;
  }
}
