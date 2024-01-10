package view;

import controller.ImageGUI;
import controller.Result;

/**
 * The View interface represents the interface for displaying results and messages.
 */
public interface View {

  /**
   * Displays the result of type T.
   *
   * @param <T>    the type of the result
   * @param result the result to be displayed
   */
  <T> void displayResult(Result<T> result);

  /**
   * Displays the message associated with the result of type T.
   *
   * @param <T>    the type of the result
   * @param result the result whose message is to be displayed
   */
  <T> void displayMessage(Result<T> result);

  /**
   * Add controller to the view.
   *
   * @param imageGUIController the image gui controller.
   */
  void addController(ImageGUI imageGUIController);
}
