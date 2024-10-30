package cs3500.threetrios.model.Exception;

/**
 * This exception is thrown when trying to read a configuration file using JSON_Explainer with.
 * A file path that does not exist.
 */
public class NoSuchConfigException extends RuntimeException {
  public NoSuchConfigException(String message) {
    super(message);
  }
}
