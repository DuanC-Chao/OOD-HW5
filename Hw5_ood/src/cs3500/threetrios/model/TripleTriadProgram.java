package cs3500.threetrios.model;

import java.io.IOException;

import cs3500.threetrios.controller.TripleTriadController;
import cs3500.threetrios.view.TripleTriadGraphicView;
import cs3500.threetrios.view.TripleTriadView;

import static cs3500.threetrios.view.ViewUtils.loadResourceFile;

/**
 * The main class.
 */
public class TripleTriadProgram {

  /**
   * The main method, here to demostrate view.
   *
   * @param args Arguemnts.
   */
  public static void main(String[] args) {

    String boardConfigPath = loadResourceFile("/SimpleBoard.txt");
    String cardConfigPath = loadResourceFile("/SevenCardsDeck.txt");

    ITripleTriadModel model = new TripleTriadModel();

    ICombatRule baseRule = new DefaultCombatRule();
    ICombatRule falledAceRule = new FallenAceCombatRule(baseRule);
    ICombatRule reverseRule = new ReverseCombatRule(falledAceRule);

    model.startGame(boardConfigPath, cardConfigPath, "A", "B",
      false, reverseRule, null, null);

    TripleTriadView viewOne = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    TripleTriadView viewTwo = new TripleTriadGraphicView(model, EPlayer.PLAYER_TWO);

    TripleTriadController controllerOne = new TripleTriadController(EPlayer.PLAYER_ONE, model, viewOne, viewTwo);
    TripleTriadController constrllerTwo = new TripleTriadController(EPlayer.PLAYER_TWO, model, viewTwo, viewOne);
  }
}
