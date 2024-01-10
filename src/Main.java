import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageController;
import controller.ImageGUIController;
import model.AbstractImageModel;
import model.ImageModelV2;
import view.ImageGraphicalView;
import view.ImageView;
import view.View;

/**
 * Main class of the program. It creates a model, view and controller of the program and execute
 * the controller.
 */
public class Main {
  /**
   * Main function to run the program.
   *
   * @param args arguments that user will pass into the program
   */
  public static void main(String[] args) {
    if (!Helper.checkValidArgs(args)) {
      System.err.println("Invalid command line argument");
      return;
    }
    AbstractImageModel model = new ImageModelV2();
    View view;
    try {
      if (args.length == 0) {
        view = new ImageGraphicalView();
        new ImageGUIController(model, new StringReader(""),
                view);
      } else if (args.length == 1) {
        view = new ImageView(System.out);
        ImageController controller = new ImageController(model, new InputStreamReader(System.in),
                view);
        controller.execute();
      } else {
        view = new ImageView(System.out);
        ImageController controller = new ImageController(model, new FileReader(args[1]), view);
        controller.executeFile();
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
