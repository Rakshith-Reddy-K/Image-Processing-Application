package controller;

import view.View;

/**
 * This is a MockView class which helps to test controller in isolation.
 */
class MockView implements View {
  private final Appendable out;

  /**
   * Instantiates a new Mock view.
   *
   * @param out the object of type Appendable.
   */
  public MockView(Appendable out) {
    this.out = out;
  }

  @Override
  public <T> void displayResult(Result<T> result) {
    try {
      out.append(result.getMessage());
    } catch (Exception e) {
      System.out.println("Error in mock view");
    }
  }

  @Override
  public <T> void displayMessage(Result<T> result) {
    try {
      out.append(result.getMessage());
    } catch (Exception e) {
      System.out.println("Error in mock view");
    }
  }

  @Override
  public void addController(ImageGUI imageGUIController) {
    //Add no implementation.
  }
}
