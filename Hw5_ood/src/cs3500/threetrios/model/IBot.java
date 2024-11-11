package cs3500.threetrios.model;

/**
 * Represent a bot in Triple Triad game.
 */
public interface IBot {

  /**
   * Takes a model, and play the card with predefined behaviors.
   * @param model The model to operate.
   * @throws IllegalStateException If no available spots to place card exist.
   */
  void play(ITripleTriadModel model);
}
