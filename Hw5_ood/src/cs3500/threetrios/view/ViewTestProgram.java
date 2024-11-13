package cs3500.threetrios.view;

import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.PredefinedBot;
import cs3500.threetrios.model.TripleTriadModel;

/**
 * The main for graphic view, just for test.
 */
public class ViewTestProgram {

  public static void main(String[] args) throws InterruptedException {

    //String boardConfigPath_Big = "src/docs/SimpleBoard.txt";
    //String cardConfigPath = "src/docs/SevenCardsDeck.txt";

    String boardConfigPath_Big = "src/docs/MediumSizeBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath_Big, cardConfigPath, "A", "B",
      false, new DefaultCombatRule(), PredefinedBot.ADVANCED_BOT.getBot());
    TripleTriadGraphcView view = new TripleTriadGraphcView(model);

    //model.playToGrid(1, 0, 0, 0);

    //model.playToGrid(1, 0, 0, 1);

    refresh(view);
  }

  private static void refresh(TripleTriadGraphcView view) {
    try {
      view.render(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
