package cs3500.threetrios.view;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import cs3500.threetrios.model.ICard;

/**
 * An Abstract class of CardButton, defines some basic behaviors that CardButton classes shares.
 * Class Invariant:
 * The Preferred Width will always be 90.
 */
public abstract class ACardButton extends JButton implements ICardButton {

  ICard logicalCard;

  GridBagConstraints gbc;

  private JLabel northLabel;
  private JLabel southLabel;
  private JLabel eastLabel;
  private JLabel westLabel;

  /**
   * Default Constructor, takes a card.
   *
   * @param card The logical card used to create CardButton.
   */
  public ACardButton(ICard card) {

    setLayout(new GridBagLayout());

    this.logicalCard = card;

    initializePreferredSize();

    initializeLabels();
  }

  /**
   * Initialize all for number labels for the CardButton.
   * After initialization, they should be good to display.
   */
  private void initializeLabels() {

    this.northLabel = createCardNumberLabel(logicalCard.getNorth().toString());
    this.southLabel = createCardNumberLabel(logicalCard.getSouth().toString());
    this.eastLabel = createCardNumberLabel(logicalCard.getEast().toString());
    this.westLabel = createCardNumberLabel(logicalCard.getWest().toString());

    adjustLabelPositions();
  }

  /**
   * Helper method, create a CardNumber Label.
   *
   * @param text The test wished for the Label.
   * @return A JLabel.
   */
  private JLabel createCardNumberLabel(String text) {
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 16));
    label.setForeground(Color.BLACK);
    label.setPreferredSize(new Dimension(40, 20));
    return label;
  }

  /**
   * Adjust position of labels, relative to size of CardButton.
   */
  private void adjustLabelPositions() {

    // Add all labels to components
    this.add(northLabel);
    this.add(southLabel);
    this.add(eastLabel);
    this.add(westLabel);

    gbc = new GridBagConstraints();

    // Settle labels coordinate.
    setLabelDistanceWithBound(4, 4);
  }

  /**
   * Helper method, settle the Distance of lebel with bound with a ratio.
   * Lower = Closer to the center.
   *
   * @param xOffset The x offset ratio.
   * @param yOffset The y offset ratio.
   */
  private void setLabelDistanceWithBound(int xOffset, int yOffset) {
    // First remove all labels.
    removeAll();

    gbc.insets = new Insets(yOffset, xOffset, yOffset, xOffset);

    // Adjust North Label's Position
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.NORTH;
    add(northLabel, gbc);

    // Adjust South Label's Position
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.SOUTH;
    add(southLabel, gbc);

    // Adjust West Label's Position
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(westLabel, gbc);

    // Adjust East Label's Position
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.EAST;
    add(eastLabel, gbc);
  }

  /**
   * Initialize the preferred initial size of the CardButton.
   * THe Default Width is 90.
   * The Default Height is 120.
   */
  private void initializePreferredSize() {
    setPreferredSize(new Dimension(100, Size.CARD_HEIGHT.getSize()));
    setMaximumSize(new Dimension(100, Size.CARD_HEIGHT.getSize()));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  @Override
  public void setPreferredYValue(int yValue) {
    setPreferredSize(new Dimension(100, yValue));
    int ratio = 4;
    if (yValue < Size.CARD_HEIGHT.getSize()) {
      ratio = (yValue / Size.CARD_HEIGHT.getSize()) * 4;
    }
    setLabelDistanceWithBound(4, ratio);
  }

  @Override
  public void changeColor(String hex) {
    this.setBackground(Color.decode(hex));
  }

  /**
   * A method to config each CardButton sub-class' style.
   * To be Overrided.
   */
  protected abstract void configStyle();
}
