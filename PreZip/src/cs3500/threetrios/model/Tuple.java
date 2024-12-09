package cs3500.threetrios.model;

/**
 * A class works exactly as Tuple structure in C#, just to fit my coding habit.
 */
public class Tuple<U, V> {

  private final U first;
  private final V second;

  /**
   * Constructs a Tuple with the specified values.
   *
   * @param first  the first value of the tuple
   * @param second the second value of the tuple
   */
  public Tuple(U first, V second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Gets the first value of the tuple.
   *
   * @return the first value
   */
  public U getFirst() {
    return first;
  }

  /**
   * Gets the second value of the tuple.
   *
   * @return the second value
   */
  public V getSecond() {
    return second;
  }
}
