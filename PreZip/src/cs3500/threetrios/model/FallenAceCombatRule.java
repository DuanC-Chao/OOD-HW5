package cs3500.threetrios.model;

/**
 * The Fallen Ace rule, which A is smaller than 1.
 */
public class FallenAceCombatRule extends ACombatRule {

  /**
   * The default constructor.
   *
   * @param innerCombatRule The inner combat rule.
   */
  public FallenAceCombatRule(ICombatRule innerCombatRule) {
    super(innerCombatRule);
    if (innerCombatRule == null) {
      throw new IllegalArgumentException(
        "The inner combat rule cannot be null for Fallen Ace rule.");
    }
  }

  @Override
  public int compare(CardNumber num1, CardNumber num2) {
    if (num1 == CardNumber.A && num2 == CardNumber.ONE
      || num2 == CardNumber.A && num1 == CardNumber.ONE) {
      return -innerCombatRule.compare(num1, num2);
    } else {
      return innerCombatRule.compare(num1, num2);
    }
  }
}
