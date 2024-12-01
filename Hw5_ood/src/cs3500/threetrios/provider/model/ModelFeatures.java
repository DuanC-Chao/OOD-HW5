package cs3500.threetrios.provider.model;

/**
 * Represents a features interface for the ClassicThreeTriosModel.
 */
public interface ModelFeatures {

  /**
   * Notifies a player that it is their turn.
   */
  void notifyOfTurn();

  /**
   * Notifies a player that a move has been made on the board.
   */
  void notifyStateUpdate();
}
