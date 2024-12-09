package cs3500.threetrios.controller;

import cs3500.threetrios.view.KeyPress;
import cs3500.threetrios.view.Move;
import cs3500.threetrios.view.Pick;

/**
 * The interface for the TripleTriadController.
 */
public interface ITripleTriadController {

  /**
   * Handle the Pick action passed from the view.
   * @param pick The pick to be passed to this Handler.
   */
  void pickHandler(Pick pick);

  /**
   * Handle the move action passed from the view.
   * @param move The move to be passed to this handler.
   */
  void moveHandler(Move move);

  /**
   * Handles when keys are pressed. Only used to toggle hints with 'h'.
   * @param press The key pressed.
   */
  void keyHandler(KeyPress press);
}
