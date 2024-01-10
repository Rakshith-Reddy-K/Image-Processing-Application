package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * A junit class that tests image class.
 */
public class ImageTest {
  private Image image;
  private int height;
  private int width;


  @Before
  public void setup() {
    BufferedImage bufferedImage = loadBufferedImage("test/testImages/a.png");
    image = new Image(bufferedImage);
    height = image.getHeight();
    width = image.getWidth();
  }

  @Test
  public void testScannerSetup() {
    Image a = new Image(new Scanner("1 1 200 200 100 40"));
    assertEquals(1, a.getWidth());
    assertEquals(1, a.getHeight());
    assertEquals(200, a.getMaxValue());
    assertEquals(200, a.getImage()[0][0][0]);
    assertEquals(100, a.getImage()[0][0][1]);
    assertEquals(40, a.getImage()[0][0][2]);
  }

  @Test
  public void testBufferedImageSetup() {
    BufferedImage bufferedImage = loadBufferedImage("test/testImages/a.png");
    Image a = new Image(bufferedImage);
    assertEquals(bufferedImage.getHeight(), a.getHeight());
    assertEquals(bufferedImage.getWidth(), a.getWidth());
  }

  @Test
  public void testVerticalFlip() {
    Image v = image.verticalFlip();
    Image vflip = new Image(loadBufferedImage("test/testImages/manhattan-small-vertical.png"));
    int[][][] argb = v.getImage();
    int[][][] brgb = vflip.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testHorizontalFlip() {
    Image h = image.verticalFlip().horizontalFlip();
    Image hflip = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-vertical-horizontal.png"));
    int[][][] argb = h.getImage();
    int[][][] brgb = hflip.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testBrighten() {
    Image b = image.brighten(50);
    Image d = image.brighten(-50);
    Image brighten = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-brighter-by-50.png"));
    Image darken = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-darker-by-50.png"));
    int[][][] argb = d.getImage();
    int[][][] brgb = darken.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
    argb = b.getImage();
    brgb = brighten.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testRedComponent() {
    Image actual = image.getComponent(Util.RED);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-red.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testBlueComponent() {
    Image actual = image.getComponent(Util.BLUE);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-blue.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testGreenComponent() {
    Image actual = image.getComponent(Util.GREEN);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-green.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testValueComponent() {
    Image actual = image.getGreyComponent(Util.VALUE);
    int[][][] argb = actual.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int m = Math.max(image.getImage()[i][j][0], image.getImage()[i][j][1]);
        m = Math.max(m, image.getImage()[i][j][2]);
        for (int k = 0; k < 3; k++) {
          assertEquals(m, argb[i][j][k]);
        }
      }
    }
  }

  @Test
  public void testIntensityComponent() {
    Image actual = image.getGreyComponent(Util.INTENSITY);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-intensity-greyscale.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testLumaComponent() {
    Image actual = image.getGreyComponent(Util.LUMA);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-luma-greyscale.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }


  @Test
  public void testBlur() {
    Image actual = image.filtering(Util.BLUR);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-blur.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testSharpen() {
    Image actual = image.filtering(Util.SHARPEN);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-sharpen.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testSepia() {
    Image actual = image.sepia();
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/manhattan-small-sepia.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testSplitCombine() {
    Image[] splitRGB = image.split();
    Image actual = splitRGB[0].combine(splitRGB[1], splitRGB[2]);
    Image desire = new Image(loadBufferedImage("test/testImages"
            + "/a.png"));
    int[][][] argb = actual.getImage();
    int[][][] brgb = desire.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 1);
        }
      }
    }
  }

  @Test
  public void testMultiOperations() {
    BufferedImage bufferedImage = loadBufferedImage("test/testImages/galaxy.png");
    image = new Image(bufferedImage);
    height = image.getHeight();
    width = image.getWidth();
    Image v = image.horizontalFlip().horizontalFlip().levelAdjustment(20, 100, 255);
    Image vflip = new Image(loadBufferedImage("test/testImages/galaxy-adjusted.png"));
    int[][][] argb = v.getImage();
    int[][][] brgb = vflip.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 20);
        }
      }
    }
  }

  @Test
  public void testCompression() {
    Image v = image.compress(50);
    Image vflip = new Image(loadBufferedImage("test/testImages/"
            + "manhattan-small-compress-50.png"));
    int[][][] argb = v.getImage();
    int[][][] brgb = vflip.getImage();
    int miss = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          if (Math.abs(argb[i][j][k] - brgb[i][j][k]) > 20) {
            miss++;
          }
        }
      }
    }
    assertTrue(miss < 300);
  }

  @Test
  public void testColorCorrection() {
    BufferedImage bufferedImage = loadBufferedImage("test/testImages/galaxy.png");
    image = new Image(bufferedImage);
    height = image.getHeight();
    width = image.getWidth();
    Image v = image.colorCorrection(0);
    Image vflip = new Image(loadBufferedImage("test/testImages/galaxy-corrected.png"));
    int[][][] argb = v.getImage();
    int[][][] brgb = vflip.getImage();
    int miss = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          if (Math.abs(argb[i][j][k] - brgb[i][j][k]) > 20) {
            miss++;
          }
        }
      }
    }
    assertTrue(miss < 100);
  }

  @Test
  public void testLevelAdjustment() {
    BufferedImage bufferedImage = loadBufferedImage("test/testImages/galaxy.png");
    image = new Image(bufferedImage);
    height = image.getHeight();
    width = image.getWidth();
    Image v = image.levelAdjustment(20, 100, 255);
    Image vflip = new Image(loadBufferedImage("test/testImages/galaxy-adjusted.png"));
    int[][][] argb = v.getImage();
    int[][][] brgb = vflip.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 20);
        }
      }
    }
  }

  @Test
  public void testHistogram() {
    Image v = image.histogram();
    assertEquals(v.getHeight(), 256);
    assertEquals(v.getWidth(), 256);
  }

  @Test
  public void testSplitView() {
    BufferedImage bufferedImage = loadBufferedImage("test/testImages/galaxy.png");
    image = new Image(bufferedImage);
    height = image.getHeight();
    width = image.getWidth();
    Image v = image.levelAdjustment(20, 100, 255, 50);
    Image vflip = new Image(loadBufferedImage("test/testImages/galaxy-adjusted.png"));
    int[][][] argb = v.getImage();
    int[][][] brgb = vflip.getImage();
    for (int i = 0; i < height; i++) {
      for (int j = width / 2; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], image.getImage()[i][j][k], 20);
        }
      }
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(argb[i][j][k], brgb[i][j][k], 20);
        }
      }
    }
  }

  private BufferedImage loadBufferedImage(String path) {
    BufferedImage bufferedImage = null;
    try {
      bufferedImage = ImageIO.read(new File(path));
    } catch (IOException e) {
      fail("Wrong path.");
    }
    return bufferedImage;
  }
}