package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a simple Bot.
 * This Bot simply tries to play the card that could flip the most
 * enemy card to the cell that could flip the most enemy card.
 */
public class SimpleBot extends ABot {

  @Override
  public void play(ITripleTriadModel model) {
    //First, get a list of all available coordinates
    List<Tuple<Integer, Integer>> loAvailableCoord = getAvailableTryCoordinate(model);

    Tuple<Integer, Integer> decidedCoord = null;
    List<ICard> availableCardList = model.getPlayerHand(2);
    int maxLocalFlip = 0;
    int decidedCardIndex = -1;
    for (Tuple<Integer, Integer> coord : loAvailableCoord) {
      for (int cardIte = 0; cardIte < availableCardList.size(); cardIte++) {
        ICard card = availableCardList.get(cardIte);
        if (model.calculateFlips(EPlayer.PLAYER_TWO, card, coord.getFirst(), coord.getSecond()) > maxLocalFlip) {
          maxLocalFlip = model.calculateFlips(EPlayer.PLAYER_TWO, card, coord.getFirst(), coord.getSecond());
          decidedCardIndex = cardIte;
          decidedCoord = coord;
        }
      }
    }

    if (decidedCardIndex == -1) {
      throw new IllegalStateException("There should be at least one card decided");
    }
    model.playToGrid(2, decidedCardIndex, decidedCoord.getFirst(), decidedCoord.getSecond());
  }
}
