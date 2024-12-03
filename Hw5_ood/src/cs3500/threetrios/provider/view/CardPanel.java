package cs3500.threetrios.provider.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import cs3500.threetrios.provider.model.GameCard;

/**
 * A graphical representation of a card in the game of ThreeTrios.
 * This panel displays a card's attributes (north, south, east, west values)
 * and highlights the card if it is selected.
 */
public class CardPanel extends JPanel {
  private final GameCard card;
  private final Color ownerColor;
  private boolean selected = false;
  private static final Font CARD_FONT = new Font("Arial", Font.BOLD, 18);

  /**
   * Constructs a {@code CardPanel} with the specified game card and owner color.
   *
   * @param card       the {@link GameCard} to be represented in this panel
   * @param ownerColor the color representing the owner of this card
   */
  public CardPanel(GameCard card, Color ownerColor) {
    this.card = card;
    this.ownerColor = ownerColor;
  }

  /**
   * Sets whether this card is selected and repaints the panel to reflect the state.
   *
   * @param selected {@code true} if the card is selected, {@code false} otherwise
   */
  public void setSelected(boolean selected) {
    this.selected = selected;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setColor(ownerColor);
    g2d.fillRect(0, 0, getWidth(), getHeight());

    g2d.setFont(CARD_FONT);
    g2d.setColor(Color.BLACK);

    int centerX = getWidth() / 2;
    int centerY = getHeight() / 2;

    FontMetrics metrics = g2d.getFontMetrics(CARD_FONT);
    g2d.drawString(card.north.getAbbrev(), centerX - metrics.stringWidth(
            card.north.getAbbrev()) / 2, centerY - metrics.getHeight()); // North
    g2d.drawString(card.south.getAbbrev(), centerX - metrics.stringWidth(
            card.south.getAbbrev()) / 2, centerY + metrics.getHeight()); // South
    g2d.drawString(card.west.getAbbrev(), centerX - (metrics.stringWidth(
            card.west.getAbbrev()) + 20), centerY + metrics.getHeight() / 4); // West
    g2d.drawString(card.east.getAbbrev(), centerX + 20, centerY + metrics.getHeight() / 4);

    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(0.5F));
    g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

    if (selected) {
      g2d.setStroke(new BasicStroke(3));
      g2d.drawRect(3, 3, getWidth() - 6, getHeight() - 6);
    }
  }
}


