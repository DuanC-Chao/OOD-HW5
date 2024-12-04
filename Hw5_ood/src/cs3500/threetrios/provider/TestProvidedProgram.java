package cs3500.threetrios.provider;

import cs3500.threetrios.controller.TripleTriadController;
import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.TripleTriadModel;
import cs3500.threetrios.provider.controller.ThreeTriosController;
import cs3500.threetrios.provider.controller.ThreeTriosControllerImpl;
import cs3500.threetrios.provider.model.AdaptedModel;
import cs3500.threetrios.provider.model.ThreeTriosModel;
import cs3500.threetrios.provider.view.ClassicThreeTriosGUI;
import cs3500.threetrios.provider.view.ReverseAdaptedView;
import cs3500.threetrios.provider.view.ThreeTriosGUI;
import cs3500.threetrios.view.TripleTriadGraphicView;
import cs3500.threetrios.view.TripleTriadView;

import static cs3500.threetrios.view.ViewUtils.loadResourceFile;

/**
 * A test program that demonstrates the integration of the Triple Triad game
 * with the provider's ThreeTrios adaptations.
 */
public class TestProvidedProgram {

  /**
   * The main entry point for the test program. This method sets up the game
   * configuration, initializes models, views, and controllers, and starts
   * the graphical user interface (GUI).
   *
   * @param args command-line arguments (not used in this program)
   */
  public static void main(String[] args) {

    String boardConfigPath = loadResourceFile("/SimpleBoard.txt");
    String cardConfigPath = loadResourceFile("/SevenCardsDeck.txt");

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
            false, new DefaultCombatRule(), null);

    ThreeTriosModel adaptedModel = new AdaptedModel(model);

    TripleTriadView viewOne = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    ThreeTriosGUI viewTwo = new ClassicThreeTriosGUI(adaptedModel);
    TripleTriadView reverseAdaptedViewTwo = new ReverseAdaptedView(viewTwo);

    TripleTriadController controllerOne = new TripleTriadController(EPlayer.PLAYER_ONE, model,
            viewOne, reverseAdaptedViewTwo);
    ThreeTriosController controllerTwo = new ThreeTriosControllerImpl(adaptedModel, viewTwo,
            viewOne);

    viewTwo.setFeatureListeners(controllerTwo);
    viewTwo.display(true);
  }
}
