package cs3500.threetrios.view;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;
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
   * The delegate to be dispatched.
   */
  private Consumer<Move> delegateToDispatch;

  /**
   * Whether hints are enabled in this view.
   */
  private boolean hintsEnabled;

  /**
   * The Method to refresh the view.
   */
  private Runnable viewRefresh;

  /**
   * The model.
   */
  private final ReadOnlyTripleTriadModel model;

  private final EPlayer player;

  private Supplier<Integer> getSelectedCard;

  /**
   * The default constructor.
   *
   * @param model The model of the game.
   * @param player The player of the view, used to show hints for that specific player
   */
  public GridPanel(ReadOnlyTripleTriadModel model, EPlayer player) {
    this.model = model;
    IGrid logicalGrid = model.getGridClone();

    int cols = logicalGrid.getColNumber();
    int rows = logicalGrid.getRowNumber();

    this.player = player;

    this.setPreferredSize(new Dimension(cols * 102, rows
            * (Size.CARD_HEIGHT.getSize() + 2)));

    // To ensure that Grid will not be automatically stretched when there are too many space
    // But, still will be automatically condensed when available space is not enough
    this.setMaximumSize(new Dimension(cols * 102, rows
            * (Size.CARD_HEIGHT.getSize() + 2)));

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

      ICellButton cellButton;
      ICard card = model.getGrid()[row][col].getCard();
      Integer selectedCardIndex = null;
      if (getSelectedCard != null) {
        selectedCardIndex = getSelectedCard.get();
      }
      if (hintsEnabled && card == null && selectedCardIndex != null) {
        ICard selectedCard = model.getPlayerHand(this.player == EPlayer.PLAYER_ONE ? 1 : 2)
          .get(selectedCardIndex);
        int playAtCellResult = model.calculateFlips(player, selectedCard, col, row);
        cellButton = new HintedCellButton(cell, col, row, playAtCellResult - 1);
      }
      else {
        cellButton = new CellButton(cell, col, row);
      }
      // Dispatch delegate
      cellButton.passMoveDelegate(delegateToDispatch, this.viewRefresh);
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
  public void takeToBeDispatchedDelegate(Consumer<Move> delegate, Runnable viewRefreshDelegate) {
    this.delegateToDispatch = delegate;
    this.viewRefresh = viewRefreshDelegate;
    updateGrid();
  }

  @Override
  public void setHintsEnabled(boolean hintsEnabled) {
    this.hintsEnabled = hintsEnabled;
  }

  @Override
  public void setSelectedCardSupplier(Supplier<Integer> getSelectedCard) {
    this.getSelectedCard = getSelectedCard;
  }
}
