package cs3500.threetrios.provider.controller;

import cs3500.threetrios.provider.model.ModelFeatures;
import cs3500.threetrios.provider.view.PlayerActions;

/**
 * A controller interface for a player in a game of Three Trios.
 */
public interface ThreeTriosController extends PlayerActions, ModelFeatures {

  /**
   * Opens up the GUI for the player.
   */
  void startGUI();
}
