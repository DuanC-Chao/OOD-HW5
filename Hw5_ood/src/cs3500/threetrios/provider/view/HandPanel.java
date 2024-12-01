package cs3500.threetrios.provider.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import model.GameCard;

/**
 * A graphical representation of a player's hand in a game of ThreeTrios.
 */
class HandPanel extends JPanel {
  private List<GameCard> hand;
  private int selectedCardIndex = -1;
  private final Color panelColor;
  private final ThreeTriosGUI gui;
  private boolean currentPlayer;
  private PlayerActions features;

  /**
   * Constructs a HandPanel with the given color and gui to reference.
   * @param panelColor the color of the player's cards
   * @param gui the referenced gui
   */
  public HandPanel(Color panelColor, ClassicThreeTriosGUI gui) {
    this.currentPlayer = false;
    this.gui = gui;
    this.panelColor = panelColor;
    setPreferredSize(new Dimension(150, 0));
    setBackground(panelColor);
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    setLayout(new GridLayout(0, 1, 0, 0));
  }

  public void setFeatureListener(PlayerActions features) {
    this.features = features;
  }

  // Sets the currentPlayer field to the given boolean value
  public void setCurrentPlayer(boolean current) {
    currentPlayer = current;
  }

  /**
   * Creates CardPanels to occupy this panel, each representing a card in the player's hand.
   * Resets the selected card.
   * @param hand the player's hand to be represented
   */
  public void renderHand(List<GameCard> hand) {
    this.hand = hand;
    selectedCardIndex = -1;
    removeAll();

    int cardHeight = getHeight() / Math.max(hand.size(), 1);
    for (GameCard card : hand) {
      CardPanel cardPanel = new CardPanel(card, panelColor);
      cardPanel.setPreferredSize(new Dimension(getWidth(), cardHeight));
      add(cardPanel);
    }

    revalidate();
    repaint();
  }

  // Updates the selected card in the hand, and refreshes the hand accordingly
  // but only if this player is the current player
  protected void updateSelection(int cardIdx) {
    if (currentPlayer) {
      System.out.println("Selection updated");
      selectedCardIndex = cardIdx;
      for (int i = 0; i < getComponentCount(); i++) {
        CardPanel cardPanel = (CardPanel) getComponent(i);
        cardPanel.setSelected(i == cardIdx);
      }
      ((ClassicThreeTriosGUI) gui).notifyGridOfSelection();
      repaint();
    }
  }

  // Returns the index of the currently selected card
  public int getSelectedIndex() {
    return selectedCardIndex;
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
      if (currentPlayer) {
        int cardIdx = getCardIdx(e.getPoint());
        features.selectCard(cardIdx);
        System.out.println("Selected card at index: " + cardIdx);
      }
    }

    private int getCardIdx(Point point) {
      if (hand == null || hand.isEmpty()) {
        return - 1;
      }

      int cardHeight = getHeight() / hand.size();
      int clickedIndex = (int) (point.getY() / cardHeight);
      if (clickedIndex >= 0 && clickedIndex < hand.size()) {
        return (selectedCardIndex == clickedIndex) ? -1 : clickedIndex;
      } else {
        return -1;
      }
    }
  }

}