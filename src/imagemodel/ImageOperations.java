package imagemodel;

import java.util.function.Function;

/**
 * The ImageOperations class contains the implementation of all the operations that can be
 * performed on the image such as horizontal/vertical flipping, brighten/darken, component
 * extraction and so on.
 */
public class ImageOperations implements Operations {

  /**
   * This is an internal function that copies an image content on to another.
   *
   * @param original image to be copied.
   * @return copied image.
   */
  protected Image deepCopyImage(Image original) {
    int width = original.getWidth();
    int height = original.getHeight();
    Image copy = new Image(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = original.getPixel(x, y);
        copy.setPixel(x, y, new Pixel(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
      }
    }
    return copy;
  }

  /**
   * The applyHorizontalFlip method will flip the image horizontally.
   *
   * @param image that needs to be flipped.
   * @return object of type Image, after flipping it horizontally.
   */
  @Override
  public Image applyHorizontalFlip(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image horizontal = deepCopyImage(image);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width / 2; x++) {
        Pixel left = image.getPixel(x, y);
        Pixel right = image.getPixel(width - 1 - x, y);
        horizontal.setPixel(x, y, right);
        horizontal.setPixel(width - 1 - x, y, left);
      }
    }
    return horizontal;
  }

  /**
   * The applyVerticalFlip method will flip the image vertically.
   *
   * @param image that needs to be flipped.
   * @return object of type Image, after flipping it vertically.
   */
  @Override
  public Image applyVerticalFlip(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image vertical = deepCopyImage(image);

    for (int y = 0; y < height / 2; y++) {
      for (int x = 0; x < width; x++) {
        Pixel top = image.getPixel(x, y);
        Pixel bottom = image.getPixel(x, height - 1 - y);
        vertical.setPixel(x, y, bottom);
        vertical.setPixel(x, height - 1 - y, top);
      }
    }
    return vertical;
  }

  /**
   * The applyBrightness method will brighten or darken the image depending on the increment
   * parameter received.
   *
   * @param image     whose brightness needs to be changed.
   * @param increment factor to be applied on the image.
   * @return object of type Image after brightening/darkening.
   */
  @Override
  public Image applyBrightness(Image image, int increment) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image brightened = deepCopyImage(image);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = image.getPixel(x, y);
        int red = clamp(pixel.getRed() + increment);
        int green = clamp(pixel.getGreen() + increment);
        int blue = clamp(pixel.getBlue() + increment);
        brightened.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return brightened;
  }

  /**
   * The clamp method will make sure that the pixel value of the image stays within the range of
   * 0 and 255.
   *
   * @param value takes input of the pixel value.
   * @return the clamped value.
   */
  protected int clamp(int value) {
    return Math.min(Math.max(value, 0), 255);
  }

  /**
   * The applySepia method will apply a Sepia tone to the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying a sepia tone.
   */
  @Override
  public Image applySepia(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image sepia = deepCopyImage(image);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = image.getPixel(x, y);
        int red = clamp((int) (0.393 * pixel.getRed() + 0.769 * pixel.getGreen()
                + 0.189 * pixel.getBlue()));
        int green = clamp((int) (0.349 * pixel.getRed() + 0.686 * pixel.getGreen()
                + 0.168 * pixel.getBlue()));
        int blue = clamp((int) (0.272 * pixel.getRed() + 0.534 * pixel.getGreen()
                + 0.131 * pixel.getBlue()));
        sepia.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return sepia;
  }

  /**
   * The applyBlur method will blur the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after blurring.
   */
  @Override
  public Image applyBlur(Image image) {
    double[][] blurKernel = {
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };
    Image blur = deepCopyImage(image);
    return applyKernel(blur, blurKernel);
  }

  /**
   * The applySharpen method will sharpen the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  @Override
  public Image applySharpen(Image image) {
    double[][] sharpenKernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };
    Image sharpen = deepCopyImage(image);
    return applyKernel(sharpen, sharpenKernel);
  }

  /**
   * This method will extract the red component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the red component.
   */
  @Override
  public Image visualizeRedComponent(Image image) {
    return componentHelper(image, Pixel::getRed);
  }

  /**
   * This method will extract the green component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the green component.
   */
  @Override
  public Image visualizeGreenComponent(Image image) {
    return componentHelper(image, Pixel::getGreen);
  }

  /**
   * This method will extract the blue component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the blue component.
   */
  @Override
  public Image visualizeBlueComponent(Image image) {
    return componentHelper(image, Pixel::getBlue);
  }

  /**
   * This method will return an image with the value component of the input image.
   *
   * @param image is the image on which the operation is to be applied.
   * @return this will return an object of type Image after applying the operation.
   */
  @Override
  public Image visualizeValue(Image image) {
    return componentHelper(image, pixel -> Math.max(pixel.getRed(),
            Math.max(pixel.getGreen(), pixel.getBlue())));
  }

  /**
   * This method will return an image with the intensity component of the input image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  @Override
  public Image visualizeIntensity(Image image) {
    return componentHelper(image, pixel ->
            (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3);
  }

  /**
   * This method will return an image with the luma component of the input image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  @Override
  public Image visualizeLuma(Image image) {
    return componentHelper(image, pixel -> (int) (0.2126 * pixel.getRed()
            + 0.7152 * pixel.getGreen() + 0.0722 * pixel.getBlue()));
  }

  private Image componentHelper(Image image, Function<Pixel, Integer> componentExtractor) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image resultImage = deepCopyImage(image);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = image.getPixel(x, y);
        int value = componentExtractor.apply(pixel);
        resultImage.setPixel(x, y, new Pixel(value, value, value));
      }
    }
    return resultImage;
  }

  /**
   * This function will apply the kernel to the blur and sepia tone functions.
   *
   * @param image  on which the kernel needs to be applied.
   * @param kernel The kernel on that particular function.
   */
  private Image applyKernel(Image image, double[][] kernel) {
    int kernelHeight = kernel.length;
    int kernelWidth = kernel[0].length;
    int kernelRadiusX = kernelWidth / 2;
    int kernelRadiusY = kernelHeight / 2;
    int width = image.getWidth();
    int height = image.getHeight();
    Image result = deepCopyImage(image);

    for (int y = kernelRadiusY; y < height - kernelRadiusY; y++) {
      for (int x = kernelRadiusX; x < width - kernelRadiusX; x++) {
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;

        for (int ky = -kernelRadiusY; ky <= kernelRadiusY; ky++) {
          for (int kx = -kernelRadiusX; kx <= kernelRadiusX; kx++) {
            Pixel pixel = image.getPixel(x + kx, y + ky);
            double kernelValue = kernel[ky + kernelRadiusY][kx + kernelRadiusX];
            redSum += pixel.getRed() * kernelValue;
            greenSum += pixel.getGreen() * kernelValue;
            blueSum += pixel.getBlue() * kernelValue;
          }
        }
        Pixel pixel1 = new Pixel(clamp((int) redSum), clamp((int) greenSum), clamp((int) blueSum));
        result.setPixel(x, y, pixel1);
      }
    }
    return result;
  }

  /**
   * The combineRGB method will combine the Red, Green, and Blue channels, and convert it into
   * one single image.
   *
   * @param redImage   that is used for the red-pixels of the image.
   * @param greenImage that is used for the green-pixels of the image.
   * @param blueImage  that is used for the blue-pixels of the image.
   * @return an image after combining all the three RGB Channels.
   */
  @Override
  public Image combineRGB(Image redImage, Image greenImage, Image blueImage) {
    int width = redImage.getWidth();
    int height = redImage.getHeight();
    Image combinedImage = new Image(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = redImage.getPixel(x, y).getRed();
        int green = greenImage.getPixel(x, y).getGreen();
        int blue = blueImage.getPixel(x, y).getBlue();
        combinedImage.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return combinedImage;
  }
}