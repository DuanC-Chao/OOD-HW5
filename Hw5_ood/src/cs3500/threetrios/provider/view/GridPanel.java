package cs3500.threetrios.provider.view;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import cs3500.threetrios.provider.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.provider.model.PlayerColor;
import cs3500.threetrios.provider.model.GameCard;

/**
 * Graphical representation of the grid in a game of ThreeTrios.
 */
class GridPanel extends JPanel {
  private final ReadOnlyThreeTriosModel model;
  private PlayerActions featuresListeners;
  private int selectedCardIdx;

  /**
   * Set the feature listeners for this grid.
   * @param features the feature listeners
   */
  public void setFeatureListeners(PlayerActions features) {
    this.featuresListeners = features;
  }

  /**
   * Produces a GridPanel with the given model.
   * @param model the model that will be represented by the GridPanel
   */
  public GridPanel(ReadOnlyThreeTriosModel model) {
    this.selectedCardIdx = -1;
    this.model = model;
    setLayout(new GridLayout(model.getNumGridRows(), model.getNumGridCols()));
    setBackground(Color.LIGHT_GRAY);
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);

    // Initialize the grid
    updateGrid();
  }

  /**
   * Sets the player's currently selected card index.
   * @param newCardIdx the new card index
   */
  public void setSelectedCardIdx(int newCardIdx) {
    selectedCardIdx = newCardIdx;
  }

  /**
   * Refreshes the grid.
   */
  public void updateGrid() {
    selectedCardIdx = -1;
    removeAll(); // Clear any existing components to refresh the grid

    int rows = model.getNumGridRows();
    int cols = model.getNumGridCols();
    LineBorder cellBorder = new LineBorder(Color.BLACK, 1); // Black border around each cell

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        GameCard card = model.getCardAt(row, col);

        JPanel cell;
        if (model.isHole(row, col)) {
          // Empty cell for holes
          cell = new JPanel();
          cell.setBackground(Color.LIGHT_GRAY);
        } else {
          if (card != null) {
            // Create a CardPanel for the card, matching styling with the hand
            Color ownerColor = model.cardOwner(card)
                    == PlayerColor.RED ? new Color(255, 170, 173) : new Color(72, 172, 255);
            cell = new CardPanel(card, ownerColor); // Use CardPanel for occupied cells
          } else {
            // Empty occupiable cell with grid background color
            cell = new JPanel();
            cell.setBackground(new Color(213, 199, 0));
          }
        }

        // Apply border to each cell
        cell.setBorder(cellBorder);
        add(cell); // Add the cell to the grid
      }
    }

    revalidate();
    repaint();
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      try {
        int row = getRow(e.getPoint());
        int col = getCol(e.getPoint());
        featuresListeners.currentPlayerMakeMove(selectedCardIdx, row, col);
      } catch (IllegalArgumentException | IllegalStateException ie) {
        // can't make move - pop-ups handled in the controller
      }
    }

    private int getRow(Point point) {
      int cellHeight = getHeight() / model.getNumGridRows();
      double row = point.getY() / cellHeight;
      return (int) row;
    }

    private int getCol(Point point) {
      int cellWidth = getWidth() / model.getNumGridCols();
      double col = point.getX() / cellWidth;
      return (int) col;
    }
  }
}