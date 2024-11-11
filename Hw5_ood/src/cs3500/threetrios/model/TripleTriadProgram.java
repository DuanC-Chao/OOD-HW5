package cs3500.threetrios.model;

import java.io.IOException;

import cs3500.threetrios.view.TripleTriadTextView;
import cs3500.threetrios.view.TripleTriadView;

/**
 * The main class.
 */
public class TripleTriadProgram {

  /**
   * The main method, here to demostrate view.
   * @param args Arguemnts.
   */
  public static void main(String[] args) {

    String boardConfigPath_Simple = "src/docs/SimpleBoard.txt";
    String boardConfigPath_Big = "src/docs/UnreachableHoleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    ITripleTriadModel model = new TripleTriadModel();
    TripleTriadView view = new TripleTriadTextView(model);
    model.startGame(boardConfigPath_Big, cardConfigPath, "A", "B", false, new DefaultCombatRule(), new AdvancedBot());
    try {
      view.render(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
    model.playToGrid(1, 0, 0, 0);
    try {
      view.render(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
