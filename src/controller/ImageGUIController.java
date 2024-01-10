package controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

import model.AbstractImageModel;
import view.View;

/**
 * The type Image gui controller.
 */
public class ImageGUIController extends ImageController implements ImageGUI {
  private View view;
  private String command;
  private String currentImageName;

  private int noOfOperations;

  /**
   * Constructs an object of image controller.
   *
   * @param model the abstract image model.
   * @param in    an object of Readable interface.
   * @param view  the GUI for showing the results.
   */
  public ImageGUIController(AbstractImageModel model, Readable in, View view) {
    super(model, in, view);
    setView(view);
    noOfOperations = 0;
    command = null;
    currentImageName = null;
  }

  @Override
  public void setView(View view) {
    this.view = view;
    view.addController(this);
  }

  @Override
  public void executeUserCommandInput(String userInputCommand, String args,
                                      String splitPercentage) {
    String destImageName = null;
    try {
      switch (userInputCommand) {
        case "Load":
          destImageName = "load" + "-Image" + noOfOperations;
          command = "load" + " " + args + " " + destImageName;
          currentImageName = destImageName;
          break;
        case "Save":
          command = "save" + " " + args + " " + currentImageName;
          break;
        case "red-component":
        case "blue-component":
        case "green-component":
        case "vertical-flip":
        case "horizontal-flip":
        case "blur":
        case "sharpen":
        case "sepia":
        case "luma-component":
        case "color-correct":
          destImageName = userInputCommand + "-Image" + noOfOperations;
          command = userInputCommand + " " + currentImageName + " " + destImageName;
          break;
        case "compress":
          try {
            double percentage = Double.parseDouble(args);
            if (percentageNotInRange(percentage)) {
              view.displayMessage(new Result<>(null,
                      "Compress percentage not in range\n", true));
              return;
            }
            destImageName = userInputCommand + "-Image" + noOfOperations;
            command =
                    userInputCommand + " " + percentage + " " + currentImageName + " "
                            + destImageName;
            break;
          } catch (NumberFormatException e) {
            view.displayMessage(new Result<>(null,
                    "Compress percentage is not valid\n", true));
          }
          break;
        case "level-adjustment":
          String[] value = args.split(" ");
          try {
            int b = Integer.parseInt(value[0]);
            int m = Integer.parseInt(value[1]);
            int w = Integer.parseInt(value[2]);
            if (levelNotInRange(b) || levelNotInRange(m) || levelNotInRange(w) || b > m || b > w
                    || m > w) {
              view.displayMessage(new Result<>(null,
                      "The values b,m,w provided are not in range " +
                              "or order\n", true));
              return;
            }
            destImageName = "levels-adjust" + "-Image" + noOfOperations;
            command = "levels-adjust" + " " + b + " " + m + " " + w + " "
                    + currentImageName + " " + destImageName;
            break;
          } catch (NumberFormatException e) {
            view.displayMessage(new Result<>(null,
                    "The values b,m,w provided are not valid\n",
                    true));
          }
          break;

        case "split":
          switch (args) {
            case "blur":
            case "sharpen":
            case "sepia":
            case "luma-component":
            case "color-correct":
              try {
                double spPercentage = 0;
                if (splitPercentage != null) {
                  spPercentage = Double.parseDouble(splitPercentage);
                  if (percentageNotInRange(spPercentage)) {
                    view.displayMessage(new Result<>(null,
                            "The split percentage provided is not in range \n", true));
                    return;
                  }
                }
                destImageName = args + "-Image-split" + noOfOperations;
                command = args + " " + currentImageName + " " + destImageName
                        + " split" + " " + spPercentage;
              } catch (NumberFormatException e) {
                view.displayMessage(new Result<>(null,
                        "The split percentage is not valid\n",
                        true));
              }
              break;
            default:
              view.displayMessage(new Result<>(null,
                      "The split view only works on specific operations\n",
                      true));
              break;
          }
          break;
        default:
          view.displayMessage(new Result<>(null,
                  "No such operation exists\n",
                  true));
          break;
      }
      if (command != null && (userInputCommand.equals("Save") || destImageName != null)) {
        this.in = new StringReader(command + "\nquit");
        execute();
        if (!Objects.equals(userInputCommand, "split") && destImageName != null) {
          currentImageName = destImageName;
        }
        command = null;
        noOfOperations += 1;
      }
    } catch (IOException e) {
      view.displayMessage(new Result<>(null,
              "Operation cannot be executed\n", true));
    }
  }
}
