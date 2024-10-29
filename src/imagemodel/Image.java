package imagemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is designed to represent images. It stores each pixel in a 2-dimensional list which
 * is of the type Pixel class. It also provides additional functionality of getting/setting the
 * pixel value, width and height of the image.
 */
public class Image implements ImageInterface {
  private List<List<Pixel>> pixels;
  private int width;
  private int height;

  /**
   * The class constructor initializes the object such that a black image is generated as the
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
        row.add(new Pixel(0, 0, 0));
      }
      pixels.add(row);
    }
  }

  /**
   * The getPixel method extracts the pixels at the given position of the image.
   *
   * @param x row index of the image.
   * @param y column index of the image.
   * @return pixel value
   */
  public Pixel getPixel(int x, int y) {
    return pixels.get(y).get(x);
  }

  /**
   * The setPixel method sets the pixel values at a specific position in the image.
   *
   * @param x     row index where the pixel is to be set.
   * @param y     column index where the pixel is to be set.
   * @param pixel values that are to be set in that position.
   */
  public void setPixel(int x, int y, Pixel pixel) {
    pixels.get(y).set(x, pixel);
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
    if (!(o instanceof Image)) {
      return false;
    }
    Image actual = (Image) o;
    if (this.getWidth() != actual.getWidth() || this.getHeight() != actual.getHeight()) {
      return false;
    }
    for (int y = 0; y < this.getHeight(); y++) {
      for (int x = 0; x < this.getWidth(); x++) {
        Pixel expectedPixel = this.getPixel(x, y);
        Pixel actualPixel = actual.getPixel(x, y);
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
      for (Pixel pixel : row) {
        result += Objects.hash(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
      }
    }
    return result;
  }
}
