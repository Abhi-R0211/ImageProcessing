package imagemodel;

/**
 * The ImageCopyInterface provides the functionality to create a deep copy of the image
 * while offering the functionality to manually set the pixel value.
 */
public interface ImageCopyInterface {

  /**
   * The setPixel method sets the pixel values at a specific position in the image.
   *
   * @param x     row index where the pixel is to be set.
   * @param y     column index where the pixel is to be set.
   * @param pixel values that are to be set in that position.
   */
  void setPixel(int x, int y, PixelInterface pixel);

  /**
   * This method copies an image content on to another.
   *
   * @return copied image.
   */
  ImageInterface deepCopyImage();
}
