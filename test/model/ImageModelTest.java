package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * A Junit class to test image model.
 */
public class ImageModelTest {
  private ImageModelV2 model;

  @Before
  public void setup() {
    model = new ImageModelV2();
    Image a = new Image(new Scanner("1 1 200 200 100 40"));
    model.loadImage(a, "a");
  }


  @Test
  public void testValidSave() {
    try {
      setup();
      model.getImage("a");
    } catch (SourceNotFoundException e) {
      fail("Should not thrown exception.");
    }
  }

  @Test
  public void testInvalidSave() {
    try {
      setup();
      model.getImage("b");
      fail("Should thrown exception.");
    } catch (SourceNotFoundException ignored) {
    }

    try {
      setup();
      model.getImage("b");
      fail("Should thrown exception.");
    } catch (SourceNotFoundException ignored) {
    }
  }

  @Test
  public void testRedComponent() {
    try {
      Image result = model.redComponent("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testGreenComponent() {
    try {
      Image result = model.greenComponent("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testBlueComponent() {
    try {
      Image result = model.blueComponent("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testLumaComponent() {
    try {
      Image result = model.lumaComponent("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testIntensityComponent() {
    try {
      Image result = model.intensityComponent("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testValueComponent() {
    try {
      Image result = model.valueComponent("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testHorizontalFlip() {
    try {
      Image result = model.horizontalFlip("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testVerticalFlip() {
    try {
      Image result = model.verticalFlip("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testBlur() {
    try {
      Image result = model.blur("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testSharpen() {
    try {
      Image result = model.sharpen("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testSplitCombineRGB() {
    try {
      ArrayList<Image> result = model.splitToRGB("a", "b", "c", "d");
      Image result2 = model.combineRGB("e", "b", "c", "d");
      assertEquals(3, result.size());
      assertNotNull(result2);
    } catch (SourceNotFoundException e) {
      fail();
    }

  }

  @Test
  public void testSepia() {
    try {
      Image result = model.sepia("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testBrighten() {
    try {
      Image result = model.brighten(50, "a", "b");
      Image result2 = model.brighten(-50, "a", "c");
      assertNotNull(result);
      assertNotNull(result2);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testCompress() {
    try {
      Image result = model.compress(50, "a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testColorCorrection() {
    try {
      Image result = model.colorCorrect("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testLevelAdjust() {
    try {
      Image result = model.levelsAdjust(20, 100, 200, "a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testHistogram() {
    try {
      Image result = model.histogram("a", "b");
      assertNotNull(result);
    } catch (SourceNotFoundException e) {
      fail();
    }
  }

  @Test
  public void testSplit() {
    try {
      ArrayList<Image> images = new ArrayList<>();
      images.add(model.blurSplit("a", "b", 20));
      images.add(model.colorCorrectSplit("a", "b", 10));
      images.add(model.intensityComponentSplit("a", "b", 10));
      images.add(model.sepiaSplit("a", "b", 10));
      images.add(model.levelsAdjustSplit(20, 100, 200, "a", "b", 30));
      images.add(model.lumaComponentSplit("a", "b", 10));
      images.add(model.lumaComponentSplit("a", "b", 10));
      images.add(model.sharpenSplit("a", "b", 70));
      for (Image result : images) {
        assertNotNull(result);
      }
    } catch (SourceNotFoundException e) {
      fail();
    }
  }
}
