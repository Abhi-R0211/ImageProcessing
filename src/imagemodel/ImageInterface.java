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
  PixelInterface getPixel(int x, int y);

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
