package cs3500.threetrios.model;

/**
 * Represent All possible card numbers.
 */
public enum CardNumber {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  A(10);

  /**
   * The number.
   */
  private final int number;

  CardNumber(int number) {
    this.number = number;
  }

  /**
   * The toString() method.
   * @return A String of current number.
   */
  public String toString() {
    return number + "";
  }

  /**
   * Get the number as int.
   * @return An int number.
   */
  public int getNumber() {
    return number;
  }
}
