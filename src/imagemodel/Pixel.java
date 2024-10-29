package imagemodel;

/**
 * This Pixel class defines the pixel data structure which will be used for various operations.
 */
public class Pixel implements PixelInterface {
  private int red;
  private int green;
  private int blue;

  /**
   * The Pixel constructor that defines the RGB values.
   *
   * @param red   pixel value.
   * @param green pixel value.
   * @param blue  pixel value.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("Invalid pixel value for red/green/blue");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * The getRed function will retrieve the red pixel value.
   *
   * @return the red pixel value as an integer.
   */
  public int getRed() {
    return red;
  }

  /**
   * The getGreen function will retrieve the green pixel value.
   *
   * @return the green pixel value as an integer.
   */
  public int getGreen() {
    return green;
  }

  /**
   * The getBlue function will retrieve the Blue pixel value.
   *
   * @return the blue pixel value as an integer.
   */
  public int getBlue() {
    return blue;
  }

  /**
   * The toString function converts the pixel values into a string.
   *
   * @return a String of pixel values.
   */
  @Override
  public String toString() {
    return "ImageModel.Pixel(" + red + ", " + green + ", " + blue + ")";
  }
}
