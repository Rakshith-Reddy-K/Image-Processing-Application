package view;

import java.io.IOException;

import controller.ImageGUI;
import controller.Result;

/**
 * The ImageView class implements the View interface and provides methods to display results and
 * messages.
 */
public class ImageView implements View {
  private final Appendable out;

  /**
   * Constructs an ImageView object with the specified Appendable output.
   *
   * @param out the Appendable output to which the results and messages will be displayed.
   */
  public ImageView(Appendable out) {
    this.out = out;
  }

  @Override
  public <T> void displayResult(Result<T> result) {
    if (result.getData() != null && !result.isError()) {
      this.displayMessage(result);
    }
  }

  @Override
  public <T> void displayMessage(Result<T> result) {
    try {
      if (result.isError()) {
        out.append("Error: ").append(result.getMessage());
      } else {
        out.append(result.getMessage());
      }
    } catch (IOException e) {
      throw new RuntimeException("Cannot access view\n");
    }
  }

  @Override
  public void addController(ImageGUI imageGUIController) {
    //Add no UI controller.
  }
}
