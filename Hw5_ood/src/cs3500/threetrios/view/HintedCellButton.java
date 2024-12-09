package cs3500.threetrios.view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cs3500.threetrios.model.ICell;

public class HintedCellButton extends CellButton {

  private final JLabel hintLabel;

  /**
   * The Default constructor
   * @param cell The logical cell to be assigned.
   * @param cellCol The column of the cell
   * @param cellRow The row of the cell
   * @param hint The hint to be displayed
   */
  public HintedCellButton(ICell cell, int cellCol, int cellRow, int hint) {
    super(cell, cellCol, cellRow);

    hintLabel = new JLabel(hint + "", SwingConstants.CENTER);
    hintLabel.setFont(new Font("Arial", Font.BOLD, 16));
    hintLabel.setForeground(Color.BLACK);
    hintLabel.setPreferredSize(new Dimension(40, 20));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.SOUTHWEST;
    this.add(hintLabel, gbc);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.add(hintLabel);
  }
}
