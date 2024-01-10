package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import model.AbstractImageModel;
import model.Image;
import view.View;


/**
 * This class represents a controller.
 * It is responsible for handling user inputs and delegating it to model.
 */
public class ImageController {

  protected final View view;
  protected Readable in;
  protected AbstractImageModel model;
  protected Result<ArrayList<Image>> result;

  /**
   * Constructs an object of image controller.
   *
   * @param model the model
   * @param in    an object of Readable interface.
   * @param view  the view
   */
  public ImageController(AbstractImageModel model, Readable in, View view) {
    this.model = model;
    this.in = in;
    this.view = view;
  }

  /**
   * Executes a continuous loop for processing user input.
   * The method reads user input(command) from the specified input source.
   * And executes the command.
   * Type quit to end the program.
   *
   * @throws IOException if IO exception occurs
   */
  public void execute() throws IOException {
    Scanner scanner = new Scanner(this.in);
    while (true) {
      if (scanner.hasNextLine()) {
        String userInput = scanner.nextLine();
        if (Objects.equals(userInput, "quit")) {
          return;
        }
        processUserInput(userInput);
      }
    }
  }

  /**
   * Execute file for processing user input.
   * The method reads user file from the specified input source.
   * And executes the command.
   *
   * @throws IOException if IO exception occurs
   */
  public void executeFile() throws IOException {
    Scanner scanner = new Scanner(this.in);
    while (scanner.hasNextLine()) {
      String userInput = scanner.nextLine();
      processUserInput(userInput);
    }
  }

  private void processUserInput(String userInput) throws IOException {
    Command userCommand = validateAndGetCommand(userInput);
    if (userCommand != null) {
      String[] inputs = userInput.split(" ");
      String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
      executeCommand(userCommand, args);
    } else {
      view.displayMessage(new Result<>(null, "Invalid Command\n", true));
    }
  }

  private void executeCommand(Command userCommand, String[] args) throws IOException {
    Image image = null;
    ArrayList<Image> images = null;
    double splitPercentage;
    try {
      switch (userCommand) {
        case RUN:
          if (args[0].endsWith(".txt")) {
            Scanner sc = new Scanner(new FileInputStream(args[0]));
            while (sc.hasNextLine()) {
              String s = sc.nextLine();
              processUserInput(s);
            }
          } else {
            throw new RuntimeException("The provided file is not a text file");
          }
          break;
        case LOAD:
          String path = args[0];
          Image newImg = ImageIOHelper.loadImageFromPath(path);
          model.loadImage(newImg, args[1]);
          image = newImg;
          break;
        case SAVE:
          String imageName = args[1];
          Image image1 = model.getImage(imageName);
          ImageIOHelper.saveImageToPath(args[0], image1);
          view.displayMessage(new Result<>(null, "Image saved successfully\n",
                  false));
          break;
        case RED_COMPONENT:
          image = model.redComponent(args[0], args[1]);
          break;
        case GREEN_COMPONENT:
          image = model.greenComponent(args[0], args[1]);
          break;
        case BLUE_COMPONENT:
          image = model.blueComponent(args[0], args[1]);
          break;
        case LUMA_COMPONENT:
          image = model.lumaComponent(args[0], args[1]);
          break;
        case INTENSITY_COMPONENT:
          image = model.intensityComponent(args[0], args[1]);
          break;
        case VALUE_COMPONENT:
          image = model.valueComponent(args[0], args[1]);
          break;
        case RGB_SPLIT:
          images = model.splitToRGB(args[0], args[1], args[2], args[3]);
          break;
        case RGB_COMBINE:
          image = model.combineRGB(args[0], args[1], args[2], args[3]);
          break;
        case BRIGHTEN:
          try {
            double increment = Double.parseDouble(args[0]);
            image = model.brighten(increment, args[1], args[2]);
          } catch (NumberFormatException e) {
            throw new NumberFormatException("The increment value provided is not valid");
          }
          break;
        case SHARPEN:
          image = model.sharpen(args[0], args[1]);
          break;
        case BLUR:
          image = model.blur(args[0], args[1]);
          break;
        case SEPIA:
          image = model.sepia(args[0], args[1]);
          break;
        case HORIZONTAL_FLIP:
          image = model.horizontalFlip(args[0], args[1]);
          break;
        case VERTICAL_FLIP:
          image = model.verticalFlip(args[0], args[1]);
          break;
        case COMPRESS:
          try {
            double percentage = Double.parseDouble(args[0]);
            if (percentageNotInRange(percentage)) {
              throw new RuntimeException("The percentage is not in range");
            }
            image = model.compress(percentage, args[1], args[2]);
          } catch (NumberFormatException e) {
            throw new NumberFormatException("The percentage value provided is not valid");
          }
          break;
        case HISTOGRAM:
          image = model.histogram(args[0], args[1]);
          break;
        case COLOR_CORRECT:
          image = model.colorCorrect(args[0], args[1]);
          break;
        case LEVELS_ADJUST:
          try {
            int b = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            int w = Integer.parseInt(args[2]);
            if (levelNotInRange(b) || levelNotInRange(m) || levelNotInRange(w) || b > m || b > w
                    || m > w) {
              throw new RuntimeException("The values b,m,w provided are not valid");
            }
            image = model.levelsAdjust(b, m, w, args[3], args[4]);
          } catch (NumberFormatException e) {
            throw new NumberFormatException("The values b,m,w provided are not valid");
          }
          break;
        case BLUR_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.blurSplit(args[0], args[1], splitPercentage);
          break;
        case SHARPEN_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.sharpenSplit(args[0], args[1], splitPercentage);
          break;
        case SEPIA_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.sepiaSplit(args[0], args[1], splitPercentage);
          break;
        case VALUE_COMPONENT_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.valueComponentSplit(args[0], args[1],
                  splitPercentage);
          break;
        case LUMA_COMPONENT_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.lumaComponentSplit(args[0], args[1],
                  splitPercentage);
          break;
        case INTENSITY_COMPONENT_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.intensityComponentSplit(args[0], args[1],
                  splitPercentage);
          break;
        case COLOR_CORRECT_SPLIT:
          splitPercentage = getSplitPercentage(args);
          image = model.colorCorrectSplit(args[0], args[1],
                  splitPercentage);
          break;
        case LEVELS_ADJUST_SPLIT:
          splitPercentage = getSplitPercentage(args);
          int b = Integer.parseInt(args[0]);
          int m = Integer.parseInt(args[1]);
          int w = Integer.parseInt(args[2]);
          if (levelNotInRange(b) || levelNotInRange(m) || levelNotInRange(w)
                  || b > m || b > w || m > w) {
            throw new RuntimeException("The values b,m,w provided are not valid");
          }
          image = model.levelsAdjustSplit(b, m, w, args[3], args[4],
                  splitPercentage);
          break;
        default:
          break;
      }
    } catch (FileNotFoundException e) {
      view.displayMessage(new Result<>(null, "File provided doesn't exist\n", true));
    } catch (Exception e) {
      view.displayMessage(new Result<>(null, e.getMessage() + "\n", true));
    }
    if (image != null) {
      Image histogram = image.histogram();
      ArrayList<Image> resultedImages = new ArrayList<>();
      resultedImages.add(image);
      resultedImages.add(histogram);
      result = new Result<>(resultedImages, "Command executed\n", false);
      view.displayResult(result);
    }
  }


  private Command validateAndGetCommand(String userInput) {
    String[] inputs = userInput.split(" ");
    for (Command command : Command.values()) {
      if (command.getCommand().equals(inputs[0])
              && command.getExpectedArgs() == inputs.length - 1) {
        return command;
      }
    }
    return null;
  }

  private double getSplitPercentage(String[] args) throws Exception {
    try {
      if (!Objects.equals(args[args.length - 2], "split")) {
        throw new RuntimeException("Invalid Command");
      }
      double splitPercentage = Double.parseDouble(args[args.length - 1]);
      if (percentageNotInRange(splitPercentage)) {
        throw new RuntimeException("The percentage is not in range");
      }
      return splitPercentage;
    } catch (NumberFormatException e) {
      throw new NumberFormatException("The percentage is not valid");
    }
  }

  protected boolean percentageNotInRange(double d) {
    return notInRange(d, 0, 100);
  }

  protected boolean notInRange(double d, double a, double b) {
    return (d < a) || (d > b);
  }

  protected boolean levelNotInRange(double d) {
    return notInRange(d, 0, 255);
  }
}
