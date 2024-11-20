package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * The test class for TripleTriadModel.
 */
public class MyModelTest {

  /**
   * The model to test on.
   */
  ITripleTriadModel model;

  /**
   * Reset the current model.
   */
  protected void resetModel() {
    this.model = new TripleTriadModel();
  }

  /**
   * Reset the model to ready to go state, which game is started.
   */
  protected void resetReadyToGoModel() {
    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";
    this.model = new TripleTriadModel();
    model.startGame(boardConfigPath, cardConfigPath, "A",
        "B", false, new DefaultCombatRule(), null);
  }

  /**
   * This method tests the startGame method with simple config files.
   */
  @Test
  public void testStartGameWithSimpleCongifurationFiles() {
    resetModel();
    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String bigBoardConfigPath = "src/docs/UnreachableHoleBoard.txt";
    String boardConfigPathWithEvenTotalCellNum = "src/docs/NonOddBoard.txt";

    String cardConfigPath = "src/docs/SimpleDeck.txt";
    String smallCardConfigPath = "src/docs/SmallDeck.txt";

    try {
      model.startGame(boardConfigPath, cardConfigPath, "John",
          "Bob", false, new DefaultCombatRule(), null);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }

    //Test if NoSuchConfigException could be thrown when given non-existing config file path.
    resetModel();
    Assert.assertThrows(Exception.class, () -> {
      model.startGame("nonsense", "nonsense", "John",
          "bob", false, new DefaultCombatRule(), null);
    });

    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(null, "nonsense", "John",
          "bob", false, new DefaultCombatRule(), null);
    });

    //Test if IllegalArgumentException could be thrown when player's name are Illegal.
    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(boardConfigPath, cardConfigPath,
          "A", "A", false, new DefaultCombatRule(), null);
    });

    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(boardConfigPath, cardConfigPath,
          null, "A", false, new DefaultCombatRule(), null);
    });

    //Test if IllegalArgumentException could be thrown when the board config contains
    //col * row is even
    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(boardConfigPathWithEvenTotalCellNum, cardConfigPath,
          "B", "A", false, new DefaultCombatRule(), null);
    });

    //Test of IllegalStatement is thrown when given a too big grid and too small deck
    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(bigBoardConfigPath, smallCardConfigPath, "A",
          "B", false, new DefaultCombatRule(), null);
    });
  }

  /**
   * Test the method playToGrid().
   */
  @Test
  public void testPlayToGrid() {

    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";
    String holeBoardConfigPath = "src/docs/ReachableHoleBoard.txt";

    //First, test if 0-based variant passing is implemented
    resetReadyToGoModel();
    try {
      model.playToGrid(1, 0, 0, 0);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }

    try {
      model.playToGrid(2, 2, 1, 0);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }

    //Try invalid card index, player should have 5 cards
    resetReadyToGoModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.playToGrid(1, 5, 0, 0);
    });

    //This time, test an invalid player number
    resetReadyToGoModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.playToGrid(3, 3, 0, 0);
    });

    //Test if the model could handle switch of player's turn
    resetReadyToGoModel();
    model.playToGrid(1, 0, 0, 0);
    Assert.assertThrows(Exception.class, () -> {
      model.playToGrid(1, 0, 1, 0);
    });

    //Test if model could handle Cell already full situation
    resetReadyToGoModel();
    model.playToGrid(1, 0, 0, 0);
    Assert.assertThrows(Exception.class, () -> {
      model.playToGrid(2, 0, 0, 0);
    });

    //Test if Cell is a Hole situation
    resetModel();
    model.startGame(holeBoardConfigPath, cardConfigPath, "A",
        "B", false, new DefaultCombatRule(), null);
    Assert.assertThrows(Exception.class, () -> {
      model.playToGrid(1, 0, 1, 1);
    });
  }

  /**
   * Test if game could be won.
   */
  @Test
  public void testWinning() {
    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SmallDeck.txt";
    resetModel();
    model.startGame(boardConfigPath, cardConfigPath, "A",
        "B", false, new DefaultCombatRule(), null);

    Assert.assertFalse(model.isGameWon());

    model.playToGrid(1, 0, 0, 0);
    model.playToGrid(2, 0, 0, 1);
    model.playToGrid(1, 0, 0, 2);
    model.playToGrid(2, 0, 1, 0);
    model.playToGrid(1, 0, 1, 1);
    model.playToGrid(2, 0, 1, 2);
    model.playToGrid(1, 0, 2, 0);
    model.playToGrid(2, 0, 2, 1);
    model.playToGrid(1, 0, 2, 2);

    Assert.assertTrue(model.isGameWon());
    Assert.assertEquals("B", model.getWinningPlayerName());
  }
}
