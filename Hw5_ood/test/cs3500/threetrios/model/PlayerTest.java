package cs3500.threetrios.model;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Test toward Player class.
 */
public class PlayerTest {

  // Model Interface-Testing Section (Public Method Tests)

  @Test
  public void testConstructorAndGetters() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    Assert.assertEquals("Alice", player.getName());
    Assert.assertEquals(EPlayer.PLAYER_ONE, player.getIdentity());
    Assert.assertTrue(player.getHand().isEmpty());
  }

  @Test
  public void testSetName() {
    Player player = new Player(EPlayer.PLAYER_TWO, "Bob");
    player.setName("Charlie");
    Assert.assertEquals("Charlie", player.getName());
  }

  @Test
  public void testSetHand() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    List<ICard> deck = new ArrayList<>();
    deck.add(new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR,
            "Card 1", EPlayer.PLAYER_TWO));
    player.setHand(deck, 1);
    Assert.assertEquals(1, player.getHand().size());
    Assert.assertEquals(EPlayer.PLAYER_ONE, player.getHand().get(0).getOwner());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetHandWithNullDeck() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    player.setHand(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetHandWithInsufficientDeck() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    List<ICard> deck = new ArrayList<>();
    player.setHand(deck, 1);
  }

  @Test
  public void testPopCardFromHand() {
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice");
    List<ICard> deck = new ArrayList<>();
    RegularCard card = new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE,
            CardNumber.FOUR, "Card 1", EPlayer.PLAYER_ONE);
    deck.add(card);
    player.setHand(deck, 1);
    // The card in player's hand is a clone.
    Assert.assertNotEquals(card, player.popCardFromHand(0));
    Assert.assertTrue(player.getHand().isEmpty());
  }

  // Implementation-Testing Section (Testing Clone Functionality)
  @Test
  public void testMakeClone() {
    List<ICard> hand = new ArrayList<>();
    hand.add(new RegularCard(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR,
            "Card 1", EPlayer.PLAYER_ONE));
    Player player = new Player(EPlayer.PLAYER_ONE, "Alice", hand);
    Player clonedPlayer = (Player) player.makeClone();

    Assert.assertNotSame(player, clonedPlayer);
    Assert.assertEquals(player.getName(), clonedPlayer.getName());
    Assert.assertEquals(player.getIdentity(), clonedPlayer.getIdentity());
    Assert.assertEquals(player.getHand(), clonedPlayer.getHand());
  }
}
