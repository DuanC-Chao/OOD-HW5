package cs3500.threetrios.provider;

import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.PredefinedBot;
import cs3500.threetrios.model.TripleTriadModel;
import cs3500.threetrios.provider.model.AdaptedReadOnlyModel;
import cs3500.threetrios.provider.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.provider.view.ClassicThreeTriosGUI;
import cs3500.threetrios.provider.view.ThreeTriosGUI;
import cs3500.threetrios.view.TripleTriadGraphicView;

import static cs3500.threetrios.view.ViewUtils.loadResourceFile;

public class TestProvidedProgram {
  public static void main(String[] args) {

    String boardConfigPath = loadResourceFile("/SimpleBoard.txt");
    String cardConfigPath = loadResourceFile("/SevenCardsDeck.txt");

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
      false, new DefaultCombatRule(), PredefinedBot.ADVANCED_BOT.getBot());

    ReadOnlyThreeTriosModel adaptedModel = new AdaptedReadOnlyModel(model);

    TripleTriadGraphicView viewOne = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    ThreeTriosGUI viewTwo = new ClassicThreeTriosGUI(adaptedModel);
    viewTwo.display(true);
  }
}
