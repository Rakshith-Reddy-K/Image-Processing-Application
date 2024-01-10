package view;

import org.junit.Before;
import org.junit.Test;

import controller.Result;

import static org.junit.Assert.assertEquals;

/**
 * This is a JUnit test class for ImageView class.
 */
public class ImageViewTest {

  StringBuffer output;
  private ImageView view;

  @Before
  public void setup() {
    output = new StringBuffer();
    view = new ImageView(output);
  }

  @Test
  public void testDisplayMessage_nonErrorMessage() {
    Result<String> result = new Result<>(null, "Success", false);
    view.displayMessage(result);
    assertEquals("Success", output.toString());
  }

  @Test
  public void testDisplayMessage_errorMessage() {
    Result<String> result = new Result<>(null, "Null exception", true);
    view.displayMessage(result);
    assertEquals("Error: Null exception", output.toString());
  }

  @Test
  public void testDisplayResult() {
    Result<String> result = new Result<>("Data", "Success", false);
    view.displayResult(result);
    assertEquals("Success", output.toString());
  }

  @Test
  public void testDisplayResult_error() {
    Result<String> result = new Result<>("Data", "Failure", true);
    view.displayResult(result);
    assertEquals("", output.toString());
  }
}
