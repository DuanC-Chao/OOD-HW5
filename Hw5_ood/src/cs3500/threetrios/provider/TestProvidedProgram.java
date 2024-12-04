package cs3500.threetrios.provider;

import java.util.Arrays;
import java.util.List;

import cs3500.threetrios.controller.TripleTriadController;
import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.FallenAceCombatRule;
import cs3500.threetrios.model.IBot;
import cs3500.threetrios.model.ICombatRule;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.PredefinedBot;
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

    ICombatRule ruleToApply = null;
    int currentIndex = 1;

    // First, when no argument given, run the default scenario
    // No Bot, No provided view, base rule
    if (args.length == 0) {
      baseScenario();
    } else {
      int ruleNumber = Integer.parseInt(args[0]);
      currentIndex += ruleNumber;
      String[] rules = Arrays.copyOfRange(args, 1, 1 + ruleNumber);
      ruleToApply = loadRule(rules);
    }

    System.out.println("BreakPoint#0 \n");

    IBot bot = null;

    if (args[currentIndex].equals("1")) {
      bot = PredefinedBot.ADVANCED_BOT.getBot();
      System.out.println("BreakPoint#1 \n");
    }

    currentIndex += 1;

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
      false, ruleToApply, bot);

    ThreeTriosModel adaptedModel = new AdaptedModel(model);

    boolean useProviderView = false;

    if (args[currentIndex].equals("1")) {
      useProviderView = true;
      System.out.println("BreakPoint#2 \n");
    }

    TripleTriadView viewOne = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    TripleTriadView viewTwo = new TripleTriadGraphicView(model, EPlayer.PLAYER_TWO);

    ThreeTriosGUI viewProvider = new ClassicThreeTriosGUI(adaptedModel);
    TripleTriadView reverseAdaptedViewTwo = new ReverseAdaptedView(viewProvider);

    TripleTriadController controllerOne = new TripleTriadController(EPlayer.PLAYER_ONE, model,
      viewOne, viewTwo);
    TripleTriadController controllerTwo = null;

    ThreeTriosController controllerProvider = new ThreeTriosControllerImpl(adaptedModel, viewProvider,
      viewOne);

    if (useProviderView) {
      viewProvider.setFeatureListeners(controllerProvider);
      viewProvider.display(true);
    } else {
      controllerTwo = new TripleTriadController(EPlayer.PLAYER_TWO, model,
        viewTwo, viewOne);
    }
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

    TripleTriadController controllerOne = new TripleTriadController(EPlayer.PLAYER_ONE, model,
      viewOne, viewTwo);
    TripleTriadController constrllerTwo = new TripleTriadController(EPlayer.PLAYER_TWO, model,
      viewTwo, viewOne);
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

    if (loRule.contains("base") && !loRule.contains("fallenAce") && !loRule.contains("reverse")) {
      System.out.println("Base Rule Apply");
      return baseRule;
    } else if (loRule.contains("base") && loRule.contains("fallenAce") &&
      !loRule.contains("reverse")) {
      System.out.println("FallenAce Rule Apply");
      return falledAceRule;
    } else if (loRule.contains("base") && loRule.contains("fallenAce") &&
      loRule.contains("reverse")) {
      System.out.println("ReversedFA Rule Apply");
      return reversedFalledAceRule;
    } else if (loRule.contains("base") && !loRule.contains("fallenAce") &&
      loRule.contains("reverse")) {
      System.out.println("Reversed Base Rule Apply");
      return reversedBaseRule;
    } else {
      throw new IllegalArgumentException("Illegal combination of rules");
    }
  }
}
