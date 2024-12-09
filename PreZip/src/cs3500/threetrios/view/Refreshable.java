package cs3500.threetrios.view;

/**
 * An Interface, provide Panel, Frame or other components the ability of refresh.
 * Refresh with model's status.
 */
public interface Refreshable {

  /**
   * Takes a model, and refresh component with model's status.
   */
  public void refresh();
}
