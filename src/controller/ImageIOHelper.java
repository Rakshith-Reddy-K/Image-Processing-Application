package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;

/**
 * This is a helper for Image IO operations.
 */
public class ImageIOHelper {

  /**
   * Loads an image from the specified path.
   *
   * @param path the path to the image file.
   * @return the loaded image.
   * @throws IOException if an I/O error occurs during the loading process.
   */
  public static Image loadImageFromPath(String path) throws IOException {
    Image image = null;
    if (path.endsWith("ppm")) {
      Scanner sc = checkPPMInput(path);
      image = new Image(sc);
    } else {
      try {
        BufferedImage newImg = ImageIO.read(new File(path));
        image = new Image(newImg);
      } catch (IOException e) {
        throw new IOException("File " + path + " not found.");
      }
    }
    return image;
  }

  /**
   * This method saves the image to the specified path.
   *
   * @param path  the path to save the image file.
   * @param image the image to be saved.
   * @throws IOException if an I/O error occurs during the saving process.
   */
  public static void saveImageToPath(String path, Image image) throws IOException {
    if (image == null) {
      throw new IOException("Image doesn't exist");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] imageArray = image.getImage();
    File file = new File(path);
    if (path.endsWith("png") || path.endsWith("jpg") || path.endsWith("jpeg")) {
      BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color color = new Color(imageArray[i][j][0], imageArray[i][j][1], imageArray[i][j][2]);
          img.setRGB(j, i, color.getRGB());
        }
      }
      if (path.endsWith("jpeg")) {
        ImageIO.write(img, path.substring(path.length() - 4), file);
      } else {
        ImageIO.write(img, path.substring(path.length() - 3), file);
      }
    } else if (path.endsWith("ppm")) {
      PrintWriter outfile = new PrintWriter(path);

      outfile.println("P3");   // Ascii PPM file
      outfile.println("# " + path);
      outfile.println(width + " " + height);
      outfile.println(image.getMaxValue());

      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          for (int k = 0; k < 3; k++) {
            outfile.println(imageArray[r][c][k]);
          }
        }
      }
      outfile.close();
    } else {
      throw new IOException("Invalid saving path.");
    }
  }

  private static Scanner checkPPMInput(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (IOException e) {
      throw new IOException("File " + filename + " not found.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
    }
    return sc;
  }
}
