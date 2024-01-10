package model;

/**
 * This is an interface for performing various image compression and enhancement operations.
 */
public interface ImageEnhancement {

  /**
   * Compresses the image and saves it within the program.
   *
   * @param percentage the compression ratio.
   * @param srcName    the name of the source image.
   * @param destName   the reference name for the extracted image.
   * @return the compressed image.
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image compress(double percentage, String srcName, String destName) throws SourceNotFoundException;

  /**
   * Gets the histogram of the image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image histogram(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Color corrects the image and saves it within the program.
   *
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image colorCorrect(String srcName, String destName) throws SourceNotFoundException;

  /**
   * Levels adjusts the image with given ascending order of black, mid and white values.
   * and saves it within the program.
   *
   * @param black    the black value
   * @param mid      the mid-value
   * @param white    the white value
   * @param srcName  the name of the source image.
   * @param destName the reference name for the extracted image.
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image levelsAdjust(int black, int mid, int white, String srcName, String destName)
          throws SourceNotFoundException;


  /**
   * Blurs the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image blurSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Sharpens the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image sharpenSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Applies sepia filter to the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image sepiaSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Gets luma component of the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image lumaComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Gets intensity component of the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image intensityComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Gets value component of the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image valueComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Color corrects the image upto the split width and saves it within the program.
   *
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image colorCorrectSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException;

  /**
   * Levels adjusts the image with given ascending order of black, mid and white values.
   * upto the split width and saves it within the program.
   *
   * @param black           the black value
   * @param mid             the mid-value
   * @param white           the white value
   * @param srcName         the name of the source image.
   * @param destName        the reference name for the extracted image.
   * @param splitPercentage the split percentage
   * @return the image
   * @throws SourceNotFoundException If the source doesn't exist.
   */
  Image levelsAdjustSplit(int black, int mid, int white, String srcName, String destName,
                          double splitPercentage) throws SourceNotFoundException;
}
