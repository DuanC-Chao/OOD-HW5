package cs3500.threetrios.model.Config;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.Card.CardFactory;
import cs3500.threetrios.model.Card.RegularCard;

/**
 * This class explains JSON file, work with CardFactory, dynamically construct board.
 */
public class JSON_Explainer {

  /**
   * Takes a filename, and read the content from the file.
   *
   * @param fileName The name of file wished to read from.
   * @return A Deck Config, contains all necessary content from JSON file.
   */
  public static DeckConfig readDeckConfig(String fileName) {
    Gson gson = new Gson();
    DeckConfig deckConfig = null;

    try (FileReader reader = new FileReader(fileName)) {
      // Map JSON content to DeckConfig class.
      deckConfig = gson.fromJson(reader, DeckConfig.class);
    } catch (JsonIOException | JsonSyntaxException | IOException e) {
      System.err.println("Error reading JSON file: " + e.getMessage());
    }

    return deckConfig;
  }

  /**
   * Takes a file name of a JSON file, read configuration from it, and build a deck with it.
   *
   * @param fileName The name of the file.
   * @return A List of RegularCards, the deck.
   */
  static List<RegularCard> getDeckWithFileName(String fileName) {
    String relativePath = "../../../docs/";
    relativePath += fileName;

    List<RegularCard> cards = new ArrayList<>();
    DeckConfig deckConfig = readDeckConfig(fileName);

    for (DeckConfig.CardConfig cardConfig : deckConfig.TestDeck) {
      String name = cardConfig.name;
      String faction = cardConfig.faction;
      int north = cardConfig.values.get("north");
      int south = cardConfig.values.get("south");
      int east = cardConfig.values.get("east");
      int west = cardConfig.values.get("west");

      cards.add(CardFactory.createRegularCard(name, faction, north, south, east, west));
    }

    return cards;
  }
}
