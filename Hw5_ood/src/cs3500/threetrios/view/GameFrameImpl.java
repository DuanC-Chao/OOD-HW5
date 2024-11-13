package cs3500.threetrios.view;

import cs3500.threetrios.model.ReadOnlyTripleTriadModel;
import javax.swing.*;
import java.awt.*;

public class GameFrameImpl extends JFrame {
  private final GameGridPanel gridPanel;
  private final PlayerHandPanel leftHandPanel;
  private final PlayerHandPanel rightHandPanel;
  private final JLabel playerTurnLabel;

  public GameFrameImpl(ReadOnlyTripleTriadModel model) {
    // Set up main frame properties
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Three Trios Game");
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(1000, 600));

    // Initialize components
    this.gridPanel = new GameGridPanel(model);
    this.leftHandPanel = new PlayerHandPanel(model, 1, Color.PINK); // For player one
    this.rightHandPanel = new PlayerHandPanel(model, 2, Color.CYAN); // For player two
    this.playerTurnLabel = new JLabel("Current Player: RED", SwingConstants.CENTER);

    // Set font for the player turn label
    playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 16));

    // Add components to frame
    this.add(playerTurnLabel, BorderLayout.NORTH);
    this.add(leftHandPanel, BorderLayout.WEST);
    this.add(rightHandPanel, BorderLayout.EAST);
    this.add(gridPanel, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
  }

  // Method to update the current player label
  public void setPlayerTurn(String playerName) {
    playerTurnLabel.setText("Current Player: " + playerName);
  }

  // Refresh the view based on the updated model
  public void updateView() {
    gridPanel.updatePanel();
    leftHandPanel.updatePanel();
    rightHandPanel.updatePanel();
    this.repaint();
  }
}
