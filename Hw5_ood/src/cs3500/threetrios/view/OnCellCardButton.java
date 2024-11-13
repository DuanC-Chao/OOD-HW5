package cs3500.threetrios.view;

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
}
