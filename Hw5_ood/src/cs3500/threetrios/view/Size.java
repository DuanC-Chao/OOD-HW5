package cs3500.threetrios.view;

/**
 * The Enum configurations Oftenly used sizes.
 */
public enum Size {

  CARD_HEIGHT(150);

  private final int size;

  Size(int num) {
    this.size = num;
  }

  /**
   * Get the size of the Enum instance.
   * @return The size.
   */
  public int getSize() {
    return this.size;
  }
}
