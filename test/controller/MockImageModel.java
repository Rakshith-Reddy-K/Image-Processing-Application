package controller;

import java.util.ArrayList;
import java.util.List;

import model.AbstractImageModel;
import model.Image;
import model.SourceNotFoundException;

/**
 * This is a mock ImageModel used for testing controller in isolation.
 */
class MockImageModel extends AbstractImageModel {

  private StringBuilder log;

  private Image image;

  /**
   * Instantiates a new Mock image model.
   *
   * @param log the log
   */
  public MockImageModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Instantiates a new Mock image model.
   *
   * @param log   the log
   * @param image the image
   */
  public MockImageModel(StringBuilder log, Image image) {
    this.log = log;
    this.image = image;
  }

  @Override
  public Image redComponent(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image greenComponent(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image lumaComponent(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image intensityComponent(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image valueComponent(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image blueComponent(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image horizontalFlip(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image verticalFlip(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image blur(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image sharpen(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image sepia(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image brighten(double increment, String srcName, String destName)
          throws SourceNotFoundException {
    log.append("Increment: ").append(increment)
            .append(" Source: ").append(srcName)
            .append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image combineRGB(String destName, String redSrcName, String greenSrcName,
                          String blueSrcName) throws SourceNotFoundException {
    log.append("Destination: ").append(destName)
            .append(" Red-Source: ").append(redSrcName)
            .append(" Green-Source: ").append(greenSrcName)
            .append(" Blue-Source: ").append(blueSrcName);
    return image;
  }

  @Override
  public ArrayList<Image> splitToRGB(String srcName, String redDestName, String greenDestName,
                                     String blueDestName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Red-Destination: ").append(redDestName)
            .append(" Green-Destination: ").append(greenDestName)
            .append(" Blue-Destination: ").append(blueDestName);
    return new ArrayList<Image>(List.of(image, image, image));
  }

  @Override
  public Image compress(double percentage, String srcName, String destName)
          throws SourceNotFoundException {
    log.append("Percentage: ").append(percentage)
            .append(" Source: ").append(srcName)
            .append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image histogram(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image colorCorrect(String srcName, String destName) throws SourceNotFoundException {
    log.append("Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image levelsAdjust(int black, int mid, int white, String srcName, String destName)
          throws SourceNotFoundException {
    log.append("Black: ").append(black)
            .append(" Mid: ").append(mid)
            .append(" White: ").append(white)
            .append(" Source: ").append(srcName).append(" Destination: ").append(destName);
    return image;
  }

  @Override
  public Image blurSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image sharpenSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image sepiaSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image lumaComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image intensityComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image valueComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image colorCorrectSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    log.append("Source: ").append(srcName)
            .append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public Image levelsAdjustSplit(int black, int mid, int white, String srcName, String destName,
                                 double splitPercentage) throws SourceNotFoundException {
    log.append("Black: ").append(black)
            .append(" Mid: ").append(mid)
            .append(" White: ").append(white)
            .append(" Source: ").append(srcName).append(" Destination: ").append(destName)
            .append(" Percentage: ").append(splitPercentage);
    return image;
  }

  @Override
  public void loadImage(Image image, String name) {
    log.append("Name: ").append(name);
  }

  @Override
  public Image getImage(String name) throws SourceNotFoundException {
    log.append("Name: ").append(name);
    return image;
  }
}
