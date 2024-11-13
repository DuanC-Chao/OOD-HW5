package cs3500.threetrios.view;

import java.awt.*;

import javax.swing.*;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;

/**
 * An Abstract class of CardButton, defines some basic behaviors that CardButton classes shares.
 * Class Invariant:
 * The Preferred Width will always be 90.
 */
public abstract class ACardButton extends JButton implements ICardButton {

  ICard logicalCard;

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

    GridBagConstraints gbc = new GridBagConstraints();

    // Adjust distance with Top and Bottom bounds
    // Lower = Closer to the center of the Button
    int yOffset = 4;

    // Adjust distance with left and right bounds
    int xOffset = 4;

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
