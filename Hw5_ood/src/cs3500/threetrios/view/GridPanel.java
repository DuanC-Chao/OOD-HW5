package cs3500.threetrios.view;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;

import cs3500.threetrios.model.ICell;
import cs3500.threetrios.model.IGrid;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;
import cs3500.threetrios.model.Tuple;

import static cs3500.threetrios.view.ViewUtils.toGridLayoutList;

/**
 * The Class for a Grid Panel.
 * A Grid panel holds ICellButton, and forms the game grid.
 */
public class GridPanel extends JPanel implements IGridPanel {

  /**
   * The Logical Grid.
   */
  private IGrid logicalGrid;

  /**
   * The delegate to be dispatched.
   */
  private Consumer<Move> delegateToDispatch;

  /**
   * The model.
   */
  private ReadOnlyTripleTriadModel model;

  /**
   * The default constructor.
   *
   * @param model The model of the game.
   */
  public GridPanel(ReadOnlyTripleTriadModel model) {
    this.model = model;
    this.logicalGrid = model.getGridClone();

    int cols = logicalGrid.getColNumber();
    int rows = logicalGrid.getRowNumber();

    this.setPreferredSize(new Dimension(cols * 102, rows *
      (Size.CARD_HEIGHT.getSize() + 2)));

    // To ensure that Grid will not be automatically stretched when there are too many space
    // But, still will be automatically condensed when available space is not enough
    this.setMaximumSize(new Dimension(cols * 102, rows *
      (Size.CARD_HEIGHT.getSize() + 2)));

    setGridBackGroundColor();

    // Set grid layout
    setLayout(new GridLayout(rows, cols, 1, 1));

    updateGrid();
  }

  /**
   * Update the grid based on Cells in the logical grid.
   */
  private void updateGrid() {
    List<Tuple<ICell, Tuple<Integer, Integer>>> loICell = toGridLayoutList(this.model.getGrid());

    // Remove all components before updating grid panel.
    removeAll();

    for (Tuple<ICell, Tuple<Integer, Integer>> tuple : loICell) {
      ICell cell = tuple.getFirst();
      int col = tuple.getSecond().getFirst();
      int row = tuple.getSecond().getSecond();

      ICellButton cellButton = new CellButton(cell, col, row);
      // Dispatch delegate
      cellButton.passMoveDelegate(delegateToDispatch);
      this.add((Component) cellButton);
    }
  }

  @Override
  public void refresh() {
    updateGrid();
  }

  @Override
  public void changeColor(String colorHex) {
    this.setBackground(Color.decode(colorHex));
  }

  /**
   * Helper method, settle the background color of grid panel.
   * Based on ElementColor Configuration.
   */
  private void setGridBackGroundColor() {
    this.changeColor(ElementColor.GRID_BACKGROUND_COLOR.toString());
  }

  @Override
  public void takeToBeDispatchedDelegate(Consumer<Move> delegate) {
    this.delegateToDispatch = delegate;
  }
}
