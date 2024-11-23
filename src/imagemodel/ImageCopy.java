package imagemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * A copy class that implements the ImageCopyInterface. The purpose of this class is to create a
 * copy of the image that is passed as input to it, while offering the functionality to manually
 * set the pixel value to this copy image, making the Image class immutable.
 */
public class ImageCopy implements ImageCopyInterface {

  private List<List<Pixel>> pixels;
  private int width;
  private int height;

  /**
   * Class constructor which initializes with a white image upon object creation.
   *
   * @param width  of the image.
   * @param height of the image.
   */
  public ImageCopy(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid image dimensions");
    }
    this.width = width;
    this.height = height;
    this.pixels = new ArrayList<>();
    for (int i = 0; i < this.height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < this.width; j++) {
        row.add(new Pixel(255, 255, 255));
      }
      pixels.add(row);
    }
  }

  /**
   * The setPixel method sets the pixel values at a specific position in the image.
   *
   * @param x     row index where the pixel is to be set.
   * @param y     column index where the pixel is to be set.
   * @param pixel values that are to be set in that position.
   */
  public void setPixel(int x, int y, PixelInterface pixel) {
    pixels.get(y).set(x, (Pixel) pixel);
  }

  /**
   * This method copies an image content on to another.
   *
   * @return copied image.
   */
  public ImageInterface deepCopyImage() {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = pixels.get(y).get(x);
        setPixel(x, y, new Pixel(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
      }
    }
    return new Image(width, height, pixels);
  }
}
