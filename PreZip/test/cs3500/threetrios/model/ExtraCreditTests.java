package cs3500.threetrios.model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Level 1, 2 and 3 for the extra credit assignment.
 */
public class ExtraCreditTests {
  private ACombatRule defaultRule;
  private ACombatRule fallenAceRule;
  private ICombatRule reverseRule;
  private SamePreCombatRule samePreCombatRule;
  private PlusPreCombatRule plusPreCombatRule;
  private ICell[][] grid;

  @Before
  public void setUp() {
    defaultRule = new DefaultCombatRule();
    fallenAceRule = new FallenAceCombatRule(defaultRule);
    reverseRule = new ReverseCombatRule(defaultRule);
    samePreCombatRule = new SamePreCombatRule();
    plusPreCombatRule = new PlusPreCombatRule();
    grid = new ICell[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        grid[i][j] = new RegularCardCell();
      }
    }
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

  @Test
  public void testPlusRuleFlipTwoCards() {
    ICard centerCard = new RegularCard(CardNumber.FIVE, CardNumber.FOUR, CardNumber.THREE,
      CardNumber.TWO, "Center", EPlayer.PLAYER_ONE);
    grid[1][1].setCard(centerCard);

    ICard northCard = new RegularCard(CardNumber.TWO, CardNumber.FOUR, CardNumber.ONE,
      CardNumber.THREE, "North", EPlayer.PLAYER_TWO);
    grid[0][1].setCard(northCard);

    ICard southCard = new RegularCard(CardNumber.THREE, CardNumber.FOUR, CardNumber.TWO,
      CardNumber.ONE, "South", EPlayer.PLAYER_TWO);
    grid[2][1].setCard(southCard);

    plusPreCombatRule.flip(grid, 1, 1);

    assertEquals(EPlayer.PLAYER_TWO, grid[0][1].getCard().getOwner());
    assertEquals(EPlayer.PLAYER_TWO, grid[2][1].getCard().getOwner());
  }

  @Test
  public void testPlusRuleNoFlips() {
    ICard centerCard = new RegularCard(CardNumber.FIVE, CardNumber.FOUR, CardNumber.THREE,
      CardNumber.TWO, "Center", EPlayer.PLAYER_ONE);
    grid[1][1].setCard(centerCard);

    ICard northCard = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.ONE,
      CardNumber.TWO, "North", EPlayer.PLAYER_TWO);
    grid[0][1].setCard(northCard);

    ICard southCard = new RegularCard(CardNumber.SIX, CardNumber.SEVEN, CardNumber.FIVE,
      CardNumber.FOUR, "South", EPlayer.PLAYER_TWO);
    grid[2][1].setCard(southCard);

    plusPreCombatRule.flip(grid, 1, 1);

    assertEquals(EPlayer.PLAYER_TWO, grid[0][1].getCard().getOwner());
    assertEquals(EPlayer.PLAYER_TWO, grid[2][1].getCard().getOwner());
  }

  @Test
  public void testPlusRuleEdgeCase() {
    ICard centerCard = new RegularCard(CardNumber.FIVE, CardNumber.FOUR, CardNumber.THREE,
      CardNumber.TWO, "Corner", EPlayer.PLAYER_ONE);
    grid[0][0].setCard(centerCard);

    ICard eastCard = new RegularCard(CardNumber.TWO, CardNumber.FIVE, CardNumber.ONE,
      CardNumber.THREE, "East", EPlayer.PLAYER_TWO);
    grid[0][1].setCard(eastCard);

    plusPreCombatRule.flip(grid, 0, 0);

    assertEquals(EPlayer.PLAYER_TWO, grid[0][1].getCard().getOwner());
  }

  @Test
  public void testPlusRuleNoSurroundingCards() {
    ICard centerCard = new RegularCard(CardNumber.FIVE, CardNumber.FOUR, CardNumber.THREE,
      CardNumber.TWO, "Center", EPlayer.PLAYER_ONE);
    grid[1][1].setCard(centerCard);

    plusPreCombatRule.flip(grid, 1, 1);

    assertEquals(EPlayer.PLAYER_ONE, grid[1][1].getCard().getOwner());
  }

  @Test
  public void testSameRuleFlipTwoCards() {
    ICard centerCard = new RegularCard(CardNumber.THREE, CardNumber.FOUR, CardNumber.FIVE, CardNumber.SIX, "Center", EPlayer.PLAYER_ONE);
    grid[1][1].setCard(centerCard);

    ICard northCard = new RegularCard(CardNumber.FIVE, CardNumber.THREE, CardNumber.ONE, CardNumber.FOUR, "North", EPlayer.PLAYER_TWO);
    grid[0][1].setCard(northCard);

    ICard southCard = new RegularCard(CardNumber.FOUR, CardNumber.FIVE, CardNumber.TWO, CardNumber.ONE, "South", EPlayer.PLAYER_TWO);
    grid[2][1].setCard(southCard);

    samePreCombatRule.flip(grid, 1, 1);

    assertEquals(EPlayer.PLAYER_ONE, grid[0][1].getCard().getOwner());
    assertEquals(EPlayer.PLAYER_ONE, grid[2][1].getCard().getOwner());
  }

  @Test
  public void testSameRuleNoFlips() {
    ICard centerCard = new RegularCard(CardNumber.FIVE, CardNumber.FOUR, CardNumber.THREE,
      CardNumber.TWO, "Center", EPlayer.PLAYER_ONE);
    grid[1][1].setCard(centerCard);

    ICard northCard = new RegularCard(CardNumber.ONE, CardNumber.ONE, CardNumber.ONE,
      CardNumber.ONE, "North", EPlayer.PLAYER_TWO);
    grid[0][1].setCard(northCard);

    ICard southCard = new RegularCard(CardNumber.SIX, CardNumber.SIX, CardNumber.SIX,
      CardNumber.SIX, "South", EPlayer.PLAYER_TWO);
    grid[2][1].setCard(southCard);

    samePreCombatRule.flip(grid, 1, 1);

    assertEquals(EPlayer.PLAYER_TWO, grid[0][1].getCard().getOwner());
    assertEquals(EPlayer.PLAYER_TWO, grid[2][1].getCard().getOwner());
  }

}
