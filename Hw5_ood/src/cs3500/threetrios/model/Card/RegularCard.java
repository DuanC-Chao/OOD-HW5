package cs3500.threetrios.model.Card;

import java.util.UUID;

import cs3500.threetrios.model.Enums.CardNumber;
import cs3500.threetrios.model.Enums.Direction;
import cs3500.threetrios.model.Enums.Faction;
import cs3500.threetrios.model.Rule.ICombatRule;

public class RegularCard implements ICard {

  /**
   * The north number of the card.
   */
  CardNumber north;

  /**
   * The south number of the card.
   */
  CardNumber south;

  /**
   * The east number of the card.
   */
  CardNumber east;

  /**
   * The west number of the card.
   */
  CardNumber west;

  /**
   * The name of the card.
   */
  String name;

  /**
   * The faction of the card.
   */
  Faction faction;

  /**
   * The owner's ID of the card.
   */
  UUID ownerID;

  /**
   * Default constructor.
   */
  public RegularCard() {
    this.ownerID = null;
    this.faction = null;
    this.name = "Default Name";
  }

  @Override
  public void setCardNumber(CardNumber north, CardNumber east, CardNumber south, CardNumber west) {
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }

  @Override
  public void setCardName(String name) {
    if(name == null) {
      return;
    }
    this.name = name;
  }

  @Override
  public void setFaction(Faction faction) {
    this.faction = faction;
  }

  @Override
  public int compare(ICard other, Direction direction, ICombatRule rule) {
    if(other instanceof RegularCard) {
      RegularCard otherCard = (RegularCard) other;

    }
  }

  @Override
  public void setOwner(UUID owner) {

  }
}
