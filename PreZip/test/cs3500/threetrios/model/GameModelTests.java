
package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Consolidated test class for game model functionality.
 */
public class GameModelTests {
  /*

  // ---------- BoardConfigReader Tests ----------

  @Test
  public void testValidGridConfig() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-valid.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("3 3\n");
      writer.write("CXC\n");
      writer.write("XCX\n");
      writer.write("CXC\n");
    }

    ICell[][] grid = ConfigReader.loadGridConfig(filePath);
    assertEquals(3, grid[0].length);
    assertEquals(3, grid.length);
    assertTrue(grid[0][0] instanceof RegularCardCell);
    assertTrue(grid[1][0] instanceof RegularHole);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowOrColZero() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-zero.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("0 3\n");
    }
    ConfigReader.loadGridConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnevenGridDimensions() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-even-dimensions.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("2 2\n");
      writer.write("CC\n");
      writer.write("CC\n");
    }
    ConfigReader.loadGridConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnknownCellType() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-unknown-cell.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("3 3\n");
      writer.write("CYC\n");
      writer.write("XCX\n");
      writer.write("CXC\n");
    }
    ConfigReader.loadGridConfig(filePath);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRowLengthMismatch() throws IOException {
    String filePath = "src/docs/JUnitTests/test-config-row-mismatch.txt";
    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write("3 3\n");
      writer.write("CC\n");
      writer.write("XCX\n");
      writer.write("CXC\n");
    }
    ConfigReader.loadGridConfig(filePath);
  }

  // ---------- Grid Tests ----------

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

  @Test(expected = IllegalArgumentException.class)
  public void testEvenTotalCellsThrowsException() {
    ICell[][] evenGrid = {
            {new RegularCardCell(), new RegularHole()},
            {new RegularCardCell(), new RegularHole()}
    };
    new Grid(evenGrid);
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

  // ---------- Player Tests ----------

  @Test
  public void testConstructorAndGetters() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    Assert.assertEquals("Alice", player.getName());
    Assert.assertEquals(EPlayer.PLAYER_ONE, player.getIdentity());
    Assert.assertTrue(player.getHand().isEmpty());
  }

  @Test
  public void testSetHand() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    List<ICard> deck = new ArrayList<>();
    deck.add(new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR,
            "Card 1", EPlayer.PLAYER_TWO));
    player.setHand(deck, 1);
    Assert.assertEquals(1, player.getHand().size());
    Assert.assertEquals(EPlayer.PLAYER_ONE, player.getHand().get(0).getOwner());
  }

  @Test
  public void testPopCardFromHand() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    List<ICard> deck = new ArrayList<>();
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Card 1", EPlayer.PLAYER_ONE);
    deck.add(card);
    player.setHand(deck, 1);
    // The card in player's hand is a clone.
    Assert.assertNotEquals(card, player.popCardFromHand(0));
    Assert.assertTrue(player.getHand().isEmpty());
  }

  @Test
  public void testMakeClone() {
    List<ICard> hand = new ArrayList<>();
    hand.add(new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR,
            "Card 1", EPlayer.PLAYER_ONE));
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice", hand);
    Player clonedPlayer = (Player) player.makeClone();

    Assert.assertNotSame(player, clonedPlayer);
    Assert.assertEquals(player.getName(), clonedPlayer.getName());
    Assert.assertEquals(player.getIdentity(), clonedPlayer.getIdentity());
    Assert.assertEquals(player.getHand(), clonedPlayer.getHand());
  }

  // ---------- RegularHole Tests ----------

  @Test
  public void testGetCard() {
    RegularHole hole = new RegularHole();
    Assert.assertNull(hole.getCard());
  }

  @Test(expected = Exception.class)
  public void testSetCardThrowsException() throws Exception {
    RegularHole hole = new RegularHole();

    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Test Card", EPlayer.PLAYER_ONE);
    hole.setCard(card);
  }

  @Test
  public void testGetType() {
    RegularHole hole = new RegularHole();
    Assert.assertEquals(CellType.HOLE, hole.getType());
  }

  @Test
  public void testMakeCloneHole() {
    RegularHole hole = new RegularHole();
    RegularHole clonedHole = (RegularHole) hole.makeClone();

    Assert.assertNotSame(hole, clonedHole);
    Assert.assertEquals(CellType.HOLE, clonedHole.getType());
    Assert.assertNull(clonedHole.getCard());
  }

  // ---------- MyModel Tests ----------

  @Test
  public void testStartGameWithSimpleCongifurationFiles() {
    ITripleTriadModel model = new TripleTriadModel();
    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    try {
      model.startGame(boardConfigPath, cardConfigPath, "John",
              "Bob", false, new DefaultCombatRule(), null);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void testPlayToGrid() {
    ITripleTriadModel model = new TripleTriadModel();
    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
            false, new DefaultCombatRule(), null);
    model.playToGrid(1, 0, 0, 0);

    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.playToGrid(3, 3, 0, 0);
    });
  }

   */
}


