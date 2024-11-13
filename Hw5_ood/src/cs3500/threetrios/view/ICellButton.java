package cs3500.threetrios.view;

/**
 * The Interface for the button that represents a Cell.
 * A Cell button could hold an IOnGridCardButton.
 */
public interface ICellButton extends Refreshable, Discolorable {

  /**
   * Get the column of the cell.
   * @return The column coordinate of the cell.
   */
  int getCellCol();

  /**
   * Get the row of the cell.
   * @return The row coordinate of the cell.
   */
  int getCellRow();

  /**
   * Put the cardButton on the IcellButton.
   * Notice, takes an InHandCardButton, need converting.
   * @param cardButton The cardButton to be putted.
   */
  void assignCardButton(IinHandCardButton cardButton);
}
