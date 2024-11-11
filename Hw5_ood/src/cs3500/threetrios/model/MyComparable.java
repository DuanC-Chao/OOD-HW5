package cs3500.threetrios.model;

/**
 * A DIY Comparable class.
 * @param <T> A generic type.
 */
public interface MyComparable<T> {

  /**
   * Takes an T, and return if the invoking T is the same as the other 'T'.
   * @param other Another T object.
   * @return A boolean, represent weather this and other are the same.
   */
  boolean myCompare(T other);
}
