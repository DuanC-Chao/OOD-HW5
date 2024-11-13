package cs3500.threetrios.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import cs3500.threetrios.model.ICard;

/**
 * Represents a CardButton that is in a player's hand.
 * Hence, it contains information about card's in-hand index.
 */
public class InHandCardButton extends ACardButton implements IinHandCardButton {

  private final int cardIdx;

  /**
   * The contructor of InHandCardButton.
   * @param card The card to assign to the CardButton.
   * @param cardIdx The Index of card in hand.
   */
  public InHandCardButton(ICard card, int cardIdx, String color) {
    super(card);
    this.cardIdx = cardIdx;

    // Only invoked in InHandCardButton's constructor
    // Since for OnGridCardButton, there will be different logic of setting color
    changeColor(color);
  }

  @Override
  public int getCardIndex() {
    return cardIdx;
  }

  @Override
  public ICard getCard() {
    return logicalCard;
  }

  @Override
  protected void configStyle() {
    this.setContentAreaFilled(true);
    this.setOpaque(true);
    this.setBorderPainted(true);
  }
}
