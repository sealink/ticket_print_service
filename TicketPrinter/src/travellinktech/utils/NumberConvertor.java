package travellinktech.utils;

public class NumberConvertor {

  /**
   * Finch is a fractional inch, e.g. 1/72nd of an Inch This is useful when
   * dealing with 72 dots per inch resolutions.
   *
   * @param mm The amount of mm to convert to finches
   * @return The finch equivalent of mm
   */
  public static double mm2finch(double mm) {
    return mm * 72 / 25.4;
  }

  public static int objectToInt(Object o) {
    if (o instanceof Long) {
      return ((Long) o).intValue();
      
    } else if (o instanceof Integer) {
      return ((Integer) o).intValue();

    } else if (o instanceof Double) {
      return ((Double) o).intValue();

    } else if (o instanceof Float) {
      return (int) ((Float) o).floatValue();

    } else if (o instanceof String) {
      return (int) Double.parseDouble(o.toString());

    } else {
      throw new NumberFormatException("Number formatted incorrectly");
    }
  }

  public static double objectToDouble(Object o) {
    if (o instanceof Long) {
      return ((Long) o).doubleValue();
      
    } else if (o instanceof Integer) {
      return ((Integer) o).doubleValue();

    } else if (o instanceof Double) {
      return ((Double) o).doubleValue();

    } else if (o instanceof Float) {
      return (int) ((Float) o).doubleValue();

    } else if (o instanceof String) {
      return (int) Double.parseDouble(o.toString());

    } else {
      throw new NumberFormatException("Number formatted incorrectly");
    }
  }
}
