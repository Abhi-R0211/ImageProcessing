package imagemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is designed to represent images. It stores each pixel in a 2-dimensional list which
 * is of the type Pixel class. It also provides additional functionality of getting the
 * pixel value, width and height of the image.
 */
public class Image implements ImageInterface {
  private List<List<Pixel>> pixels;
  private int width;
  private int height;

  /**
   * The class constructor initializes the object such that a white image is generated as the
   * default image whenever an object of this class is created.
   *
   * @param width  of the image.
   * @param height of the image.
   * @throws IllegalArgumentException when the height or width of image is invalid.
   */
  public Image(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid image dimensions");
    }
    this.width = width;
    this.height = height;
    this.pixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(new Pixel(255, 255, 255));
      }
      pixels.add(row);
    }
  }

  /**
   * Additional constructor for the ImageCopy class.
   *
   * @param width  of the image.
   * @param height of the image.
   * @param pixels 2-D list of pixel values.
   * @throws IllegalArgumentException when the height or width of image is invalid.
   */
  public Image(int width, int height, List<List<Pixel>> pixels) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid image dimensions");
    }
    this.width = width;
    this.height = height;
    this.pixels = pixels;
  }

  /**
   * The getPixel method extracts the pixels at the given position of the image.
   *
   * @param x row index of the image.
   * @param y column index of the image.
   * @return pixel value
   */
  public PixelInterface getPixel(int x, int y) {
    return pixels.get(y).get(x);
  }

  /**
   * The getWidth method is used to get the width of the Image.
   *
   * @return the width of the image as an integer.
   */
  public int getWidth() {
    return width;
  }

  /**
   * The getHeight function is used to get the height of the Image.
   *
   * @return the height of the image as an integer.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Equals method to check whether two images are equal or not.
   *
   * @param o object to be checked for equality.
   * @return true if equal, else false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageInterface)) {
      return false;
    }
    ImageInterface actual = (ImageInterface) o;
    if (this.getWidth() != actual.getWidth() || this.getHeight() != actual.getHeight()) {
      return false;
    }
    for (int y = 0; y < this.getHeight(); y++) {
      for (int x = 0; x < this.getWidth(); x++) {
        PixelInterface expectedPixel = this.getPixel(x, y);
        PixelInterface actualPixel = actual.getPixel(x, y);
        if (expectedPixel.getRed() != actualPixel.getRed()
                || expectedPixel.getGreen() != actualPixel.getGreen()
                || expectedPixel.getBlue() != actualPixel.getBlue()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Hashcode method that complements the equals method.
   *
   * @return hashcode of the object.
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(width, height);
    for (List<Pixel> row : pixels) {
      for (PixelInterface pixel : row) {
        result += Objects.hash(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
      }
    }
    return result;
  }
}
