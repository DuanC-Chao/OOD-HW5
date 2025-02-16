package cs3500.threetrios.view;

/**
 * An Enum, to configurate color of each player.
 */
public enum ElementColor {

  PLAYER_ONE_COLOR("#ff9994"), // RED
  PLAYER_TWO_COLOR("#41bfda"), // BLUE
  HOLE_COLOR("#2C5269"),
  EMPTY_CELL_COLOR("#FFFCF6"),
  GRID_BACKGROUND_COLOR("#2C5269"),
  //#2C5269
  HAND_PANEL_BACKGROUBND_COLOR("#000000");

  private final String colorHex;

  ElementColor(String colorHex) {
    this.colorHex = colorHex;
  }

  /**
   * Get the color hex.
   *
   * @return The color hex, as a String.
   */
  public String toString() {
    return this.colorHex;
  }
}
