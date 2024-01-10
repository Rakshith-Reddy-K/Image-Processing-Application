package model;

/**
 * This class represents a new version of ImageModel.
 * Which implements Image enhancement and compression operations.
 */
public class ImageModelV2 extends ImageModel {

  /**
   * Instantiates a new Image model V2.
   */
  public ImageModelV2() {
    super();
  }

  @Override
  public final Image compress(double percentage, String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image compressedImage = i.compress(percentage);
    putImageInMap(destName, compressedImage);
    return compressedImage.getCopy();
  }

  @Override
  public final Image histogram(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image newImg = i.histogram();
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image colorCorrect(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image newImg = i.colorCorrection();
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image levelsAdjust(int black, int mid, int white, String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image newImg = i.levelAdjustment(black, mid, white);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image blurSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);

    Image newImg = i.filtering(Util.BLUR, splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image sharpenSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);

    Image newImg = i.filtering(Util.SHARPEN, splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image sepiaSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);

    Image newImg = i.sepia(splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image lumaComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);

    Image newImg = i.getGreyComponent(Util.LUMA, splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image intensityComponentSplit(String srcName, String destName,
                                             double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);

    Image newImg = i.getGreyComponent(Util.INTENSITY, splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image valueComponentSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image newImg = i.getGreyComponent(Util.VALUE, splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image colorCorrectSplit(String srcName, String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image newImg = i.colorCorrection(splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final Image levelsAdjustSplit(int black, int mid, int white, String srcName,
                                       String destName, double splitPercentage)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image newImg = i.levelAdjustment(black, mid, white, splitPercentage);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }
}
