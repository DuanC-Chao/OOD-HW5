package cs3500.threetrios.model;

import cs3500.threetrios.view.TripleTriadTextView;
import cs3500.threetrios.view.TripleTriadView;

public class TripleTriadProgram {

  public static void main(String[] args) {

    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    ITripleTriadModel model = new TripleTriadModel();
    TripleTriadView view = new TripleTriadTextView(model);
    model.startGame(boardConfigPath, cardConfigPath, "A", "B", false, new DefaultCombatRule());
    view.render();
  }
}
