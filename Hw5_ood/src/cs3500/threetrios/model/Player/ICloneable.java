package cs3500.threetrios.model.Player;

/**
 * A generic prototype interface, for class to clone themselves.
 */
public interface ICloneable<T> {

  /**
   * The method to clone an object.
   * @return A cloned object of Type T.
   */
  T makeClone();
}
