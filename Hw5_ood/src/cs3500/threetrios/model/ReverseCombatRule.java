package cs3500.threetrios.model;

/**
 * The rule that reverse the output of the previous rule.
 */
public class ReverseCombatRule extends ACombatRule {

  /**
   * The default constructor.
   * @param rule The inner rule to be set.
   */
  public ReverseCombatRule(ICombatRule rule) {
    super(rule);
    if (rule == null) {
      throw new IllegalArgumentException("Rule cannot be null for ReverseCombatRule");
    }
  }

  @Override
  public int compare(CardNumber num1, CardNumber num2) {
    return -this.innerCombatRule.compare(num1, num2);
  }
}
