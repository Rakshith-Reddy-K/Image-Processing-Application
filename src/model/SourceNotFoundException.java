package model;

/**
 * A SourceNotFoundException that output the SourceNotFoundException according to our desire.
 */
public class SourceNotFoundException extends Exception {

  private static final String MESSAGE = "The provided source doesn't exist";

  /**
   * Instantiates a new Source not found exception.
   */
  public SourceNotFoundException() {
    super(MESSAGE);
  }
}
