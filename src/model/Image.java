package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An image class that represents an image. It has height, width, maxvalue and its rgb value.
 * There are methods to change the image according to what users need.
 */
public class Image {
  private final int height;
  private final int width;
  private int[][][] image;
  private int maxValue;

  private Image(int height, int width) {
    this.image = new int[height][width][3];
    this.height = height;
    this.width = width;
    this.maxValue = 0;
  }

  /**
   * Constructor on a ppm image.
   *
   * @param sc scanner that has value of ppm image
   */
  public Image(Scanner sc) {
    this.width = sc.nextInt();
    this.height = sc.nextInt();
    this.maxValue = sc.nextInt();
    this.image = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[i][j][0] = sc.nextInt();
        image[i][j][1] = sc.nextInt();
        image[i][j][2] = sc.nextInt();
      }
    }
  }

  /**
   * Constructor of png, jpg and jpeg image.
   *
   * @param img bufferedImage that store the bufferedImage input
   */
  public Image(BufferedImage img) {
    this.width = img.getWidth();
    this.height = img.getHeight();
    int max = -1;
    this.image = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgb = img.getRGB(j, i);
        image[i][j][0] = (rgb >> 16) & 0xFF;
        image[i][j][1] = (rgb >> 8) & 0xFF;
        image[i][j][2] = (rgb >> 0) & 0xFF;
        max = getMaxElement(image[i][j][0], image[i][j][1], image[i][j][2]);
      }
    }
    this.maxValue = max;
  }

  /**
   * Get th image.
   *
   * @return the image array.
   */
  public int[][][] getImage() {
    return image;
  }

  /**
   * Gets the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the maxValue of the image.
   *
   * @return the maxValue of the image.
   */
  public int getMaxValue() {
    return maxValue;
  }

  Image horizontalFlip() {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImg.image[i][width - 1 - j][k] = this.image[i][j][k];
        }
      }
    }
    newImg.maxValue = this.maxValue;
    return newImg;
  }

  Image verticalFlip() {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.arraycopy(this.image[i][j], 0, newImg.image[height - 1 - i][j], 0, 3);
      }
    }
    newImg.maxValue = this.maxValue;
    return newImg;
  }

  Image brighten(double increment) {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImg.image[i][j][k] = Math.min(255, (int) (this.image[i][j][k] + increment));
          newImg.image[i][j][k] = Math.max(0, newImg.image[i][j][k]);
        }
      }
    }
    newImg.maxValue = (int) (this.maxValue + increment);
    newImg.maxValue = Math.min(255, newImg.maxValue);
    newImg.maxValue = Math.max(0, newImg.maxValue);
    return newImg;
  }

  Image getComponent(String component) {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newImg.image[i][j][0] = 0;
        newImg.image[i][j][1] = 0;
        newImg.image[i][j][2] = 0;
        switch (component) {
          case Util.RED:
            newImg.image[i][j][0] = this.image[i][j][0];
            break;
          case Util.GREEN:
            newImg.image[i][j][1] = this.image[i][j][1];
            break;
          case Util.BLUE:
            newImg.image[i][j][2] = this.image[i][j][2];
            break;
          default:
            break;
        }
        newImg.maxValue = getMaxElement(newImg.image[i][j][0], newImg.image[i][j][1],
                newImg.image[i][j][2]);
      }
    }
    return newImg;
  }

  Image getGreyComponent(String component) {
    return this.getGreyComponent(component, 100);
  }

  Image getGreyComponent(String component, double percentage) {
    int splitIdx = (int) Math.round(width * percentage / 100);
    Image newImg = getSpiltImage(splitIdx);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < splitIdx; j++) {
        switch (component) {
          case (Util.VALUE):
            newImg.image[i][j][0] = getMaxElement(this.image[i][j][0], this.image[i][j][1],
                    this.image[i][j][2]);
            newImg.image[i][j][1] = newImg.image[i][j][0];
            newImg.image[i][j][2] = newImg.image[i][j][0];
            break;
          case (Util.LUMA):
            newImg.image[i][j][0] = (int) (this.image[i][j][0] * 0.2126
                    + this.image[i][j][1] * 0.7152
                    + this.image[i][j][2] * 0.0722);
            newImg.image[i][j][1] = newImg.image[i][j][0];
            newImg.image[i][j][2] = newImg.image[i][j][0];
            break;
          case (Util.INTENSITY):
            newImg.image[i][j][0] = (this.image[i][j][0] + this.image[i][j][1]
                    + this.image[i][j][2]) / 3;
            newImg.image[i][j][1] = newImg.image[i][j][0];
            newImg.image[i][j][2] = newImg.image[i][j][0];
            break;
          default:
            break;
        }
        newImg.maxValue = getMaxElement(newImg.image[i][j][0], newImg.image[i][j][1],
                newImg.image[i][j][2]);
      }
    }
    return populateRemainingImage(splitIdx, newImg);
  }

  Image filtering(String type) {
    return filtering(type, 100);
  }

  Image filtering(String type, double splitPercentage) {
    int splitIdx = (int) Math.round(width * splitPercentage / 100);
    Image newImg = getSpiltImage(splitIdx);
    double[][] filter = Util.getFilter(type);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < splitIdx; j++) {
        for (int k = 0; k < 3; k++) {
          double t = 0;
          for (int m = -filter.length / 2; m <= filter.length / 2; m++) {
            for (int n = -filter.length / 2; n <= filter.length / 2; n++) {
              if (i + m >= 0 && i + m < height && n + j >= 0 && n + j < width) {
                t += (image[i + m][j + n][k]
                        * filter[m + filter.length / 2][n + filter.length / 2]);
              }
            }
          }
          newImg.image[i][j][k] = (int) t;
          clampValuesToRange(newImg, i, j, k);
        }
      }
    }
    return populateRemainingImage(splitIdx, newImg);
  }

  Image sepia() {
    return sepia(100);
  }

  Image sepia(double percentage) {
    int splitIdx = (int) Math.round(width * percentage / 100);
    Image newImg = getSpiltImage(splitIdx);
    double[][] matrix = new double[][]{{0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < splitIdx; j++) {
        for (int m = 0; m < 3; m++) {
          double t = 0;
          for (int n = 0; n < 3; n++) {
            t += image[i][j][n] * matrix[m][n];
          }
          newImg.image[i][j][m] = (int) t;
          newImg.image[i][j][m] = Math.min(newImg.image[i][j][m], 255);
          newImg.maxValue = Math.max(newImg.maxValue, newImg.image[i][j][m]);
        }
      }
    }
    return populateRemainingImage(splitIdx, newImg);
  }

  Image combine(Image green, Image blue) {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        newImg.image[i][j][0] = this.image[i][j][0];
        newImg.image[i][j][1] = green.image[i][j][1];
        newImg.image[i][j][2] = blue.image[i][j][2];
        newImg.maxValue = getMaxElement(newImg.image[i][j][0], newImg.image[i][j][1],
                newImg.image[i][j][2]);
      }
    }
    return newImg;
  }

  Image[] split() {
    Image red = this.getComponent(Util.RED);
    Image green = this.getComponent(Util.GREEN);
    Image blue = this.getComponent(Util.BLUE);

    return new Image[]{red, green, blue};
  }

  /**
   * Histogram of the image.
   *
   * @return the histogram of the image.
   */
  public Image histogram() {
    int[][] freq = new int[3][256];
    int maxFrequency = colorFrequency(freq);
    BufferedImage histogram = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = histogram.createGraphics();
    g.setColor(Color.white);
    g.fillRect(0, 0, 256, 256);
    g.translate(0, 255);
    g.scale(1, -1);
    drawSingleHistogram(g, freq[0], maxFrequency, Color.RED);
    drawSingleHistogram(g, freq[1], maxFrequency, Color.GREEN);
    drawSingleHistogram(g, freq[2], maxFrequency, Color.BLUE);
    g.dispose();
    return new Image(histogram);
  }

  Image colorCorrection() {
    return colorCorrection(100);
  }

  Image colorCorrection(double percentage) {
    int[][] freq = new int[3][256];
    colorFrequency(freq);
    int[] peak = new int[3];
    int[] peakIndex = new int[3];
    for (int i = 0; i < 3; i++) {
      for (int j = 10; j < 246; j++) {
        if (peak[i] < freq[i][j]) {
          peakIndex[i] = j;
          peak[i] = freq[i][j];
        }
      }
    }
    int newPeak = (int) (peakIndex[0] + peakIndex[1] + peakIndex[2]) / 3;
    int splitIdx = (int) Math.round(width * percentage / 100);
    Image newImg = getSpiltImage(splitIdx);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < splitIdx; j++) {
        for (int k = 0; k < 3; k++) {
          newImg.image[i][j][k] = this.image[i][j][k] + newPeak - peakIndex[k];
          if (newImg.image[i][j][k] < 0) {
            newImg.image[i][j][k] = 0;
          } else if (newImg.image[i][j][k] > 255) {
            newImg.image[i][j][k] = 255;
          }
          newImg.maxValue = Math.max(newImg.image[i][j][k], newImg.maxValue);
        }
      }
    }
    return populateRemainingImage(splitIdx, newImg);
  }

  Image levelAdjustment(int b, int m, int w) {
    return levelAdjustment(b, m, w, 100);
  }

  Image levelAdjustment(int b, int m, int w, double percentage) {
    double a = b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w;
    double aa = -b * (128 - 255) + 128 * w - 255 * m;
    double ab = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    double ac = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);
    double fa = aa / a;
    double fb = ab / a;
    double fc = ac / a;
    int splitIdx = (int) Math.round(width * percentage / 100);
    Image newImg = getSpiltImage(splitIdx);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < splitIdx; j++) {
        for (int k = 0; k < 3; k++) {
          int x = image[i][j][k];
          newImg.image[i][j][k] = (int) (fa * x * x + fb * x + fc);
          clampValuesToRange(newImg, i, j, k);
        }
      }
    }
    return populateRemainingImage(splitIdx, newImg);
  }

  private void clampValuesToRange(Image newImg, int i, int j, int k) {
    if (newImg.image[i][j][k] > 255) {
      newImg.image[i][j][k] = 255;
    } else if (newImg.image[i][j][k] < 0) {
      newImg.image[i][j][k] = 0;
    }
    newImg.maxValue = Math.max(newImg.maxValue, newImg.image[i][j][k]);
  }

  private Image populateRemainingImage(int splitIdx, Image newImg) {
    for (int i = 0; i < height; i++) {
      for (int j = splitIdx; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImg.image[i][j][k] = this.image[i][j][k];
          newImg.maxValue = Math.max(newImg.maxValue, newImg.image[i][j][k]);
        }
      }
    }
    return newImg;
  }

  private int colorFrequency(int[][] frequency) {
    int max = 0;
    for (int k = 0; k < 3; k++) {
      ArrayList<Integer> freqList = new ArrayList<>(Collections.nCopies(256, 0));
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          frequency[k][image[i][j][k]]++;
          freqList.set(image[i][j][k], frequency[k][image[i][j][k]]);
        }
      }
      int maxIndex;
      int maxFreq = 0;
      int count = 256;
      while (count > 0) {
        maxFreq = Collections.max(freqList);
        maxIndex = freqList.indexOf(maxFreq);
        if (maxIndex > 10 && maxIndex < 246) {
          break;
        }
        freqList.set(maxIndex, 0);
        frequency[k][maxIndex] = 0;
        count--;
      }
      max = Math.max(max, maxFreq);
    }
    return max;
  }

  private void drawSingleHistogram(Graphics2D g, int[] histogram, int maxFrequency, Color color) {
    g.setColor(color);
    for (int i = 0; i < 255; i++) {
      int barHeight = (int) (((double) histogram[i] / maxFrequency) * (255));
      int nextBarHeight = (int) (((double) histogram[i + 1] / maxFrequency) * (255));
      g.drawLine(i, barHeight, i + 1, nextBarHeight);
    }
  }

  Image compress(double percentage) {
    int newImageDimension = 2;
    while (newImageDimension < height || newImageDimension < width) {
      newImageDimension *= 2;
    }
    double[][][] paddedImage = this.pad(newImageDimension);
    haar(paddedImage, newImageDimension);
    compressBy(paddedImage, newImageDimension, percentage);
    invHaar(paddedImage, newImageDimension);

    return unPad(paddedImage);
  }

  Image getCopy() {
    Image copyImage = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          copyImage.image[i][j][k] = this.image[i][j][k];
        }
      }
    }
    copyImage.maxValue = this.maxValue;
    return copyImage;
  }

  private double[][][] pad(int newImageDimension) {
    double[][][] paddedImage = new double[newImageDimension][newImageDimension][3];
    for (int row = 0; row < newImageDimension; row++) {
      for (int col = 0; col < newImageDimension; col++) {
        if (row >= height || col >= width) {
          paddedImage[row][col][0] = 0;
          paddedImage[row][col][1] = 0;
          paddedImage[row][col][2] = 0;
        } else {
          paddedImage[row][col][0] = this.image[row][col][0];
          paddedImage[row][col][1] = this.image[row][col][1];
          paddedImage[row][col][2] = this.image[row][col][2];
        }
      }
    }
    return paddedImage;
  }

  private double[] translate(double[] s) {
    double[] out = new double[s.length];
    for (int i = 0, j = 0; i < s.length - 1; i = i + 2, j++) {
      double av = (s[i] + s[i + 1]) / Math.sqrt(2);
      double di = (s[i] - s[i + 1]) / Math.sqrt(2);
      out[j] = av;
      out[j + s.length / 2] = di;
    }
    return out;
  }

  private double[] invert(double[] s) {
    double[] out = new double[s.length];
    for (int i = 0, j = 0; j < s.length; i++, j += 2) {
      double av = (s[i] + s[i + s.length / 2]) / Math.sqrt(2);
      double di = (s[i] - s[i + s.length / 2]) / Math.sqrt(2);
      out[j] = av;
      out[j + 1] = di;
    }
    return out;
  }

  private void haar(double[][][] src, int length) {
    int c = length;
    while (c > 1) {
      for (int k = 0; k < 3; k++) {
        for (int row = 0; row < c; row++) {
          double[] tRow = translate(Util.getRowOfComponent(src, row, c, k));
          for (int i = 0; i < c; i++) {
            src[row][i][k] = tRow[i];
          }
        }
        for (int col = 0; col < c; col++) {
          double[] tCol = translate(Util.getColumnOfComponent(src, col, c, k));
          for (int i = 0; i < c; i++) {
            src[i][col][k] = tCol[i];
          }
        }
      }
      c = c / 2;
    }
  }

  private void invHaar(double[][][] src, int length) {
    int c = 2;
    while (c <= length) {
      for (int k = 0; k < 3; k++) {
        for (int col = 0; col < c; col++) {
          double[] iCol = invert(Util.getColumnOfComponent(src, col, c, k));
          for (int i = 0; i < c; i++) {
            src[i][col][k] = iCol[i];
          }
        }
        for (int row = 0; row < c; row++) {
          double[] iRow = invert(Util.getRowOfComponent(src, row, c, k));
          for (int i = 0; i < c; i++) {
            src[row][i][k] = iRow[i];
          }
        }

      }
      c = c * 2;
    }
  }

  private Image unPad(double[][][] src) {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          newImg.image[i][j][k] = (int) Math.round(src[i][j][k]);
          clampValuesToRange(newImg, i, j, k);
        }
      }
    }
    return newImg;
  }

  private void compressBy(double[][][] src, int length, double percentage) {
    SortedSet<Double> uniqueValues = new TreeSet<>();
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < length; col++) {
        for (int k = 0; k < 3; k++) {
          uniqueValues.add(Math.abs(src[row][col][k]));
        }
      }
    }
    Double[] uniqueList = uniqueValues.toArray(new Double[0]);
    int thresholdIndex = (int) (Math.round((uniqueList.length * percentage) / 100) - 1);
    double threshold = uniqueList[thresholdIndex];
    for (int row = 0; row < length; row++) {
      for (int col = 0; col < length; col++) {
        for (int k = 0; k < 3; k++) {
          if (Math.abs(src[row][col][k]) <= threshold) {
            src[row][col][k] = 0;
          }
        }
      }
    }
  }

  private int getMaxElement(int a, int b, int c) {
    return Math.max(Math.max(a, b), c);
  }

  private Image getSpiltImage(int index) {
    Image newImg = new Image(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < index; j++) {
        for (int k = 0; k < 3; k++) {
          newImg.image[i][j][k] = this.image[i][j][k];
          newImg.maxValue = Math.max(newImg.maxValue, newImg.image[i][j][k]);
        }
      }
    }
    return newImg;
  }

}