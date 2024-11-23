package imagemodel;

import java.util.function.Function;

/**
 * This interface extends the Operations interface, providing additional image processing
 * functionalities such as compression, color correction, level adjustment, split view operation
 * and creation of a histogram.
 */
public interface ExtendedOperations extends Operations {

  /**
   * Compresses an image by applying Haar wavelet transformation and thresholding.
   *
   * @param image      the original image to be compressed.
   * @param percentage the percentage of detail to compress, between 0 and 100.
   * @throws IllegalArgumentException if the percentage is out of range or image is null.
   */
  ImageInterface compressImage(ImageInterface image, int percentage)
          throws IllegalArgumentException;

  /**
   * Creates a histogram image from the given image.
   *
   * @param image the input image to generate the histogram from.
   * @throws IllegalArgumentException if the input image is null.
   */
  ImageInterface createHistogram(ImageInterface image) throws IllegalArgumentException;

  /**
   * Adjusts color peaks to achieve a balanced color appearance across the image.
   *
   * @param image the input image to adjust.
   * @throws IllegalArgumentException if the input image is null.
   */
  ImageInterface colorCorrect(ImageInterface image) throws IllegalArgumentException;

  /**
   * Adjusts levels in an image using specified black, mid, and white values for color mapping.
   *
   * @param image the input image to adjust.
   * @param b     the black point for levels adjustment.
   * @param m     the mid-tone point for levels adjustment.
   * @param w     the white point for levels adjustment.
   * @throws IllegalArgumentException if black, mid, and white values are not in ascending order.
   * @throws IllegalArgumentException if the input image is null.
   */
  ImageInterface levelsAdjust(ImageInterface image, int b, int m, int w)
          throws IllegalArgumentException;

  /**
   * Applies a split-view operation, applying an image transformation to a specified percentage
   * of the image width.
   *
   * @param percentage of image to be split.
   * @param image      the input image to split and transform.
   * @param operation  lambda function that performs the necessary operation.
   * @throws IllegalArgumentException if the input image is null.
   * @throws IllegalArgumentException if the operation type is unsupported.
   */
  ImageInterface splitViewOperation(int percentage, ImageInterface image, Function<ImageInterface,
          ImageInterface> operation) throws IllegalArgumentException;
}
