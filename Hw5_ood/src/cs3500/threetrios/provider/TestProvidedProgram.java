package cs3500.threetrios.provider;

import java.util.Arrays;
import java.util.List;

import cs3500.threetrios.controller.TripleTriadController;
import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.FallenAceCombatRule;
import cs3500.threetrios.model.ICombatRule;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.ReverseCombatRule;
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

    ICombatRule ruleToApply = null;
    int currentIndex = 0;

    // First, when no argument given, run the default scenario
    // No Bot, No provided view, base rule
    if (args.length == 0) {
      baseScenario();
    } else {
      int ruleNumber = Integer.parseInt(args[0]);
      String[] rules = Arrays.copyOfRange(args, 1, 1 + ruleNumber);
      ruleToApply = loadRule(rules);
    }



    TripleTriadView viewOne = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    ThreeTriosGUI viewTwo = new ClassicThreeTriosGUI(adaptedModel);
    TripleTriadView reverseAdaptedViewTwo = new ReverseAdaptedView(viewTwo);

    TripleTriadController controllerOne = new TripleTriadController(EPlayer.PLAYER_ONE, model,
      viewOne, reverseAdaptedViewTwo);
    ThreeTriosController controllerTwo = new ThreeTriosControllerImpl(adaptedModel, viewTwo, viewOne);

    viewTwo.setFeatureListeners(controllerTwo);
    viewTwo.display(true);
  }

  private static void baseScenario() {
    String boardConfigPath = loadResourceFile("/SimpleBoard.txt");
    String cardConfigPath = loadResourceFile("/SevenCardsDeck.txt");

    ITripleTriadModel model = new TripleTriadModel();

    ICombatRule baseRule = new DefaultCombatRule();
    ICombatRule falledAceRule = new FallenAceCombatRule(baseRule);
    ICombatRule reverseRule = new ReverseCombatRule(falledAceRule);

    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
      false, reverseRule, null);

    TripleTriadView viewOne = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    TripleTriadView viewTwo = new TripleTriadGraphicView(model, EPlayer.PLAYER_TWO);

    TripleTriadController controllerOne = new TripleTriadController(EPlayer.PLAYER_ONE, model, viewOne, viewTwo);
    TripleTriadController constrllerTwo = new TripleTriadController(EPlayer.PLAYER_TWO, model, viewTwo, viewOne);
  }

  private static ICombatRule loadRule(String[] rules) {
    for (String rule : rules) {
      if (!(rule.equals("base") || rule.equals("falledAce") || rule.equals("reverse"))) {
        throw new IllegalArgumentException("Invalid rule: " + rule);
      }
    }

    List<String> loRule = Arrays.asList(rules);

    ICombatRule baseRule = new DefaultCombatRule();
    ICombatRule falledAceRule = new FallenAceCombatRule(baseRule);

    ICombatRule reversedBaseRule = new ReverseCombatRule(baseRule);
    ICombatRule reversedFalledAceRule = new FallenAceCombatRule(falledAceRule);

    if (loRule.contains("base") && !loRule.contains("falledAce") && !loRule.contains("reverse")) {
      return baseRule;
    } else if (loRule.contains("base") && loRule.contains("falledAce") && !loRule.contains("reverse")) {
      return falledAceRule;
    } else if (loRule.contains("base") && loRule.contains("falledAce") && loRule.contains("reverse")) {
      return reversedFalledAceRule;
    } else if (loRule.contains("base") && !loRule.contains("falledAce") && loRule.contains("reverse")) {
      return reversedBaseRule;
    } else {
      throw new IllegalArgumentException("Illegal combination of rules");
    }


  }
}
