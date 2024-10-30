package cs3500.threetrios.model;

import java.util.List;
import java.util.Map;

/**
 * For JSON, represent the deck data model.
 */
public class DeckConfig {
  List<CardConfig> TestDeck;

  /**
   * For JSON, represent the single card data model.
   */
  public static class CardConfig {
    String name;
    String faction;
    Map<String, Integer> values;
  }
}
