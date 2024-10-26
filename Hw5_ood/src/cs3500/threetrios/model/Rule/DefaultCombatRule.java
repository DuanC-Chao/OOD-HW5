package cs3500.threetrios.model.Rule;

import cs3500.threetrios.model.Enums.CardNumber;

/**
 * Represent the default Triple Triad rule.
 */
public class DefaultCombatRule implements ICombatRule{

  @Override
  public int compare(CardNumber num1, CardNumber num2) {
    return num1.compareTo(num2);
  }
}
