package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.threetrios.view.TripleTriadTextView;
import cs3500.threetrios.view.TripleTriadView;

/**
 * Testing with a example model, along with sinple view.
 */
public class MyGameExamplesTest {

  /**
   * The model to test on.
   */
  ITripleTriadModel model;

  String boardConfigPath_Big = "src/docs/UnreachableHoleBoard.txt";
  String cardConfigPath = "src/docs/SimpleDeck.txt";

  /**
   * Reset the current model.
   */
  protected void resetModel() {
    this.model = new TripleTriadModel();
  }

  /**
   * Tests the view with a determined grid.
   */
  @Test
  public void testIfViewCouldDisplayStatusRight() {
    resetModel();
    TripleTriadView view = new TripleTriadTextView(model);
    model.startGame(boardConfigPath_Big, cardConfigPath, "A",
        "B", false, new DefaultCombatRule());
    Appendable output = new StringBuilder();
    try {
      view.render(output);
    } catch (IOException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    }
    String expected = " _   _   _   X   _  \n" +
        " _   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   X   X   _   _  \n" +
        " _   X   _   _   _  \n" +
        "\n" +
        "Player One Cards: \n" +
        "Bob 1 2 3 4\n" +
        "Dragon 2 3 4 5\n" +
        "DogMan 6 4 3 1\n" +
        "CaptainNutButter 10 5 7 8\n" +
        "RottenBanana 3 4 6 7\n" +
        "CowBoy 1 5 5 1\n" +
        "GrassJump 7 6 5 4\n" +
        "Tiffany 10 8 7 7\n" +
        "JustACard 9 10 8 10\n" +
        "CoffeeMan 1 1 10 1\n" +
        "CoolAid 3 3 3 3\n" +
        "Hug 7 1 1 7\n" +
        "ManDog 1 3 4 6\n" +
        "boB 4 3 2 1\n" +
        "MeACard 6 7 4 5\n" +
        "Ant 5 4 7 6\n" +
        "ManCoffee 1 10 1 1\n" +
        "Herb 6 6 6 6\n" +
        "\n" +
        "Player Two Cards: \n" +
        "Bob 1 2 3 4\n" +
        "Dragon 2 3 4 5\n" +
        "DogMan 6 4 3 1\n" +
        "CaptainNutButter 10 5 7 8\n" +
        "RottenBanana 3 4 6 7\n" +
        "CowBoy 1 5 5 1\n" +
        "GrassJump 7 6 5 4\n" +
        "Tiffany 10 8 7 7\n" +
        "JustACard 9 10 8 10\n" +
        "CoffeeMan 1 1 10 1\n" +
        "CoolAid 3 3 3 3\n" +
        "Hug 7 1 1 7\n" +
        "ManDog 1 3 4 6\n" +
        "boB 4 3 2 1\n" +
        "MeACard 6 7 4 5\n" +
        "Ant 5 4 7 6\n" +
        "ManCoffee 1 10 1 1\n" +
        "Herb 6 6 6 6" + "\n";

    Assert.assertEquals(expected, output.toString());
  }

  /**
   * Check if could place card on grid, and flip opponent grid.
   */
  @Test
  public void testIfCouldPlaceCardAndFlip() {
    String expected =
        " R   _   _   X   _  \n" +
        " R   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   X   X   _   _  \n" +
        " _   X   _   _   _  \n" +
        "\n" +
        "Player One Cards: \n" +
        "Dragon 2 3 4 5\n" +
        "DogMan 6 4 3 1\n" +
        "CaptainNutButter 10 5 7 8\n" +
        "RottenBanana 3 4 6 7\n" +
        "CowBoy 1 5 5 1\n" +
        "GrassJump 7 6 5 4\n" +
        "Tiffany 10 8 7 7\n" +
        "JustACard 9 10 8 10\n" +
        "CoffeeMan 1 1 10 1\n" +
        "CoolAid 3 3 3 3\n" +
        "Hug 7 1 1 7\n" +
        "ManDog 1 3 4 6\n" +
        "boB 4 3 2 1\n" +
        "MeACard 6 7 4 5\n" +
        "Ant 5 4 7 6\n" +
        "ManCoffee 1 10 1 1\n" +
        "Herb 6 6 6 6\n" +
        "\n" +
        "Player Two Cards: \n" +
        "Dragon 2 3 4 5\n" +
        "DogMan 6 4 3 1\n" +
        "CaptainNutButter 10 5 7 8\n" +
        "RottenBanana 3 4 6 7\n" +
        "CowBoy 1 5 5 1\n" +
        "GrassJump 7 6 5 4\n" +
        "Tiffany 10 8 7 7\n" +
        "JustACard 9 10 8 10\n" +
        "CoffeeMan 1 1 10 1\n" +
        "CoolAid 3 3 3 3\n" +
        "Hug 7 1 1 7\n" +
        "ManDog 1 3 4 6\n" +
        "boB 4 3 2 1\n" +
        "MeACard 6 7 4 5\n" +
        "Ant 5 4 7 6\n" +
        "ManCoffee 1 10 1 1\n" +
        "Herb 6 6 6 6" + "\n";

    resetModel();
    TripleTriadView view = new TripleTriadTextView(model);
    model.startGame(boardConfigPath_Big, cardConfigPath, "A",
        "B", false, new DefaultCombatRule());
    model.playToGrid(1, 0, 0, 0);
    model.playToGrid(2, 0, 0, 1);
    Appendable output = new StringBuilder();
    try {
      view.render(output);
    } catch (IOException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    }
    Assert.assertEquals(expected, output.toString());
  }

  /**
   * Test if the Combo feature works properly.
   */
  @Test
  public void testCombo() {
    String expected =
        " B   _   _   X   _  \n" +
        " B   _   X   _   _  \n" +
        " B   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   _   X   _   _  \n" +
        " _   X   X   _   _  \n" +
        " _   X   _   _   _  \n" +
        "\n" +
        "Player One Cards: \n" +
        "Dragon 2 3 4 5\n" +
        "DogMan 6 4 3 1\n" +
        "RottenBanana 3 4 6 7\n" +
        "CowBoy 1 5 5 1\n" +
        "GrassJump 7 6 5 4\n" +
        "Tiffany 10 8 7 7\n" +
        "JustACard 9 10 8 10\n" +
        "CoffeeMan 1 1 10 1\n" +
        "CoolAid 3 3 3 3\n" +
        "Hug 7 1 1 7\n" +
        "ManDog 1 3 4 6\n" +
        "boB 4 3 2 1\n" +
        "MeACard 6 7 4 5\n" +
        "Ant 5 4 7 6\n" +
        "ManCoffee 1 10 1 1\n" +
        "Herb 6 6 6 6\n" +
        "\n" +
        "Player Two Cards: \n" +
        "Dragon 2 3 4 5\n" +
        "DogMan 6 4 3 1\n" +
        "CaptainNutButter 10 5 7 8\n" +
        "RottenBanana 3 4 6 7\n" +
        "CowBoy 1 5 5 1\n" +
        "GrassJump 7 6 5 4\n" +
        "Tiffany 10 8 7 7\n" +
        "JustACard 9 10 8 10\n" +
        "CoffeeMan 1 1 10 1\n" +
        "CoolAid 3 3 3 3\n" +
        "Hug 7 1 1 7\n" +
        "ManDog 1 3 4 6\n" +
        "boB 4 3 2 1\n" +
        "MeACard 6 7 4 5\n" +
        "Ant 5 4 7 6\n" +
        "ManCoffee 1 10 1 1\n" +
        "Herb 6 6 6 6" + "\n";

    resetModel();
    TripleTriadView view = new TripleTriadTextView(model);
    model.startGame(boardConfigPath_Big, cardConfigPath, "A",
        "B", false, new DefaultCombatRule());
    model.playToGrid(1, 0, 0, 0);
    model.playToGrid(2, 0, 0, 1);
    model.playToGrid(1, 2, 0, 2);
    Appendable output = new StringBuilder();
    try {
      view.render(output);
    } catch (IOException e) {
      Assert.fail(e.getMessage());
      e.printStackTrace();
    }
    Assert.assertEquals(expected, output.toString());
  }
}
