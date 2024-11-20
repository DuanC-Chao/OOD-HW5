package cs3500.threetrios.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import cs3500.threetrios.model.ICard;

/**
 * Represents a CardButton that is in a player's hand.
 * Hence, it contains information about card's in-hand index.
 */
public class InHandCardButton extends ACardButton implements IinHandCardButton {

  private final int cardIdx;

  /**
   * The delegate about picking a card.
   */
  private Consumer<Pick> delegate;

  /**
   * The contructor of InHandCardButton.
   *
   * @param card    The card to assign to the CardButton.
   * @param cardIdx The Index of card in hand.
   */
  public InHandCardButton(ICard card, int cardIdx, String color) {
    super(card);
    this.cardIdx = cardIdx;

    // Only invoked in InHandCardButton's constructor
    // Since for OnGridCardButton, there will be different logic of setting color
    changeColor(color);

    this.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Just for test, remove after implement Controller.
        System.out.println("Card Button with Index: " + cardIdx + "Pressed\n");

        // Controller will be dealing with the "Selected Card Buffer".
        if(InHandCardButton.this.delegate != null) {
          System.out.println("Calling delegate");
          delegate.accept(new Pick(InHandCardButton.this.logicalCard.getOwner(), cardIdx));
        } else {
          System.out.println("Delegate not set");
        }
      }
    });
  }

  @Override
  public int getCardIndex() {
    return cardIdx;
  }

  @Override
  public void passPickDelegate(Consumer<Pick> delegate) {

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
