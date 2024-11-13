package cs3500.threetrios.view;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.EPlayer;
import cs3500.threetrios.model.ICard;
import cs3500.threetrios.model.ReadOnlyTripleTriadModel;

import static cs3500.threetrios.view.ViewUtils.refreshPanel;

/**
 * Represent a hand panel, shows a player's hand, arrayed vertically.
 * About Layout Beautify:
 * There are some Glues between each CardButton, the TOTAL SIZE of Glues should be: 30.
 * In the default scenario, there should be 7 CardButton with height 120.
 * Total height is 840, with 30 Glue heights, total Height should be 870.
 * For Less than 7 cards, CardButton height still be 120.
 * Just not to fill the HandPanel.
 * For more than 7 cards, CardButton height = (870 - 30)/CardButton number.
 * When CardButton numbers are big, the appearance of layout WILL NOT be insured.
 */
public class HandPanel extends JPanel implements IHandPanel {

  /**
   * The CardButtons managed as a list.
   * Should be always syncronized with Panel's component management logic.
   */
  private List<IinHandCardButton> cards;

  /**
   * The model of the game.
   */
  private final ReadOnlyTripleTriadModel model;

  /**
   * Represent which player's hand this panel represents.
   */
  private final EPlayer player;

  public HandPanel(EPlayer player, ReadOnlyTripleTriadModel model) {
    this.model = model;
    this.player = player;
    this.cards = new ArrayList<>();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setPreferredSize(new Dimension(100, 870));
    this.setBackground(Color.decode(ElementColor.HAND_PANEL_BACKGROUBND_COLOR.toString()));
  }

  /**
   * Takes a list of logical cards as ICard, and add them all to HandPanel.
   * The list of ICard will be considered player's hand.
   * Added as InHandCardButton, their sequence are determined by index in the list.
   * Index 0 is on the top of CardPanel, index list.size() - 1 is on the bottom.
   * @param cards The List of logical cards.
   */
  private void updateHand(List<ICard> cards) {

    int wishedHeight = Size.CARD_HEIGHT.getSize();
    if (840 / cards.size() < Size.CARD_HEIGHT.getSize()) {
      wishedHeight = 840 / cards.size();
    }
    System.out.println("Should be: " + cards.size() + " CardButtons added \n");
    for (int i = 0; i < cards.size(); i++) {
      addSingleCard(cards.get(i), i, i == cards.size() - 1, wishedHeight);
    }
    System.out.println("Alcutally added: " + this.getComponentCount() + " CardButtons \n");
  }

  /**
   * Helper method, takes a card, and a boolean indicates if is end, and add the card and
   *
   * @param card            The logical card to add.
   * @param cardIdx         The index of the logical card in hand.
   * @param isEnd           Is the card the last one in the hand.
   * @param preferredHeight The preferred height of the cardButton.
   */
  private void addSingleCard(ICard card, int cardIdx, boolean isEnd, int preferredHeight) {
    IinHandCardButton cardButton = new InHandCardButton(card, cardIdx, this.getColorBasedOnPlayer());
    cardButton.setPreferredYValue(preferredHeight);
    cards.add(cardButton);
    this.add((Component) cardButton);
    if (!isEnd) {
      this.add(Box.createVerticalGlue());
    }
  }

  @Override
  public void refresh() {
    if (this.player == null) {
      throw new IllegalStateException("When refreshing HandPanel: Encountered " +
        "HandPanel with null player");
    }
    // Remove all cardButtons from HandPanel first.
    removeAll();
    int playerNum = 0;
    switch (this.player) {
      case PLAYER_ONE:
        playerNum = 1;
        break;
      case PLAYER_TWO:
        playerNum = 2;
        break;
      default:
        throw new IllegalStateException("When refreshing HandPanel: " +
          "Encountered unexpected EPlayer");
    }
    refreshPanel(this);
    List<ICard> hand = this.model.getPlayerHand(playerNum);
    this.updateHand(hand);
  }

  /**
   * Get the color Hex based on this HandPanel's player.
   * @return A Stirng of color hex.
   */
  private String getColorBasedOnPlayer() {
    if(this.player == EPlayer.PLAYER_ONE) {
      return ElementColor.PLAYER_ONE_COLOR.toString();
    } else if(this.player == EPlayer.PLAYER_TWO) {
      return ElementColor.PLAYER_TWO_COLOR.toString();
    } else {
      throw new IllegalArgumentException("Unknown player " + this.player);
    }
  }
}

