package cs3500.threetrios.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.ICell;
import cs3500.threetrios.model.RegularCardCell;
import cs3500.threetrios.model.RegularHole;

import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.UIDefaults;

/**
 * The class represents a CellButton.
 * A CellButton represent a logical Cell.
 */
public class CellButton extends JButton implements ICellButton {

  /**
   * The Logical Cell.
   */
  private ICell logicalCell;

  /**
   * The col of the cell on the grid, 0-Based.
   */
  private int cellCol;

  /**
   * The row of the cell on the grid, 0-Based.
   */
  private int cellRow;

  /**
   * The Delegate of "Move card from buffer to this Cell".
   * Should be provided by Controller.
   */
  private Consumer<Move> delegate;

  /**
   * The delegate to refresh the whole view, called after a card is placed.
   */
  private Runnable refreshDelegate;

  /**
   * The On Cell CardButton it holds.
   * Initialized as null.
   */
  private IOnCellCardButton cardButton;

  /**
   * The Default constructor.
   *
   * @param cell The logical cell to be assigned.
   * @param cellCol The column of the cell
   * @param cellRow The row of the cell
   */
  public CellButton(ICell cell, int cellCol, int cellRow) {
    this.logicalCell = cell;
    this.cellCol = cellCol;
    this.cellRow = cellRow;
    this.cardButton = null;

    this.setPreferredSize(new Dimension(102, Size.CARD_HEIGHT.getSize() + 2));
    this.setMaximumSize(new Dimension(102, Size.CARD_HEIGHT.getSize() + 2));

    setLayout(new GridBagLayout());

    // Wrapped Component will always be centered.
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;

    selfDetermineColor();
    configStyle();

    // Load card from logical cell.
    asyncCardFromCell();

    setListener();
  }

  /**
   * Set the listener of itself being pressed.
   * And call delegate.
   */
  private void setListener() {
    this.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Just for test, remove after implement Controller.
        System.out.println("Cell Button with Col: " + CellButton.this.cellCol
                + ", Row: " + CellButton.this.cellRow + " Pressed\n");

        // Controller will be dealing with the "Put from buffer to this coord".
        if (CellButton.this.delegate != null) {
          delegate.accept(new Move(CellButton.this.cellCol, CellButton.this.cellRow));
        }
      }
    });
  }

  @Override
  public int getCellCol() {
    return this.cellCol;
  }

  @Override
  public int getCellRow() {
    return this.cellRow;
  }

  @Override
  public void assignCardButton(IinHandCardButton cardButton) {
    this.cardButton = new OnCellCardButton(cardButton);
  }

  @Override
  public void passMoveDelegate(Consumer<Move> delegate, Runnable refreshDelegate) {
    this.delegate = delegate;
  }

  @Override
  public void refresh() {
    this.asyncCardFromCell();
  }

  /**
   * Syncronize logical card and create new OnCellCardButton from card that logical cell holds.
   */
  private void asyncCardFromCell() {
    ICard card = this.logicalCell.getCard();
    if (card != null) {
      this.cardButton = new OnCellCardButton(card);
      this.add((Component) cardButton);
    }
  }

  @Override
  public void changeColor(String colorHex) {
    this.setBackground(Color.decode(colorHex));
  }

  /**
   * Change the color of CellButton, based on its status.
   * There are two possibilities:
   * 1, Hole
   * 2, RegularCell
   */
  private void selfDetermineColor() {
    if (this.logicalCell instanceof RegularHole) {
      this.changeColor(ElementColor.HOLE_COLOR.toString());
    } else if (this.logicalCell instanceof RegularCardCell) {
      this.changeColor(ElementColor.EMPTY_CELL_COLOR.toString());
    } else {
      throw new IllegalArgumentException("Unknown cell type: " + this.logicalCell.getClass());
    }
  }

  /**
   * Helper method, configure the style of CellButton as needed.
   */
  private void configStyle() {
    // Disable clicking highlighting and hover highlighting
    this.putClientProperty("Nimbus.Overrides", new UIDefaults() {
      {
        put("Button[Enabled].backgroundPainter", null);
        put("Button[MouseOver].backgroundPainter", null);
        put("Button[Pressed].backgroundPainter", null);
        put("Button[Focused].backgroundPainter", null);
      }
    });

    this.setContentAreaFilled(false);
    this.setOpaque(true);
    this.setBorderPainted(false);
  }
}
