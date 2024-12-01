package cs3500.threetrios.provider.controller;

import model.ModelFeatures;
import view.PlayerActions;

/**
 * A controller interface for a player in a game of Three Trios.
 */
public interface ThreeTriosController extends PlayerActions, ModelFeatures {

  /**
   * Opens up the GUI for the player.
   */
  void startGUI();
}
