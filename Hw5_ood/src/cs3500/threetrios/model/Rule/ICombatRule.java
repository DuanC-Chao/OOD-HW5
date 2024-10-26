package cs3500.threetrios.model.Rule;

import cs3500.threetrios.model.Enums.CardNumber;

/**
 * Represents a combat rule.
 */
public interface ICombatRule extends IRule {

  /**
   * Takes two CardNumber, return an int, represents their size relation under CURRENT RULE.
   *
   * @param num1 The first CardNumber.
   * @param num2 The second CardNumber.
   * @return An int, If:
   * num1 > num2: return 1
   * num1 < num2: return -1
   * num1 = num2: return 0
   */
  int compare(CardNumber num1, CardNumber num2);
}
