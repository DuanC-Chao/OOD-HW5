package cs3500.threetrios.view;

/**
 * All components that could change background color should implement this interface.
 */
public interface Discolorable {

  /**
   * Change the color of CardButton with the given Hex value.
   *
   * @param colorHex The HEX value of the color.
   */
  void changeColor(String colorHex);
}
