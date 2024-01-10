package controller;

import model.Util;

/**
 * This class represents an enum of possible commands by the user.
 * Each enum stores the command name and the number of arguments required to execute the command.
 */
public enum Command {

  /**
   * Load command.
   */
  LOAD("load", 2),
  /**
   * Save command.
   */
  SAVE("save", 2),

  /**
   * Red component command.
   */
  RED_COMPONENT("red-component", 2),
  /**
   * Green component command.
   */
  GREEN_COMPONENT("green-component", 2),
  /**
   * Blue component command.
   */
  BLUE_COMPONENT("blue-component", 2),

  /**
   * Value component command.
   */
  VALUE_COMPONENT("value-component", 2),
  /**
   * Intensity component command.
   */
  INTENSITY_COMPONENT("intensity-component", 2),
  /**
   * Luma component command.
   */
  LUMA_COMPONENT("luma-component", 2),
  /**
   * Horizontal flip command.
   */
  HORIZONTAL_FLIP("horizontal-flip", 2),
  /**
   * Vertical flip command.
   */
  VERTICAL_FLIP("vertical-flip", 2),
  /**
   * Brighten command.
   */
  BRIGHTEN("brighten", 3),
  /**
   * Rgb split command.
   */
  RGB_SPLIT("rgb-split", 4),
  /**
   * Rgb combine command.
   */
  RGB_COMBINE("rgb-combine", 4),
  /**
   * Blur command.
   */
  BLUR(Util.BLUR, 2),
  /**
   * Sharpen command.
   */
  SHARPEN(Util.SHARPEN, 2),
  /**
   * Sepia command.
   */
  SEPIA(Util.SEPIA, 2),
  /**
   * Run command.
   */
  RUN("run", 1),
  /**
   * Compress command.
   */
  COMPRESS("compress", 3),

  /**
   * Histogram command.
   */
  HISTOGRAM("histogram", 2),
  /**
   * Color correct command.
   */
  COLOR_CORRECT("color-correct", 2),
  /**
   * Levels adjust command.
   */
  LEVELS_ADJUST("levels-adjust", 5),

  /**
   * Blur split command.
   */
  BLUR_SPLIT(Util.BLUR, 4),
  /**
   * Sharpen split command.
   */
  SHARPEN_SPLIT(Util.SHARPEN, 4),
  /**
   * Sepia split command.
   */
  SEPIA_SPLIT(Util.SEPIA, 4),
  /**
   * Color correct split command.
   */
  COLOR_CORRECT_SPLIT("color-correct", 4),
  /**
   * Levels adjust split command.
   */
  LEVELS_ADJUST_SPLIT("levels-adjust", 7),
  /**
   * Value component split command.
   */
  VALUE_COMPONENT_SPLIT("value-component", 4),
  /**
   * Intensity component split command.
   */
  INTENSITY_COMPONENT_SPLIT("intensity-component", 4),
  /**
   * Luma component split command.
   */
  LUMA_COMPONENT_SPLIT("luma-component", 4);
  private final String command;
  private final int expectedArgs;

  Command(String command, int expectedArgs) {
    this.command = command;
    this.expectedArgs = expectedArgs;
  }

  /**
   * Gets command.
   *
   * @return the command
   */
  public String getCommand() {
    return command;
  }

  /**
   * Gets expected args.
   *
   * @return the expected args
   */
  public int getExpectedArgs() {
    return expectedArgs;
  }
}
