package cs3500.threetrios.view;

import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.PredefinedBot;
import cs3500.threetrios.model.TripleTriadModel;

import static cs3500.threetrios.view.ViewUtils.loadResourceFile;

/**
 * The main for graphic view, just for test.
 */
public class ViewTestProgram {

  /**
   * The main class.
   *
   * @param args The args.
   */
  public static void main(String[] args) {

    String boardConfigPath = loadResourceFile("/SimpleBoard.txt");
    String cardConfigPath = loadResourceFile("/SevenCardsDeck.txt");

    //String boardConfigPath_Big = "src/../resources/MediumSizeBoard.txt";
    //String cardConfigPath = "src/../resources/SimpleDeck.txt";

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
            false, new DefaultCombatRule(), PredefinedBot.ADVANCED_BOT.getBot());
    TripleTriadGraphicView view = new TripleTriadGraphicView(model, null);

    //model.playToGrid(1, 0, 0, 0);

    //model.playToGrid(1, 0, 0, 1);

    refresh(view);
  }

  private static void refresh(TripleTriadGraphicView view) {
    try {
      view.render(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
