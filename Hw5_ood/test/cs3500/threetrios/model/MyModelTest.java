package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import cs3500.threetrios.model.Exception.NoSuchConfigException;
import cs3500.threetrios.model.Rule.DefaultCombatRule;

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
      "B", false, new DefaultCombatRule());
  }

  /**
   * This method tests the startGame method with simple config files.
   */
  @Test
  public void testStartGameWithSimpleCongifurationFiles() {
    resetModel();
    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";
    String boardConfigPathWithEvenTotalCellNum = "src/docs/NonOddBoard.txt";
    try {
      model.startGame(boardConfigPath, cardConfigPath, "John",
        "Bob", false, new DefaultCombatRule());
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }

    //Test if NoSuchConfigException could be thrown when given non-existing config file path.
    resetModel();
    Assert.assertThrows(NoSuchConfigException.class, () -> {
      model.startGame("nonsense", "nonsense", "John",
        "bob", false, new DefaultCombatRule());
    });

    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(null, "nonsense", "John",
        "bob", false, new DefaultCombatRule());
    });

    //Test if IllegalArgumentException could be thrown when player's name are Illegal.
    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(boardConfigPath, cardConfigPath,
        "A", "A", false, new DefaultCombatRule());
    });

    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(boardConfigPath, cardConfigPath,
        null, "A", false, new DefaultCombatRule());
    });

    //Test if IllegalArgumentException could be thrown when the board config contains
    //col * row is even
    resetModel();
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      model.startGame(boardConfigPathWithEvenTotalCellNum, cardConfigPath,
        "B", "A", false, new DefaultCombatRule());
    });
  }

  /**
   * Test the method playToGrid().
   */
  @Test
  public void testPlayToGrid() {

    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    //First, test if 0-based variant passing is implemented
    resetReadyToGoModel();
    try {
      model.playToGrid(1, 0, 0, 0);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }

  }
}
