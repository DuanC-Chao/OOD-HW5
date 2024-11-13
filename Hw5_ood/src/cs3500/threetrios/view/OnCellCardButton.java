package cs3500.threetrios.view;

import java.awt.*;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;

/**
 * Represent a CardButton, that is played to the grid.
 */
public class OnCellCardButton extends ACardButton implements IOnCellCardButton {

  /**
   * The default constructor.
   * @param card The card.
   */
  public OnCellCardButton(ICard card) {
    super(card);
    setColor();
    setMinimumSize(new Dimension(100, Size.CARD_HEIGHT.getSize()));
  }

  /**
   * Another constructor that takes a InHandCardButton and convert into a OnGridCardButton.
   * @param cardButton The InHandCardButton.
   */
  public OnCellCardButton(IinHandCardButton cardButton) {
    super(cardButton.getCard());
  }

  @Override
  public ICard getCard() {
    return logicalCard;
  }

  @Override
  protected void configStyle() {

  }

  /**
   * Helper method. set itself's color based on card's owner.
   */
  private void setColor() {
    if(logicalCard.getOwner() == null) {
      throw new IllegalStateException();
    }
    if(logicalCard.getOwner() == EPlayer.PLAYER_ONE) {
      this.changeColor(ElementColor.PLAYER_ONE_COLOR.toString());
    } else if(logicalCard.getOwner() == EPlayer.PLAYER_TWO) {
      this.changeColor(ElementColor.PLAYER_TWO_COLOR.toString());
    }
  }
}
