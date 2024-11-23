package cs3500.threetrios.controller;

import java.io.IOException;

import cs3500.threetrios.model.DefaultCombatRule;
import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.PredefinedBot;
import cs3500.threetrios.model.TripleTriadModel;
import cs3500.threetrios.view.TripleTriadGraphicView;
import cs3500.threetrios.view.TripleTriadView;

import static cs3500.threetrios.view.ViewUtils.loadResourceFile;

public class TripleTriadPaogram {

  public static void main(String[] args) throws IOException {

    String boardConfigPath = "src/docs/SimpleBoard.txt";
    String cardConfigPath = "src/docs/SimpleDeck.txt";

    ITripleTriadModel model = new TripleTriadModel();
    model.startGame(boardConfigPath, cardConfigPath, "A", "B", false, new DefaultCombatRule(), null);

    TripleTriadView view_P1 = new TripleTriadGraphicView(model, EPlayer.PLAYER_ONE);
    TripleTriadView view_P2 = new TripleTriadGraphicView(model, EPlayer.PLAYER_TWO);
    ITripleTriadController controller_P1 = new TripleTriadController(EPlayer.PLAYER_ONE, model, view_P1, view_P2);
    ITripleTriadController controller_P2 = new TripleTriadController(EPlayer.PLAYER_TWO, model, view_P2, view_P1);
  }
}
