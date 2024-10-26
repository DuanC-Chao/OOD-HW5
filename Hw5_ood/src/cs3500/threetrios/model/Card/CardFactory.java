package cs3500.threetrios.model.Card;

import cs3500.threetrios.model.Enums.CardNumber;

/**
 * The factory class for ICard.
 */
public class CardFactory {

  /**
   * A factory method, takes variables and return a constructed RegularCard.
   *
   * @param name    The name of the card.
   * @param faction The faction of the card.
   * @param north   The north number of the card.
   * @param south   The south number of the card.
   * @param west    The west number of the card.
   * @param east    The east number of the card.
   * @return A constructed RegularCard.
   * @throws IllegalArgumentException If any number (north, south, etc.) is not a valid CardNumber.
   */
  public static RegularCard createRegularCard(String name, String faction,
                                              int north, int south,
                                              int west, int east) {
    RegularCard card = new RegularCard();

    CardNumber[] cardNumbers = new CardNumber[4];

    getCardNumberMapping(cardNumbers, 0, north);
    getCardNumberMapping(cardNumbers, 1, east);
    getCardNumberMapping(cardNumbers, 2, south);
    getCardNumberMapping(cardNumbers, 3, west);

    card.setCardNumber(cardNumbers[0], cardNumbers[1], cardNumbers[2], cardNumbers[3]);

    card.setCardName(name);

    // For now, not implementing faction
    card.setFaction(null);

    return card;
  }

  /**
   * Map the stringNumber to CardNumber, and store it in cardNumbers[index].
   *
   * @param cardNumbers The array to store result.
   * @param index       The index to store result.
   * @param number      The number to map.
   */
  public static void getCardNumberMapping(CardNumber[] cardNumbers,
                                          int index, int number) {
    switch (number) {
      case 1:
        cardNumbers[index] = CardNumber.ONE;
        break;
      case 2:
        cardNumbers[index] = CardNumber.TWO;
        break;
      case 3:
        cardNumbers[index] = CardNumber.THREE;
        break;
      case 4:
        cardNumbers[index] = CardNumber.FOUR;
        break;
      case 5:
        cardNumbers[index] = CardNumber.FIVE;
        break;
      case 6:
        cardNumbers[index] = CardNumber.SIX;
        break;
      case 7:
        cardNumbers[index] = CardNumber.SEVEN;
        break;
      case 8:
        cardNumbers[index] = CardNumber.EIGHT;
        break;
      case 9:
        cardNumbers[index] = CardNumber.NINE;
        break;
      case 10:
        cardNumbers[index] = CardNumber.A;
        break;
      default:
        throw new IllegalArgumentException("Invalid card number: " + number);
    }
  }
}
