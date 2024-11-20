package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * The class to test Grid.
 */
public class GridTest {

  // Model Interface-Testing Section (Public Method Tests)

  @Test
  public void testGetRowNumber() {
    ICell[][] gridData = {
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()},
            {new RegularHole(), new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    Assert.assertEquals(3, grid.getRowNumber());
  }

  @Test
  public void testGetColNumber() {
    ICell[][] gridData = {
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()},
            {new RegularHole(), new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    Assert.assertEquals(3, grid.getColNumber());
  }

  @Test
  public void testGetGrid() {
    ICell[][] gridData = {
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()},
            {new RegularHole(), new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    ICell[][] cloneGrid = grid.getGrid();
    Assert.assertNotSame(gridData, cloneGrid);
    Assert.assertEquals(gridData.length, cloneGrid.length);
  }

  @Test
  public void testGetCard() {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    RegularCardCell cardCell = new RegularCardCell();
    cardCell.setCard(card);
    ICell[][] gridData = {
            {cardCell, new RegularHole(), new RegularCardCell()},
            {new RegularHole(), new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    Assert.assertEquals(card, grid.getCard(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardOutOfBounds() {
    ICell[][] gridData = {
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    grid.getCard(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlaceCardOutOfBounds() throws Exception {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    ICell[][] gridData = {
            {new RegularCardCell(), new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    grid.placeCard(3, 0, card);
  }

  @Test(expected = Exception.class)
  public void testPlaceCardInHole() throws Exception {
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    RegularHole hole = new RegularHole();
    ICell[][] gridData = {
            {hole, new RegularHole(), new RegularCardCell()}
    };
    Grid grid = new Grid(gridData);
    grid.placeCard(0, 0, card);
  }

  // Implementation-Testing Section (Testing Grid Legality Check)
  @Test(expected = IllegalArgumentException.class)
  public void testEvenTotalCellsThrowsException() {
    ICell[][] evenGrid = {
            {new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularHole()}
    };
    new Grid(evenGrid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenCardCellsThrowsException() {
    ICell[][] evenCardCellsGrid = {
            {new RegularCardCell(), new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularCardCell(), new RegularHole()}
    };
    new Grid(evenCardCellsGrid);
  }
}

