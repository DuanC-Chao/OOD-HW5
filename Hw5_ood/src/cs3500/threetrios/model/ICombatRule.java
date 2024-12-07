package cs3500.threetrios.model;

/**
 * Represents a combat rule.
 */
public interface ICombatRule extends IFlippable {

  /**
   * Takes two CardNumber, return an int, represents their size relation under CURRENT RULE.
   * num1 > num2: return 1
   * num1 < num2: return -1
   * num1 = num2: return 0
   *
   * @param num1 The first CardNumber.
   * @param num2 The second CardNumber.
   * @return An int.
   */
  int compare(CardNumber num1, CardNumber num2);

  /**
   * Takes a pre-combat rule and settle it as the pre-combat rule used by this Combat rule.
   * @param preCombatRule The preCombatRule to be settled.
   */
  void takePreCombatRule(IPreCombatRule preCombatRule);
}
