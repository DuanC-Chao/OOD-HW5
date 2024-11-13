package cs3500.threetrios.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.threetrios.model.ReadOnlyTripleTriadModel;


public class PlayerHandPanel extends JPanel {
  private final ReadOnlyTripleTriadModel model;
  private final int playerNumber;
  private int selectedCardIndex = -1;

  public PlayerHandPanel(ReadOnlyTripleTriadModel model, int playerNumber, Color backgroundColor) {
    this.model = model;
    this.playerNumber = playerNumber;
    this.setBackground(backgroundColor);
    this.setPreferredSize(new Dimension(100, 600));

    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int cardHeight = getHeight() / model.getPlayerHand(playerNumber).size();
        selectedCardIndex = e.getY() / cardHeight;
        System.out.println("Player " + playerNumber + " selected card at index: " + selectedCardIndex);
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int cardHeight = getHeight() / model.getPlayerHand(playerNumber).size();
    Font font = new Font("Arial", Font.BOLD, cardHeight / 2);
    g2d.setFont(font);

    for (int i = 0; i < model.getPlayerHand(playerNumber).size(); i++) {
      if (i == selectedCardIndex) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, i * cardHeight, getWidth(), cardHeight);
      } else {
        g2d.setColor(getBackground());
      }
      g2d.fillRect(0, i * cardHeight, getWidth(), cardHeight);

      // Draw card text
      g2d.setColor(Color.BLACK);
      g2d.drawString("Card " + (i + 1), 10, (i + 1) * cardHeight - cardHeight / 3);
    }
  }

  public void updatePanel() {
    repaint();
  }
}

