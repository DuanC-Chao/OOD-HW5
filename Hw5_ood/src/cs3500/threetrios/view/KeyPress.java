package cs3500.threetrios.view;

import cs3500.threetrios.model.EPlayer;

public class KeyPress {
  /**
   * The key that was pressed.
   */
  public final char key;

  /**
   * The owner of the view in which the key was pressed.
   */
  public final EPlayer player;

  public KeyPress(char key, EPlayer player) {
    this.key = key;
    this.player = player;
  }
}
