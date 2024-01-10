package model;

import java.io.IOException;

/**
 * This is an interface for loading and saving images.
 */
public interface ImageInO {

  /**
   * Loads the given image into the map with given name.
   *
   * @param image The loaded image.
   * @param name  The name of the image.
   * @throws IOException if an I/O error occurs during the loading process.
   */
  void loadImage(Image image, String name);

  /**
   * Gets the image with the given name if exists.
   *
   * @param name the name of the image to be saved.
   * @return the image
   * @throws SourceNotFoundException if the given source doesn't exist.
   */
  Image getImage(String name) throws SourceNotFoundException;
}
