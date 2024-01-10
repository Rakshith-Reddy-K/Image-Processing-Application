package model;

import java.util.HashMap;


/**
 * This is an abstract class which implements ImageInO and ImageManipulation interfaces.
 */
public abstract class AbstractImageModel implements ImageManipulation, ImageInO, ImageEnhancement {
  private HashMap<String, Image> imageMap;

  /**
   * Instantiates a new Abstract image model.
   */
  public AbstractImageModel() {
    this.imageMap = new HashMap<>();
  }


  /**
   * Gets image from map.
   *
   * @param source the source image name.
   * @return the image from map
   * @throws SourceNotFoundException if source doesn't exist.
   */
  protected final Image getImageFromMap(String source) throws SourceNotFoundException {
    Image image = imageMap.get(source);
    if (image == null) {
      throw new SourceNotFoundException();
    }
    return image;
  }

  /**
   * Put image in map.
   *
   * @param destination the destination image name.
   * @param image       the image.
   */
  protected final void putImageInMap(String destination, Image image) {
    imageMap.put(destination, image);
  }
}
