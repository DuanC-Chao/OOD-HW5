package cs3500.threetrios;

import cs3500.threetrios.model.ITripleTriadModel;
import cs3500.threetrios.model.TripleTriadModel;
import cs3500.threetrios.view.GameFrameImpl;

public final class ThreeTrios {
  public static void main(String[] args) {
    ITripleTriadModel model = new TripleTriadModel(); 
    GameFrameImpl view = new GameFrameImpl(model);
    view.setVisible(true);
  }
}