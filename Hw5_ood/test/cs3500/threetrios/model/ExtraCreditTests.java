package cs3500.threetrios.model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExtraCreditTests {
  private ACombatRule defaultRule;
  private ACombatRule fallenAceRule;
  private ICombatRule reverseRule;
  private SamePreCombatRule samePreCombatRule;

  @Before
  public void setUp() {
    defaultRule = new DefaultCombatRule();
    fallenAceRule = new FallenAceCombatRule(defaultRule);
    reverseRule = new ReverseCombatRule(defaultRule);
    samePreCombatRule = new SamePreCombatRule();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullInnerRule() {
    new FallenAceCombatRule(null); // Should throw an exception
  }

  @Test
  public void testCompareAceVsOne() {
    assertEquals(-9, fallenAceRule.compare(CardNumber.A, CardNumber.ONE));
    assertEquals(9, fallenAceRule.compare(CardNumber.ONE, CardNumber.A));
  }

  @Test
  public void testCompareAceVsAce() {
    assertEquals(defaultRule.compare(CardNumber.A, CardNumber.A),
      fallenAceRule.compare(CardNumber.A, CardNumber.A));
  }

  @Test
  public void testCompareOneVsOne() {
    assertEquals(defaultRule.compare(CardNumber.ONE, CardNumber.ONE),
      fallenAceRule.compare(CardNumber.ONE, CardNumber.ONE));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReverseConstructorWithNull() {
    new ReverseCombatRule(null);
  }

  @Test
  public void testReverseCompareTwoCards() {
    assertEquals(-defaultRule.compare(CardNumber.TWO, CardNumber.THREE),
      reverseRule.compare(CardNumber.TWO, CardNumber.THREE));
    assertEquals(-defaultRule.compare(CardNumber.FOUR, CardNumber.FIVE),
      reverseRule.compare(CardNumber.FOUR, CardNumber.FIVE));
  }

  @Test
  public void testReverseCompareAceVsOne() {
    assertEquals(-defaultRule.compare(CardNumber.A, CardNumber.ONE),
      reverseRule.compare(CardNumber.A, CardNumber.ONE));
  }

  @Test
  public void testReverseCompareSameCard() {
    assertEquals(0, reverseRule.compare(CardNumber.A, CardNumber.A));
    assertEquals(0, reverseRule.compare(CardNumber.ONE, CardNumber.ONE));
  }

  @Test
  public void testReverseCompareExtremeValues() {
    assertEquals(-defaultRule.compare(CardNumber.A, CardNumber.SEVEN),
      reverseRule.compare(CardNumber.A, CardNumber.SEVEN));
    assertEquals(-defaultRule.compare(CardNumber.SEVEN, CardNumber.A),
      reverseRule.compare(CardNumber.SEVEN, CardNumber.A));
  }

}
