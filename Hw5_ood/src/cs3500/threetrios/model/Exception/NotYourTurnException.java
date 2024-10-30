package cs3500.threetrios.model.Exception;

/**
 * The exception represents trying to play with a player when its another player's turn.
 */
public class NotYourTurnException extends RuntimeException {
  public NotYourTurnException(String message) {
    super(message);
  }
}
