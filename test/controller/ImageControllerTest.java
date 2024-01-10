package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import model.Image;
import model.SourceNotFoundException;
import view.View;

import static org.junit.Assert.assertEquals;

/**
 * This is a JUnit test class for ImageController.
 */
public class ImageControllerTest {
  private final String loadedImageName = "Name: load-Image0";
  private MockImageModel model;
  private ImageController controller;
  private ImageGUIController guiController;
  private View view;
  private StringBuilder log;

  @Before
  public void setup() {
    log = new StringBuilder();
    Image image = new Image(new Scanner("1 1 255 200 100 40"));
    model = new MockImageModel(log, image);
  }

  @Test
  public void testExecute_InvalidCommand() {
    try {
      Reader in = new StringReader("del\nquit");
      StringBuffer output = new StringBuffer();
      view = new MockView(output);
      controller = new ImageController(model, in, view);
      controller.execute();
      assertEquals("Invalid Command\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_load() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("load test/testImages/a.png a\nquit", output);
      assertEquals("Name: a", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_load_Invalid() {
    try {
      StringBuffer output = new StringBuffer();
      view = new MockView(output);
      executeCommand("load test/testImages/aa.ppm a\nquit", output);
      assertEquals("File test/testImages/aa.ppm not found.\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_save() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("load test/testImages/a.png a\nquit", output);
      log = new StringBuilder();
      Image image = new Image(new Scanner("1 1 255 200 100 40"));
      model = new MockImageModel(log, image);
      executeCommand("save test/testImages/a_save.png a\nquit", output);
      assertEquals("Name: a", log.toString());

    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_save_NoImageFound() {
    class MockErrorImageModel extends MockImageModel {
      public MockErrorImageModel(StringBuilder log) {
        super(log);
      }

      @Override
      public Image getImage(String name) throws SourceNotFoundException {
        throw new SourceNotFoundException();
      }
    }

    model = new MockErrorImageModel(log);
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("save test/testImages/a_save.png a\nquit", output);
      assertEquals("The provided source doesn't exist\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_redComponent() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("red-component a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_greenComponent() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("green-component a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());

    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_blueComponent() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("blue-component a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_valueComponent() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("value-component a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_lumaComponent() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("luma-component a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_intensityComponent() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("intensity-component a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_horizontalFlip() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("horizontal-flip a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_verticalFlip() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("vertical-flip a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_blur() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("blur a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_sharpen() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("sharpen a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_sepia() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("sepia a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_brighten_failure() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("brighten jjj a b\nquit", output);
      assertEquals("The increment value provided is not valid\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_brighten_positiveInc() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("brighten 50 a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Increment: 50.0 Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_brighten_negativeInc() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("brighten -50 a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Increment: -50.0 Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_rgbCombine() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("rgb-combine a a-red a-green a-blue\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Destination: a Red-Source: a-red Green-Source: a-green Blue-Source: "
              + "a-blue", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }


  @Test
  public void testExecute_SourceNotException() {
    class MockErrorImageModel extends MockImageModel {
      public MockErrorImageModel(StringBuilder log) {
        super(log);
      }

      @Override
      public Image blur(String srcName, String destName) throws SourceNotFoundException {
        throw new SourceNotFoundException();
      }
    }

    model = new MockErrorImageModel(log);
    StringBuffer output = new StringBuffer();
    try {
      executeCommand("blur A b\nquit", output);
      assertEquals("", log.toString());
      assertEquals("The provided source doesn't exist\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_compress() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("compress 10 a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Percentage: 10.0 Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_compress_negativePercentage() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("compress -10 a b\nquit", output);
      assertEquals("The percentage is not in range\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_compress_GreaterThan100() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("compress 1000 a b\nquit", output);
      assertEquals("The percentage is not in range\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_histogram() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("histogram a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_colorCorrect() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("color-correct a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_levelsAdjust() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("levels-adjust 0 100 255 a b\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Black: 0 Mid: 100 White: 255 Source: a Destination: b", log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_levelsAdjustSplit() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("levels-adjust 0 100 255 a b split 20\nquit", output);
      assertEquals("Command executed\n", output.toString());
      assertEquals("Black: 0 Mid: 100 White: 255 Source: a Destination: b Percentage: 20.0",
              log.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_levelsAdjust_invalidValues() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("levels-adjust 100 0 255 a b\nquit", output);
      assertEquals("The values b,m,w provided are not valid\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_levelsAdjustSplit_invalidSplitValue() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("levels-adjust 10 50 255 a b split pp\nquit", output);
      assertEquals("The percentage is not valid\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_levelsAdjust_invalidRangeValues() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("levels-adjust 100 250 259 a b\nquit", output);
      assertEquals("The values b,m,w provided are not valid\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_levelsAdjust_invalidWhiteValues() {
    try {
      StringBuffer output = new StringBuffer();
      executeCommand("levels-adjust 10 25 2 a b\nquit", output);
      assertEquals("The values b,m,w provided are not valid\n", output.toString());
    } catch (Exception e) {
      Assert.fail("Encountered an unexpected exception");
    }
  }

  @Test
  public void testExecute_SplitCommands() {
    ArrayList<String> commands = new ArrayList<>();
    commands.add("blur a b split 10\nquit");
    commands.add("sharpen a b split 10\nquit");
    commands.add("sepia a b split 10\nquit");
    commands.add("value-component a b split 10\nquit");
    commands.add("luma-component a b split 10\nquit");
    commands.add("intensity-component a b split 10\nquit");
    commands.add("color-correct a b split 10\nquit");
    for (String command : commands) {
      try {
        StringBuffer output = new StringBuffer();
        executeCommand(command, output);
        assertEquals("Command executed\n", output.toString());
        assertEquals("Source: a Destination: b Percentage: 10.0", log.toString());
        setup();
      } catch (Exception e) {
        Assert.fail("Encountered an unexpected exception");
      }
    }
  }

  @Test
  public void testExecute_InvalidSplitCommands() {
    ArrayList<String> commands = new ArrayList<>();
    commands.add("blur a b split kk\nquit");
    commands.add("sharpen a b split l\nquit");
    commands.add("sepia a b split u\nquit");
    commands.add("value-component a b split -op\nquit");
    commands.add("luma-component a b split -i0\nquit");
    commands.add("intensity-component a b split l0\nquit");
    commands.add("color-correct a b split l0\nquit");
    for (String command : commands) {
      try {
        StringBuffer output = new StringBuffer();
        executeCommand(command, output);
        assertEquals("The percentage is not valid\n", output.toString());
        setup();
      } catch (Exception e) {
        Assert.fail("Encountered an unexpected exception");
      }
    }
  }

  @Test
  public void testExecute_InvalidSplitCommands_NotInRange() {
    ArrayList<String> commands = new ArrayList<>();
    commands.add("blur a b split -10\nquit");
    commands.add("sharpen a b split -10\nquit");
    commands.add("sepia a b split -10\nquit");
    commands.add("value-component a b split -10\nquit");
    commands.add("luma-component a b split -10\nquit");
    commands.add("intensity-component a b split -10\nquit");
    commands.add("color-correct a b split 1000\nquit");
    for (String command : commands) {
      try {
        StringBuffer output = new StringBuffer();
        executeCommand(command, output);

        assertEquals("The percentage is not in range\n", output.toString());
        setup();
      } catch (Exception e) {
        Assert.fail("Encountered an unexpected exception");
      }
    }
  }

  @Test
  public void test_ExecuteUserCommandInput_load() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    assertEquals("Name: load-Image0", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_redComponent() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("red-component", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: red-component-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_blueComponent() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("blue-component", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: blue-component-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_greenComponent() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("green-component", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: green-component-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_verticalFlip() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("vertical-flip", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: vertical-flip-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_blur() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("blur", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: blur-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_sepia() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("sepia", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: sepia-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_lumaComponent() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("luma-component", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: luma-component-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_colorCorrect() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("color-correct", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: color-correct-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_horizontalFlip() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("horizontal-flip", null, null);
    assertEquals(loadedImageName
            + "Source: load-Image0 Destination: horizontal-flip-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_compress() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("compress", "90", null);
    assertEquals(loadedImageName
            + "Percentage: 90.0 Source: load-Image0 Destination: compress-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_compressInvalid() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("compress", "900", null);
    assertEquals("Command executed\n" +
            "Compress percentage not in range\n", output.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_compressInvalid2() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("compress", "ooo", null);
    assertEquals("Command executed\n" +
            "Compress percentage is not valid\n", output.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_levelAdjustment() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("level-adjustment", "90 100 120", null);
    assertEquals(loadedImageName + "Black: 90 Mid: 100 White: 120 " +
            "Source: load-Image0 Destination: levels-adjust-Image1", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_levelAdjustmentInvalid() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("level-adjustment", "90 10 120", null);
    assertEquals("Command executed\n" +
            "The values b,m,w provided are not in range or order\n", output.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_levelAdjustmentInvalid2() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("level-adjustment", "90 100 al0", null);
    assertEquals("Command executed\n" +
            "The values b,m,w provided are not valid\n", output.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_splitBlur() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("split", "blur", "10");
    assertEquals(loadedImageName + "Source: "
            + "load-Image0 Destination: blur-Image-split1 Percentage: 10.0", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_splitSepia() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("split", "sepia", "10");
    assertEquals(loadedImageName + "Source: load-Image0 "
            + "Destination: sepia-Image-split1 Percentage: 10.0", log.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_splitBlurInvalid() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("split", "blur", "1000");
    assertEquals("Command executed\n" +
            "The split percentage provided is not in range \n", output.toString());
  }

  @Test
  public void test_ExecuteUserCommandInput_splitBlurInvalid2() {
    StringBuffer output = new StringBuffer();
    setupGUIController(output);
    guiController.executeUserCommandInput("split", "blur", "alks00");
    assertEquals("Command executed\n" +
            "The split percentage is not valid\n", output.toString());
  }

  private void executeCommand(String command, StringBuffer output) throws IOException {
    Reader in = new StringReader(command);
    view = new MockView(output);
    controller = new ImageController(model, in, view);
    controller.execute();
  }

  private void setupGUIController(StringBuffer output) {
    view = new MockView(output);
    guiController = new ImageGUIController(model, new StringReader(""), view);
    guiController.executeUserCommandInput("Load", "test/testImages/a.png", null);
  }
}
