package cs3500.threetrios.provider.view;

import java.awt.*;

import javax.swing.*;

import cs3500.threetrios.provider.model.PlayerColor;
import cs3500.threetrios.provider.model.ReadOnlyThreeTriosModel;

/**
 * Represents a Graphical user interface for a classic game of ThreeTrios.
 */
public class ClassicThreeTriosGUI extends JFrame implements ThreeTriosGUI {
  private final ReadOnlyThreeTriosModel model;
  private final HandPanel player1Hand;
  private final HandPanel player2Hand;
  private final GridPanel gameGrid;

  @Override
  public void setFeatureListeners(PlayerActions features) {
    this.gameGrid.setFeatureListeners(features);
    this.player1Hand.setFeatureListener(features);
    this.player2Hand.setFeatureListener(features);
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  /**
   * Constructs a GUI with the given ThreeTrios model.
   * @param model the model represented by the GUI
   */
  public ClassicThreeTriosGUI(ReadOnlyThreeTriosModel model) {
    this.model = model;

    // Create the components
    player1Hand = new HandPanel(new Color(255, 170, 173, 255), this);
    player2Hand = new HandPanel(new Color(72, 172, 255, 255), this);
    gameGrid = new GridPanel(model);

    setSize(1000, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setupLayout();

    // Initialize hands and grid based on model
    refresh();
  }

  private void setupLayout() {
    setLayout(new BorderLayout());

    // Create white spacer panels
    JPanel leftSpacer = new JPanel();
    leftSpacer.setBackground(Color.WHITE);
    leftSpacer.setPreferredSize(new Dimension(15, 0)); // Width of 15 pixels for the left spacer

    JPanel rightSpacer = new JPanel();
    rightSpacer.setBackground(Color.WHITE);
    rightSpacer.setPreferredSize(new Dimension(15, 0)); // Width of 15 pixels for the right spacer

    // Add the components to the frame with spacers between hand panels and grid
    JPanel westPanel = new JPanel(new BorderLayout());
    westPanel.add(player1Hand, BorderLayout.WEST);
    westPanel.add(leftSpacer, BorderLayout.EAST);

    JPanel eastPanel = new JPanel(new BorderLayout());
    eastPanel.add(rightSpacer, BorderLayout.WEST);
    eastPanel.add(player2Hand, BorderLayout.EAST);

    add(westPanel, BorderLayout.WEST);
    add(gameGrid, BorderLayout.CENTER);
    add(eastPanel, BorderLayout.EAST);
  }

  /**
   * Refreshes the UI.
   */
  @Override
  public void refresh() {
    // Update player hands
    player1Hand.renderHand(model.getPlayerHand(PlayerColor.RED));
    player2Hand.renderHand(model.getPlayerHand(PlayerColor.BLUE));
    player1Hand.setCurrentPlayer(model.getCurrentPlayer() == PlayerColor.RED);
    player2Hand.setCurrentPlayer(model.getCurrentPlayer() == PlayerColor.BLUE);

    // Refresh game grid display
    gameGrid.updateGrid();

    revalidate();
    repaint();
  }

  @Override
  public void setFrameTitle(String msg) {
    setTitle(msg);
  }

  @Override
  public void selectCard(PlayerColor color, int cardIdxInHand) {
    if (color == PlayerColor.RED) {
      player1Hand.updateSelection(cardIdxInHand);
    } else {
      player2Hand.updateSelection(cardIdxInHand);
    }
  }

  // Makes the grid panel aware of the currently selected card index,
  // so that in the case that it detects a grid cell being pressed,
  // it knows which card to play
  protected void notifyGridOfSelection() {
    // Updates the value of the selected card in hand
    if (model.getCurrentPlayer() == PlayerColor.RED) {
      gameGrid.setSelectedCardIdx(player1Hand.getSelectedIndex());
    } else {
      gameGrid.setSelectedCardIdx(player2Hand.getSelectedIndex());
    }
  }

  @Override
  public void popUpMsg(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }

}