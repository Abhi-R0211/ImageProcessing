package imagemodel;

/**
 * This is an interface for the Pixel class. It has basic getter functions for getting the RGB
 * values of a pixel.
 */
public interface PixelInterface {

  /**
   * The getRed function will retrieve the red pixel value.
   *
   * @return the red pixel value as an integer.
   */
  int getRed();

  /**
   * The getGreen function will retrieve the green pixel value.
   *
   * @return the green pixel value as an integer.
   */
  int getGreen();

  /**
   * The getBlue function will retrieve the Blue pixel value.
   *
   * @return the blue pixel value as an integer.
   */
  int getBlue();
}
