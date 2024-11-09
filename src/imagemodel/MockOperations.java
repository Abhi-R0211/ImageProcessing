package imagemodel;

/**
 * Mock class for all image operations.
 */
public class MockOperations implements AdditionalOperations {

  private StringBuilder logs;

  @Override
  public ImageInterface downscaleImage(
          ImageInterface original, int targetWidth, int targetHeight) {
    logs.append("Downscaling image").append("\n");
    return null;
  }

  @Override
  public ImageInterface applyBlur(ImageInterface image, ImageInterface maskImage) {
    logs.append("Applied blur with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface applySharpen(ImageInterface image, ImageInterface maskImage) {
    logs.append("Applied sharpen with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface applySepia(ImageInterface image, ImageInterface maskImage) {
    logs.append("Applied sepia with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeLuma(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized luma with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized red with mask").append("\n");

    return null;
  }

  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized green with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized blue with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeIntensity(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized intensity with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeValue(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized value with mask").append("\n");
    return null;
  }

  @Override
  public ImageInterface applyBrightness(ImageInterface image, int increment, ImageInterface maskImage) {
    logs.append("Brightened with mask").append("\n");
    return null;
  }

  /**
   * Class constructor that initializes the logs.
   *
   * @param logs that stores the mock logs.
   */
  public MockOperations(StringBuilder logs) {
    this.logs = logs;
  }

  /**
   * Mock for compression.
   *
   * @param image      to be compressed.
   * @param percentage of compression.
   * @return null.
   */
  @Override
  public ImageInterface compressImage(ImageInterface image, int percentage) {
    logs.append("Compressing image with percentage: ").append(percentage).append("%").append("\n");
    return null;
  }

  /**
   * Mock for creating a histogram.
   *
   * @param image whose histogram is to be calculated.
   * @return null.
   */
  @Override
  public ImageInterface createHistogram(ImageInterface image) {
    logs.append("Creating histogram with image").append("\n");
    return null;
  }

  /**
   * Mock for color correcting an image.
   *
   * @param image to be color corrected.
   * @return null.
   */
  @Override
  public ImageInterface colorCorrect(ImageInterface image) {
    logs.append("Color corrected").append("\n");
    return null;
  }

  /**
   * Mock for level adjusting an image.
   *
   * @param image to be adjusted.
   * @param b     black point.
   * @param m     midpoint.
   * @param w     white point.
   * @return null.
   */
  @Override
  public ImageInterface levelsAdjust(ImageInterface image, int b, int m, int w) {
    logs.append("Levels adjusted").append("\n");
    return null;
  }

  /**
   * Mock for split operation.
   *
   * @param image  to be split.
   * @param tokens command.
   * @return null.
   */
  @Override
  public ImageInterface splitViewOperation(String[] tokens, ImageInterface image) {
    logs.append("Split view operation performed").append("\n");
    return null;
  }

  /**
   * Mock for horizontal flip.
   *
   * @param image to be flipped.
   * @return null.
   */
  @Override
  public ImageInterface applyHorizontalFlip(ImageInterface image) {
    logs.append("Applied horizontal flip").append("\n");
    return null;
  }

  /**
   * Mock for vertical flip.
   *
   * @param image to be flipped.
   * @return null.
   */
  @Override
  public ImageInterface applyVerticalFlip(ImageInterface image) {
    logs.append("Applied vertical flip").append("\n");
    return null;
  }

  /**
   * Mock for brightening or darkening an image.
   *
   * @param image     to be brightened/darkened.
   * @param increment factor of brightness
   * @return null.
   */
  @Override
  public ImageInterface applyBrightness(ImageInterface image, int increment) {
    logs.append("Applied brightness").append("\n");
    return null;
  }

  /**
   * Mock for sepia.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface applySepia(ImageInterface image) {
    logs.append("Applied sepia").append("\n");
    return null;
  }

  /**
   * Mock for blur.
   *
   * @param image to be blurred.
   * @return null.
   */
  @Override
  public ImageInterface applyBlur(ImageInterface image) {
    logs.append("Applied blur").append("\n");
    return null;
  }

  /**
   * Mock for sharpen.
   *
   * @param image to be sharpened.
   * @return null.
   */
  @Override
  public ImageInterface applySharpen(ImageInterface image) {
    logs.append("Applied sharpen").append("\n");
    return null;
  }

  /**
   * Mock for red-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image) {
    logs.append("Visualized red component").append("\n");
    return null;
  }

  /**
   * Mock for green-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image) {
    logs.append("Visualized green component").append("\n");
    return null;
  }

  /**
   * Mock for blue-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image) {
    logs.append("Visualized blue component").append("\n");
    return null;
  }

  /**
   * Mock for value-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeValue(ImageInterface image) {
    logs.append("Visualized value").append("\n");
    return null;
  }

  /**
   * Mock for intensity-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeIntensity(ImageInterface image) {
    logs.append("Visualized intensity").append("\n");
    return null;
  }

  /**
   * Mock for luma-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeLuma(ImageInterface image) {
    logs.append("Visualized luma").append("\n");
    return null;
  }

  /**
   * Mock for combining red, green and blue channels.
   *
   * @param redImage   image with red channel.
   * @param greenImage image with green channel.
   * @param blueImage  image with blue channel.
   * @return null.
   */
  @Override
  public ImageInterface combineRGB(ImageInterface redImage, ImageInterface greenImage,
                                   ImageInterface blueImage) {
    logs.append("Combined RGB").append("\n");
    return null;
  }
}
