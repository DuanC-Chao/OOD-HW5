package cs3500.threetrios.provider.view;

import java.awt.*;

import javax.swing.*;

import cs3500.threetrios.provider.model.GameCard;

/**
 * A graphical representation of a card in the game of ThreeTrios.
 */
public class CardPanel extends JPanel {
  private final GameCard card;
  private final Color ownerColor;
  private boolean selected = false;

  private static final Font CARD_FONT = new Font("Arial", Font.BOLD, 18);

  public CardPanel(GameCard card, Color ownerColor) {
    this.card = card;
    this.ownerColor = ownerColor;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Set background color for the card, matching the color used in the grid
    g2d.setColor(ownerColor);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    // Set fixed font and color for the text on the card
    g2d.setFont(CARD_FONT);
    g2d.setColor(Color.BLACK);

    // Calculate center coordinates and use consistent positioning
    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;

    // Draw each abbreviation in its grid-like position
    FontMetrics metrics = g2d.getFontMetrics(CARD_FONT);
    g2d.drawString(card.north.getAbbrev(), centerX - metrics.stringWidth(
            card.north.getAbbrev()) / 2, centerY - metrics.getHeight()); // North
    g2d.drawString(card.south.getAbbrev(), centerX - metrics.stringWidth(
            card.south.getAbbrev()) / 2, centerY + metrics.getHeight()); // South
    g2d.drawString(card.west.getAbbrev(), centerX - (metrics.stringWidth(
            card.west.getAbbrev()) + 20), centerY + metrics.getHeight() / 4); // West
    g2d.drawString(card.east.getAbbrev(), centerX + 20, centerY + metrics.getHeight() / 4); // East

    // Draw a thin black border around the card
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(0.5F));
    g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

    // If the card is selected, draw a thicker border inside the black border
    if (selected) {
      g2d.setStroke(new BasicStroke(3));
      g2d.drawRect(3, 3, getWidth() - 6, getHeight() - 6);
    }
  }
}


