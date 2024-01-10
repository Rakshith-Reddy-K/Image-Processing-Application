import java.util.Objects;

/**
 * This is a Helper class for general functions.
 */
public class Helper {

  /**
   * Checks if the command line arguments are valid.
   * Supported args are -file filename.txt.
   *
   * @param args command line arguments.
   * @return True if the args are valid otherwise false.
   */
  public static boolean checkValidArgs(String[] args) {
    if (args.length == 0) {
      return true;
    }
    if (args.length == 1) {
      return Objects.equals(args[0], "-text");
    }
    if (args.length == 2) {
      return Objects.equals(args[0], "-file") && args[1].endsWith(".txt");
    }
    return false;
  }
}
