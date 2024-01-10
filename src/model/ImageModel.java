package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a ImageModel which implements ImageInO and ImageManipulation operations.
 * It handles all IO and image manipulation operations.
 */
public abstract class ImageModel extends AbstractImageModel {

  /**
   * Constructs an image model.
   */
  public ImageModel() {
    super();
  }


  @Override
  public final void loadImage(Image image, String name) {
    this.putImageInMap(name, image);
  }

  @Override
  public final Image getImage(String name) throws SourceNotFoundException {
    return getImageFromMap(name).getCopy();
  }

  @Override
  public final Image redComponent(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image redComponent = i.getComponent(Util.RED);
    putImageInMap(destName, redComponent);
    return redComponent.getCopy();
  }

  @Override
  public final Image greenComponent(String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image greenComponent = i.getComponent(Util.GREEN);
    putImageInMap(destName, greenComponent);
    return greenComponent.getCopy();
  }

  @Override
  public final Image lumaComponent(String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image lumaComponent = i.getGreyComponent(Util.LUMA);
    putImageInMap(destName, lumaComponent);
    return lumaComponent.getCopy();

  }

  @Override
  public final Image intensityComponent(String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image intensityComponent = i.getGreyComponent(Util.INTENSITY);
    putImageInMap(destName, intensityComponent);
    return intensityComponent.getCopy();

  }

  @Override
  public final Image valueComponent(String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image valueComponent = i.getGreyComponent(Util.VALUE);
    putImageInMap(destName, valueComponent);
    return valueComponent.getCopy();

  }

  @Override
  public final Image blueComponent(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image blueComponent = i.getComponent(Util.BLUE);
    putImageInMap(destName, blueComponent);
    return blueComponent.getCopy();

  }

  @Override
  public final Image horizontalFlip(String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image hFlip = i.horizontalFlip();
    putImageInMap(destName, hFlip);
    return hFlip.getCopy();

  }

  @Override
  public final Image verticalFlip(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image vFlip = i.verticalFlip();
    putImageInMap(destName, vFlip);
    return vFlip.getCopy();

  }

  @Override
  public final Image blur(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image blurImage = i.filtering(Util.BLUR);
    putImageInMap(destName, blurImage);
    return blurImage.getCopy();

  }

  @Override
  public final Image sharpen(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image sharpenedImage = i.filtering(Util.SHARPEN);
    putImageInMap(destName, sharpenedImage);
    return sharpenedImage.getCopy();

  }

  @Override
  public final Image sepia(String srcName, String destName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image sepiaImg = i.sepia();
    putImageInMap(destName, sepiaImg);
    return sepiaImg.getCopy();

  }

  @Override
  public final Image brighten(double increment, String srcName, String destName)
          throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image brightenedImage = i.brighten(increment);
    putImageInMap(destName, brightenedImage);
    return brightenedImage.getCopy();

  }

  @Override
  public final Image combineRGB(String destName, String redSrcName, String greenSrcName,
                                String blueSrcName) throws SourceNotFoundException {
    Image red = getImageFromMap(redSrcName);
    Image green = getImageFromMap(greenSrcName);
    Image blue = getImageFromMap(blueSrcName);
    if (red.getHeight() != green.getHeight() || red.getHeight() != blue.getHeight()
            || red.getWidth() != green.getWidth() || red.getWidth() != blue.getWidth()) {
      throw new RuntimeException("The size of the images do not match");
    }
    Image newImg = red.combine(green, blue);
    putImageInMap(destName, newImg);
    return newImg.getCopy();
  }

  @Override
  public final ArrayList<Image> splitToRGB(String srcName, String redDestName, String greenDestName,
                                           String blueDestName) throws SourceNotFoundException {
    Image i = getImageFromMap(srcName);
    Image[] rgb = i.split();
    putImageInMap(redDestName, rgb[0]);
    putImageInMap(greenDestName, rgb[1]);
    putImageInMap(blueDestName, rgb[2]);

    ArrayList<Image> images = new ArrayList<>(List.of(rgb[0].getCopy(),
            rgb[1].getCopy(), rgb[2].getCopy()));
    return images;
  }
}
