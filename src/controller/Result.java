package controller;

/**
 * The Result class represents a data structure that holds a result along with an associated
 * message and error flag.
 *
 * @param <T> the type of the data held in the Result
 */
public class Result<T> {
  private final T data;
  private final String message;
  private final boolean isError;

  /**
   * Constructs a Result object with the provided data, message, and error flag.
   *
   * @param data    the data to be stored in the Result
   * @param message the message associated with the Result
   * @param isError the boolean flag indicating whether the Result represents an error
   */
  public Result(T data, String message, boolean isError) {
    this.data = data;
    this.message = message;
    this.isError = isError;
  }

  /**
   * Returns the data stored in the Result.
   *
   * @return the data stored in the Result
   */
  public T getData() {
    return data;
  }

  /**
   * Returns the data stored in the Result.
   *
   * @return the data stored in the Result
   */
  public String getMessage() {
    return message;
  }

  /**
   * Returns whether the Result represents an error.
   *
   * @return true if the Result represents an error, false otherwise
   */
  public boolean isError() {
    return isError;
  }
}
