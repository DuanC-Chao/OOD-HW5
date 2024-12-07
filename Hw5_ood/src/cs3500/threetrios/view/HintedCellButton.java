package cs3500.threetrios.view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cs3500.threetrios.model.ICell;

public class HintedCellButton extends CellButton {

  /**
   * The Default constructor
   * @param cell The logical cell to be assigned.
   * @param cellCol The column of the cell
   * @param cellRow The row of the cell
   */
  public HintedCellButton(ICell cell, int cellCol, int cellRow) {
    super(cell, cellCol, cellRow);

    JLabel label = new JLabel("", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 16));
    label.setForeground(Color.BLACK);
    label.setPreferredSize(new Dimension(40, 20));
  }
}
