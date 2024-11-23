package imagemodel;

import java.util.function.Function;

/**
 * Class that supports operations such as downsizing of an image to a target height and width,
 * and extends the blur, sharpen, sepia, luma, red, blue,green, intensity, value and brightness
 * operations to work on images with a masking option.
 */
public class AdditionalImageOperations extends ExtendedImageOperations
        implements AdditionalOperations {

  /**
   * Downsizes an image to a target height and width.
   *
   * @param original     image.
   * @param targetWidth  of the downsized image.
   * @param targetHeight of the downsized image.
   * @return downsized image.
   * @throws IllegalArgumentException if image is null or target dimensions > original dimensions.
   */
  @Override
  public ImageInterface downscaleImage(ImageInterface original,
                                       int targetWidth, int targetHeight) {
    if (original == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    int originalWidth = original.getWidth();
    int originalHeight = original.getHeight();
    if (targetWidth > originalWidth || targetHeight > originalHeight) {
      throw new IllegalArgumentException("Target height/width should be less than original");
    }
    ImageCopyInterface resultImage = new ImageCopy(targetWidth, targetHeight);
    double xScale = (double) originalWidth / targetWidth;
    double yScale = (double) originalHeight / targetHeight;
    for (int x = 0; x < targetWidth; x++) {
      for (int y = 0; y < targetHeight; y++) {
        double xSource = x * xScale;
        double ySource = y * yScale;
        resultImage.setPixel(x, y, interpolate(xSource, ySource, original));
      }
    }
    return resultImage.deepCopyImage();
  }

  /**
   * Bi-linearly interpolates pixel values from the input image at fractional x and y coordinates.
   *
   * @param x        the fractional x-coordinate of original image.
   * @param y        the fractional y-coordinate of original image.
   * @param original image object.
   * @return Pixel object containing the interpolated RGB values.
   */
  private Pixel interpolate(double x, double y, ImageInterface original) {
    int x1 = (int) x;
    int y1 = (int) y;
    int x2 = Math.min(x1 + 1, original.getWidth() - 1);
    int y2 = Math.min(y1 + 1, original.getHeight() - 1);
    PixelInterface[] pixels = {original.getPixel(x1, y1), original.getPixel(x1, y2),
            original.getPixel(x2, y1), original.getPixel(x2, y2)};
    double wx = x - x1;
    double wy = y - y1;

    int red = clamp((int) Math.round(interpolateComponent(wx, wy, pixels, 0)));
    int green = clamp((int) Math.round(interpolateComponent(wx, wy, pixels, 1)));
    int blue = clamp((int) Math.round(interpolateComponent(wx, wy, pixels, 2)));

    return new Pixel(red, green, blue);
  }

  /**
   * Interpolates a specific color component of a pixel based on interpolation weights.
   *
   * @param wx         the weight for the horizontal interpolation.
   * @param wy         the weight for the vertical interpolation.
   * @param pixels     an array of four surrounding pixels used in bi-linear interpolation.
   * @param colorIndex the index representing the color component.
   * @return the interpolated value for the specified color component.
   */
  private double interpolateComponent(double wx, double wy,
                                      PixelInterface[] pixels, int colorIndex) {
    return (1 - wy) * ((1 - wx) * getColorComponent(pixels[0], colorIndex)
            + wx * getColorComponent(pixels[2], colorIndex))
            + wy * ((1 - wx) * getColorComponent(pixels[1], colorIndex)
            + wx * getColorComponent(pixels[3], colorIndex));
  }

  /**
   * Retrieves a specific color component (red, green, or blue) from a pixel.
   *
   * @param pixel      the pixel from which the color component is to be retrieved.
   * @param colorIndex the index representing the color component.
   * @return the value of the specified color component.
   * @throws IllegalArgumentException if an invalid color index is provided.
   */
  private int getColorComponent(PixelInterface pixel, int colorIndex) {
    switch (colorIndex) {
      case 0:
        return pixel.getRed();
      case 1:
        return pixel.getGreen();
      case 2:
        return pixel.getBlue();
      default:
        throw new IllegalArgumentException("Invalid color index");
    }
  }

  /**
   * The applyBlur method will blur the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after blurring as per mask condition.
   */
  @Override
  public ImageInterface applyBlur(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::applyBlur);
  }

  /**
   * The applySharpen method will sharpen the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after sharpening as per mask condition.
   */
  @Override
  public ImageInterface applySharpen(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::applySharpen);
  }

  /**
   * The applySepia method will apply sepia on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after applying sepia as per mask condition.
   */
  @Override
  public ImageInterface applySepia(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::applySepia);
  }

  /**
   * The visualizeLuma method will apply luma on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after applying luma as per mask condition.
   */
  @Override
  public ImageInterface visualizeLuma(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::visualizeLuma);
  }

  /**
   * The visualizeRedComponent method will extract red-component on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after extracting red-component as per mask condition.
   */
  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::visualizeRedComponent);
  }

  /**
   * The visualizeGreenComponent method will extract green-component on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after extracting green-component as per mask condition.
   */
  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::visualizeGreenComponent);
  }

  /**
   * The visualizeBlueComponent method will extract blue-component on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after extracting blue-component as per mask condition.
   */
  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::visualizeBlueComponent);
  }

  /**
   * The visualizeIntensity method will apply intensity transformation on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after applying intensity transformation as per mask condition.
   */
  @Override
  public ImageInterface visualizeIntensity(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::visualizeIntensity);
  }

  /**
   * The visualizeValue method will apply value transformation on the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after applying value transformation as per mask condition.
   */
  @Override
  public ImageInterface visualizeValue(ImageInterface image, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, this::visualizeValue);
  }

  /**
   * The applyBrightness method will brighten/darken the image.
   *
   * @param image     on which the operation is to be applied.
   * @param maskImage that is the mask to be compared.
   * @return an object of type Image after brightening/darkening as per mask condition.
   */
  @Override
  public ImageInterface applyBrightness(ImageInterface image,
                                        int increment, ImageInterface maskImage) {
    return applyWithMask(image, maskImage, img -> super.applyBrightness(img, increment));
  }

  /**
   * Helper method that applies the masking condition on the transformed images.
   *
   * @param image          original image.
   * @param maskImage      mask image to be compared.
   * @param transformation image which is the result of image operation on the original image.
   * @return image object after masking.
   * @throws IllegalArgumentException if mask image is null.
   */
  private ImageInterface applyWithMask(ImageInterface image, ImageInterface maskImage,
                                       Function<ImageInterface, ImageInterface> transformation) {
    if (maskImage == null) {
      throw new IllegalArgumentException("Mask image cannot be null");
    }
    ImageInterface transformed = transformation.apply(image);
    int width = image.getWidth();
    int height = image.getHeight();
    int maskWidth = maskImage.getWidth();
    int maskHeight = maskImage.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = transformed.getPixel(x, y);
        if (x < maskWidth && y < maskHeight && isBlack(maskImage.getPixel(x, y))) {
          copy.setPixel(x, y, pixel);
        } else {
          copy.setPixel(x, y, image.getPixel(x, y));
        }
      }
    }
    return copy.deepCopyImage();
  }

  /**
   * Helper method that checks if the input pixel is black or not.
   *
   * @param pixel value to be checked.
   * @return true if black, else false.
   */
  private boolean isBlack(PixelInterface pixel) {
    return pixel.getRed() == 0 && pixel.getGreen() == 0 && pixel.getBlue() == 0;
  }
}