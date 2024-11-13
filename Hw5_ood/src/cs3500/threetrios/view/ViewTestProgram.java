package cs3500.threetrios.view;

import cs3500.threetrios.model.AdvancedBot;
import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.TripleTriadModel;

public class ViewTestProgram {

  public static void main(String[] args) {

    String boardConfigPath_Big = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SevenCardsDeck.txt";

    //String boardConfigPath_Big = "src/docs/UnreachableHoleBoard.txt";
    //String cardConfigPath = "src/docs/SimpleDeck.txt";

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath_Big, cardConfigPath, "A", "B", false, new DefaultCombatRule(), new AdvancedBot());
    TripleTriadGraphcView view = new TripleTriadGraphcView(model);

    model.playToGrid(1, 0, 0, 0);

    try {
      view.render(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
