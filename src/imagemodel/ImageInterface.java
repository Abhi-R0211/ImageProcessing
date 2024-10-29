package imagemodel;

/**
 * This is an interface for the Image class. It has functionalities for getting or setting the
 * pixel values, and getting the width and height of the image.
 */
public interface ImageInterface {

  /**
   * The getPixel method extracts the pixels at the given position of the image.
   *
   * @param x row index of the image.
   * @param y column index of the image.
   * @return pixel value
   */
  Pixel getPixel(int x, int y);

  /**
   * The setPixel method sets the pixel values at a specific position in the image.
   *
   * @param x     row index where the pixel is to be set.
   * @param y     column index where the pixel is to be set.
   * @param pixel values that are to be set in that position.
   */
  void setPixel(int x, int y, Pixel pixel);

  /**
   * The getWidth method is used to get the width of the Image.
   *
   * @return the width of the image as an integer.
   */
  int getWidth();

  /**
   * The getHeight function is used to get the height of the Image.
   *
   * @return the height of the image as an integer.
   */
  int getHeight();
}
