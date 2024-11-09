package imagemodel;

public class AdditionalImageOperations extends ExtendedImageOperations
        implements AdditionalOperations {

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

  private double interpolateComponent(double wx, double wy,
                                      PixelInterface[] pixels, int colorIndex) {
    return (1 - wy) * ((1 - wx) * getColorComponent(pixels[0], colorIndex)
            + wx * getColorComponent(pixels[2], colorIndex))
            + wy * ((1 - wx) * getColorComponent(pixels[1], colorIndex)
            + wx * getColorComponent(pixels[3], colorIndex));
  }

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

  @Override
  public ImageInterface applyBlur(ImageInterface image, ImageInterface maskImage) {
    ImageInterface blur = super.applyBlur(image);
    return copyFromMask(image, blur, maskImage);
  }

  @Override
  public ImageInterface applySharpen(ImageInterface image, ImageInterface maskImage) {
    ImageInterface sharpen = super.applySharpen(image);
    return copyFromMask(image, sharpen, maskImage);
  }

  @Override
  public ImageInterface applySepia(ImageInterface image, ImageInterface maskImage) {
    ImageInterface sepia = super.applySepia(image);
    return copyFromMask(image, sepia, maskImage);
  }

  @Override
  public ImageInterface visualizeLuma(ImageInterface image, ImageInterface maskImage) {
    ImageInterface luma = super.visualizeLuma(image);
    return copyFromMask(image, luma, maskImage);
  }

  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image, ImageInterface maskImage) {
    ImageInterface red = super.visualizeRedComponent(image);
    return copyFromMask(image, red, maskImage);
  }

  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image, ImageInterface maskImage) {
    ImageInterface green = super.visualizeGreenComponent(image);
    return copyFromMask(image, green, maskImage);
  }

  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image, ImageInterface maskImage) {
    ImageInterface blue = super.visualizeBlueComponent(image);
    return copyFromMask(image, blue, maskImage);
  }

  @Override
  public ImageInterface visualizeIntensity(ImageInterface image, ImageInterface maskImage) {
    ImageInterface intensity = super.visualizeIntensity(image);
    return copyFromMask(image, intensity, maskImage);
  }

  @Override
  public ImageInterface visualizeValue(ImageInterface image, ImageInterface maskImage) {
    ImageInterface value = super.visualizeValue(image);
    return copyFromMask(image, value, maskImage);
  }

  @Override
  public ImageInterface applyBrightness(ImageInterface image, int increment,
                                        ImageInterface maskImage) {
    ImageInterface brighten = super.applyBrightness(image, increment);
    return copyFromMask(image, brighten, maskImage);
  }

  private ImageInterface copyFromMask(ImageInterface image, ImageInterface transformed,
                                      ImageInterface maskImage) {
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = transformed.getPixel(x, y);
        if (isBlack(maskImage.getPixel(x, y))) {
          copy.setPixel(x, y, pixel);
        } else {
          copy.setPixel(x, y, image.getPixel(x, y));
        }
      }
    }
    return copy.deepCopyImage();
  }

  private boolean isBlack(PixelInterface pixel) {
    return pixel.getRed() == 0 && pixel.getGreen() == 0 && pixel.getBlue() == 0;
  }
}
