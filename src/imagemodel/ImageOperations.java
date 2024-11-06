package imagemodel;

import java.util.function.Function;

/**
 * The ImageOperations class contains the implementation of all the operations that can be
 * performed on the image such as horizontal/vertical flipping, brighten/darken, component
 * extraction and so on.
 */
public class ImageOperations implements Operations {

  /**
   * The applyHorizontalFlip method will flip the image horizontally.
   *
   * @param image that needs to be flipped.
   * @return object of type Image, after flipping it horizontally.
   */
  @Override
  public ImageInterface applyHorizontalFlip(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width / 2; x++) {
        PixelInterface left = image.getPixel(x, y);
        PixelInterface right = image.getPixel(width - 1 - x, y);
        copy.setPixel(x, y, right);
        copy.setPixel(width - 1 - x, y, left);
      }
    }
    return copy.deepCopyImage();
  }

  /**
   * The applyVerticalFlip method will flip the image vertically.
   *
   * @param image that needs to be flipped.
   * @return object of type Image, after flipping it vertically.
   */
  @Override
  public ImageInterface applyVerticalFlip(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height / 2; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface top = image.getPixel(x, y);
        PixelInterface bottom = image.getPixel(x, height - 1 - y);
        copy.setPixel(x, y, bottom);
        copy.setPixel(x, height - 1 - y, top);
      }
    }
    return copy.deepCopyImage();
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
  public ImageInterface applyBrightness(ImageInterface image, int increment) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int red = clamp(pixel.getRed() + increment);
        int green = clamp(pixel.getGreen() + increment);
        int blue = clamp(pixel.getBlue() + increment);
        copy.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return copy.deepCopyImage();
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
  public ImageInterface applySepia(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int red = clamp((int) (0.393 * pixel.getRed() + 0.769 * pixel.getGreen()
                + 0.189 * pixel.getBlue()));
        int green = clamp((int) (0.349 * pixel.getRed() + 0.686 * pixel.getGreen()
                + 0.168 * pixel.getBlue()));
        int blue = clamp((int) (0.272 * pixel.getRed() + 0.534 * pixel.getGreen()
                + 0.131 * pixel.getBlue()));
        copy.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return copy.deepCopyImage();
  }

  /**
   * The applyBlur method will blur the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after blurring.
   */
  @Override
  public ImageInterface applyBlur(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    double[][] blurKernel = {
            {1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}
    };
    return applyKernel(image, blurKernel);
  }

  /**
   * The applySharpen method will sharpen the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  @Override
  public ImageInterface applySharpen(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    double[][] sharpenKernel = {
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
    };
    return applyKernel(image, sharpenKernel);
  }

  /**
   * This method will extract the red component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the red component.
   */
  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    return componentHelper(image, PixelInterface::getRed);
  }

  /**
   * This method will extract the green component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the green component.
   */
  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    return componentHelper(image, PixelInterface::getGreen);
  }

  /**
   * This method will extract the blue component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the blue component.
   */
  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    return componentHelper(image, PixelInterface::getBlue);
  }

  /**
   * This method will return an image with the value component of the input image.
   *
   * @param image is the image on which the operation is to be applied.
   * @return this will return an object of type Image after applying the operation.
   */
  @Override
  public ImageInterface visualizeValue(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
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
  public ImageInterface visualizeIntensity(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
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
  public ImageInterface visualizeLuma(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    return componentHelper(image, pixel -> (int) (0.2126 * pixel.getRed()
            + 0.7152 * pixel.getGreen() + 0.0722 * pixel.getBlue()));
  }

  /**
   * Helper method that will perform component modification operations.
   *
   * @param image              to be processed.
   * @param componentExtractor operation to be performed.
   * @return image after processing.
   */
  private ImageInterface componentHelper(ImageInterface image, Function<PixelInterface,
          Integer> componentExtractor) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int value = componentExtractor.apply(pixel);
        copy.setPixel(x, y, new Pixel(value, value, value));
      }
    }
    return copy.deepCopyImage();
  }

  /**
   * This function will apply the kernel to the blur and sepia tone functions.
   *
   * @param image  on which the kernel needs to be applied.
   * @param kernel The kernel on that particular function.
   */
  private ImageInterface applyKernel(ImageInterface image, double[][] kernel) {
    if (image == null) {
      throw new NullPointerException("image is null");
    }
    int kernelHeight = kernel.length;
    int kernelWidth = kernel[0].length;
    int kernelRadiusX = kernelWidth / 2;
    int kernelRadiusY = kernelHeight / 2;
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;
        for (int ky = -kernelRadiusY; ky <= kernelRadiusY; ky++) {
          for (int kx = -kernelRadiusX; kx <= kernelRadiusX; kx++) {
            int pixelX = Math.min(Math.max(x + kx, 0), width - 1);
            int pixelY = Math.min(Math.max(y + ky, 0), height - 1);
            PixelInterface pixel = image.getPixel(pixelX, pixelY);
            double kernelValue = kernel[ky + kernelRadiusY][kx + kernelRadiusX];
            redSum += pixel.getRed() * kernelValue;
            greenSum += pixel.getGreen() * kernelValue;
            blueSum += pixel.getBlue() * kernelValue;
          }
        }
        PixelInterface pixel1 = new Pixel(clamp((int) redSum), clamp((int) greenSum),
                clamp((int) blueSum));
        copy.setPixel(x, y, pixel1);
      }
    }
    return copy.deepCopyImage();
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
  public ImageInterface combineRGB(ImageInterface redImage, ImageInterface greenImage,
                                   ImageInterface blueImage) {
    if (redImage == null || greenImage == null || blueImage == null) {
      throw new NullPointerException("image is null");
    }
    int width = redImage.getWidth();
    int height = redImage.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = redImage.getPixel(x, y).getRed();
        int green = greenImage.getPixel(x, y).getGreen();
        int blue = blueImage.getPixel(x, y).getBlue();
        copy.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return copy.deepCopyImage();
  }
}