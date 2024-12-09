package cs3500.threetrios.model;

/**
 * Represent the default Triple Triad rule.
 */
public class DefaultCombatRule extends ACombatRule {

  /**
   * The default constructor.
   */
  public DefaultCombatRule() {
    super(null);
  }

  @Override
  public int compare(CardNumber num1, CardNumber num2) {
    return num1.compareTo(num2);
  }

}
