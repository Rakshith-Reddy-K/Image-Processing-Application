package model;

import java.util.ArrayList;

/**
 * This is an interface for performing various image manipulation operations.
 */
public interface ImageManipulation {

  /**
   * Extracts the red component from the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return The red component of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image redComponent(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Extracts the green component from the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return The green component of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image greenComponent(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Extracts the luma component from the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return The luma component of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image lumaComponent(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Extracts the intensity component from the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return The intensity component of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image intensityComponent(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Extracts the value component from the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return The value component of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image valueComponent(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Extracts the blue component from the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return The blue component of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image blueComponent(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Flips the source image horizontally and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the flipped image.
   * @return The horizontally flipped image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image horizontalFlip(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Flips the source image vertically and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the flipped image.
   * @return The vertically flipped image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image verticalFlip(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Applies a blur effect to the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the blurred image.
   * @return The blurred image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image blur(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Sharpens the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the sharpened image.
   * @return The sharpened image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image sharpen(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Applies a sepia filter to the source image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the sepia-filtered image.
   * @return The sepia filtered image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image sepia(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Brightens the source image and saves it within the program.
   *
   * @param increment the increment
   * @param srcName   the name of the source image.
   * @param destName  the reference name for the brightened image.
   * @return The brightened image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image brighten(double increment, String srcName, String destName) throws SourceNotFoundException;

  /**
   * Combines the red, green, and blue components of the image.
   * And saves the combined image as a new image within the program.
   *
   * @param destName     the reference name for the combined RGB image.
   * @param redSrcName   the name of the source image for the red component.
   * @param greenSrcName the name of the source image for the green component.
   * @param blueSrcName  the name of the source image for the blue component.
   * @return The component combined image of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image combineRGB(String destName, String redSrcName, String greenSrcName, String blueSrcName)
          throws SourceNotFoundException;

  /**
   * Splits the source image into its red, green, and blue components.
   * And saves them as separate images within the program.
   *
   * @param srcName       the name of the source image.
   * @param redDestName   the reference name for the destination red component image.
   * @param greenDestName the reference name for the destination green component image.
   * @param blueDestName  the reference name for the destination blue component image.
   * @return The list of red, green, and blue component images of the source image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  ArrayList<Image> splitToRGB(String srcName, String redDestName, String greenDestName,
                              String blueDestName) throws SourceNotFoundException;

}
