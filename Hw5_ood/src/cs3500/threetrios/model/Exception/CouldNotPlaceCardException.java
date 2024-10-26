package cs3500.threetrios.model.Exception;

/**
 * A self-defined exception, represent and only represent the scenario, that.
 * Trying to add a Card to a Cell that already has a card.
 */
public class CouldNotPlaceCardException extends Exception {

  /**
   * The Constructor of CardAlreadyExistException.
   * @param message Exception message.
   */
  public CouldNotPlaceCardException(String message) {
    super(message);
  }
}
