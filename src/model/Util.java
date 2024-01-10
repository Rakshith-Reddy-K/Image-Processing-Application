package model;

/**
 * This class represents a Util class which implements image Utility functions.
 */
public class Util {
  /**
   * The constant BLUR.
   */
  public static final String BLUR = "blur";
  /**
   * The constant SHARPEN.
   */
  public static final String SHARPEN = "sharpen";
  /**
   * The constant SEPIA.
   */
  public static final String SEPIA = "sepia";
  /**
   * The constant RED.
   */
  public static final String RED = "red";
  /**
   * The constant GREEN.
   */
  public static final String GREEN = "green";
  /**
   * The constant BLUE.
   */
  public static final String BLUE = "blue";
  /**
   * The constant LUMA.
   */
  public static final String LUMA = "luma";
  /**
   * The constant INTENSITY.
   */
  public static final String INTENSITY = "intensity";
  /**
   * The constant VALUE.
   */
  public static final String VALUE = "value";

  /**
   * Get column of component double [ ].
   *
   * @param array     the array
   * @param column    the column
   * @param length    the length
   * @param component the component
   * @return the double [ ]
   */
  static double[] getColumnOfComponent(double[][][] array, int column, int length,
                                       int component) {
    double[] columnValues = new double[length];
    for (int i = 0; i < length; i++) {
      columnValues[i] = array[i][column][component];
    }
    return columnValues;
  }


  static double[] getRowOfComponent(double[][][] array, int row, int length, int component) {
    double[] rowValues = new double[length];
    for (int i = 0; i < length; i++) {
      rowValues[i] = array[row][i][component];
    }
    return rowValues;
  }

  static double[][] getFilter(String type) {
    double[][] filter;
    if (type.equals(Util.BLUR)) {
      filter = new double[][]{{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
        {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0}, {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}};
    } else {
      filter = new double[][]{
              {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
              {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
              {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
              {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
              {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}};
    }
    return filter;
  }
}
