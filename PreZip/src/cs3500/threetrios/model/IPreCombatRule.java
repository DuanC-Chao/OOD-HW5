package cs3500.threetrios.model;

/**
 * The interface represents a pre-combat rule.
 * Make effort before the actual flipping session based on CombatRule.
 */
public interface IPreCombatRule extends IFlippable {

  /**
   * Get a combat rule, and settle this rule as its inner combat rule.
   * @param combatRule The combat rule to be settled.
   */
  void getCombatRule(ICombatRule combatRule);
}
