package cs3500.threetrios.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Combined testing class for RegularCardCell.
 * Contains examples, model interface tests, and implementation tests in one class.
 */
public class RegularCardCellTest {

  // Examples Section
  public static void main(String[] args) {
    // Example usage of RegularCardCell
    RegularCard exampleCard = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Example Card", EPlayer.PLAYER_ONE);
    RegularCardCell exampleCell = new RegularCardCell(exampleCard);

    System.out.println("Example RegularCardCell:");
    System.out.println("Cell Type: " + exampleCell.getType());
    System.out.println("Card Owner: " + exampleCell.getCard().getOwner());
    System.out.println("Card North Value: " + exampleCell.getCard().getNorth());
    System.out.println("Card East Value: " + exampleCell.getCard().getEast());
    System.out.println("Card South Value: " + exampleCell.getCard().getSouth());
    System.out.println("Card West Value: " + exampleCell.getCard().getWest());
  }

  // Model Interface-Testing Section (Public Method Tests)

  @Test
  public void testDefaultConstructor() {
    RegularCardCell cell = new RegularCardCell();
    assertNull(cell.getCard());
    assertEquals(CellType.CARD_CELL, cell.getType());
  }

  @Test
  public void testCloneConstructor() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    RegularCardCell cell = new RegularCardCell(card);
    assertEquals(card, cell.getCard());
  }

  @Test
  public void testSetCardInEmptyCell() throws CouldNotPlaceCardException {
    RegularCardCell cell = new RegularCardCell();
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    cell.setCard(card);
    assertEquals(card, cell.getCard());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCardWithNull() throws CouldNotPlaceCardException {
    RegularCardCell cell = new RegularCardCell();
    cell.setCard(null);
  }

  @Test(expected = CouldNotPlaceCardException.class)
  public void testSetCardInOccupiedCell() throws CouldNotPlaceCardException {
    RegularCard card1 = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Card 1", EPlayer.PLAYER_ONE);
    RegularCard card2 = new RegularCard(CardNumber.FIVE, CardNumber.SIX, CardNumber.SEVEN,
            CardNumber.EIGHT, "Card 2", EPlayer.PLAYER_TWO);
    RegularCardCell cell = new RegularCardCell(card1);
    cell.setCard(card2);
  }

  // Implementation-Testing Section (Testing Cloning Functionality)
  @Test
  public void testMakeCloneEmptyCell() {
    RegularCardCell cell = new RegularCardCell();
    RegularCardCell clonedCell = (RegularCardCell) cell.makeClone();

    assertNotSame(cell, clonedCell);
    assertNull(clonedCell.getCard());
  }

  @Test
  public void testMakeCloneWithCard() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Original Card", EPlayer.PLAYER_ONE);
    RegularCardCell cell = new RegularCardCell(card);

    RegularCardCell clonedCell = (RegularCardCell) cell.makeClone();
    assertNotSame(cell, clonedCell);
    assertNotSame(card, clonedCell.getCard());
    assertEquals(card.getOwner(), clonedCell.getCard().getOwner());
  }
}

