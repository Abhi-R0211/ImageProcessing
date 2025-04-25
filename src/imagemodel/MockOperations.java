package imagemodel;

import java.util.function.Function;

/**
 * Mock class for all image operations.
 */
public class MockOperations implements AdditionalOperations {

  private StringBuilder logs;
  private boolean downscale;
  private boolean blurred;
  private boolean split;
  private boolean sharpened;
  private boolean sepia;
  private boolean luma;
  private boolean red;
  private boolean green;
  private boolean blue;
  private boolean intensity;
  private boolean value;
  private boolean histogram;
  private boolean correct;
  private boolean adjust;
  private boolean horizontal;
  private boolean vertical;
  private boolean compress;

  /**
   * Class constructor.
   */
  public MockOperations() {
    downscale = false;
    blurred = false;
    split = false;
    sharpened = false;
    sepia = false;
    luma = false;
    red = false;
    green = false;
    blue = false;
    intensity = false;
    value = false;
    histogram = false;
    correct = false;
    adjust = false;
    horizontal = false;
    vertical = false;
    compress = false;
    logs = new StringBuilder();
  }

  /**
   * Mock for downscaling of Image.
   *
   * @param original     image to be downscaled.
   * @param targetWidth  of the downsized image.
   * @param targetHeight of the downsized image.
   * @return null.
   */
  @Override
  public ImageInterface downscaleImage(
          ImageInterface original, int targetWidth, int targetHeight) {
    logs.append("Downscaling image").append("\n");
    downscale = true;
    return null;
  }

  /**
   * Flag value for downsizing.
   *
   * @return boolean flag.
   */
  public boolean isDownscale() {
    return downscale;
  }

  /**
   * Mock for blur.
   *
   * @param image to be blurred.
   * @return null.
   */
  @Override
  public ImageInterface applyBlur(ImageInterface image, ImageInterface maskImage) {
    logs.append("Applied blur with mask").append("\n");
    blurred = true;
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
    blurred = true;
    return null;
  }

  /**
   * Flag value for blurring.
   *
   * @return boolean flag.
   */
  public boolean isBlurred() {
    return blurred;
  }

  /**
   * Mock for sharpen.
   *
   * @param image to be sharpened.
   * @return null.
   */
  @Override
  public ImageInterface applySharpen(ImageInterface image, ImageInterface maskImage) {
    logs.append("Applied sharpen with mask").append("\n");
    sharpened = true;
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
    sharpened = true;
    return null;
  }

  /**
   * Flag value for sharpening.
   *
   * @return boolean flag.
   */
  public boolean isSharpen() {
    return sharpened;
  }

  /**
   * Mock for sepia.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface applySepia(ImageInterface image, ImageInterface maskImage) {
    logs.append("Applied sepia with mask").append("\n");
    sepia = true;
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
    sepia = true;
    return null;
  }

  /**
   * Flag value for sepia.
   *
   * @return boolean flag.
   */
  public boolean isSepia() {
    return sepia;
  }

  /**
   * Mock for luma-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeLuma(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized luma with mask").append("\n");
    luma = true;
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
    luma = true;
    return null;
  }


  /**
   * Flag value for Luma.
   *
   * @return boolean flag.
   */
  public boolean isVisualizeLuma() {
    return luma;
  }

  /**
   * Mock for red-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized red with mask").append("\n");
    red = true;
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
    red = true;
    return null;
  }

  /**
   * Flag value for red component.
   *
   * @return boolean flag.
   */
  public boolean isRed() {
    return red;
  }

  /**
   * Mock for green-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized green with mask").append("\n");
    green = true;
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
    green = true;
    return null;
  }

  /**
   * Flag value for green component.
   *
   * @return boolean flag.
   */
  public boolean isGreen() {
    return green;
  }


  /**
   * Mock for blue-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized blue with mask").append("\n");
    blue = true;
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
    blue = true;
    return null;
  }

  /**
   * Flag value for blue component.
   *
   * @return boolean flag.
   */
  public boolean isBlue() {
    return blue;
  }

  /**
   * Mock for intensity-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeIntensity(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized intensity with mask").append("\n");
    intensity = true;
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
    intensity = true;
    return null;
  }

  /**
   * Flag value for intensity.
   *
   * @return boolean flag.
   */
  public boolean isIntensity() {
    return intensity;
  }

  /**
   * Mock for value-component.
   *
   * @param image to be processed.
   * @return null.
   */
  @Override
  public ImageInterface visualizeValue(ImageInterface image, ImageInterface maskImage) {
    logs.append("Visualized value with mask").append("\n");
    value = true;
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
    value = true;
    return null;
  }

  /**
   * Flag value for value component.
   *
   * @return boolean flag.
   */
  public boolean isValue() {
    return value;
  }

  /**
   * Mock for brightening or darkening an image.
   *
   * @param image     to be brightened/darkened.
   * @param increment factor of brightness
   * @return null.
   */
  @Override
  public ImageInterface applyBrightness(ImageInterface image, int increment,
                                        ImageInterface maskImage) {
    logs.append("Brightened with mask").append("\n");
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
    compress = true;
    return null;
  }

  /**
   * Flag value for compression.
   *
   * @return boolean flag.
   */
  public boolean isCompress() {
    return compress;
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
    histogram = true;
    return null;
  }

  /**
   * Flag value for histogram.
   *
   * @return boolean flag.
   */
  public boolean isHistogram() {
    return histogram;
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
    correct = true;
    return null;
  }

  /**
   * Flag value for color correction.
   *
   * @return boolean flag.
   */
  public boolean isCorrect() {
    return correct;
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
    adjust = true;
    return null;
  }

  /**
   * Flag value for levels adjust.
   *
   * @return boolean flag.
   */
  public boolean isAdjust() {
    return adjust;
  }

  /**
   * Mock for split operation.
   *
   * @param percentage of image to be split.
   * @param image      the input image to split and transform.
   * @param operation  lambda function that performs the necessary operation.
   * @return null.
   */
  @Override
  public ImageInterface splitViewOperation(int percentage, ImageInterface image,
                                           Function<ImageInterface, ImageInterface> operation) {
    logs.append("Split view operation performed").append("\n");
    split = true;
    return null;
  }

  /**
   * Flag value for checking split condition.
   *
   * @return boolean flag.
   */
  public boolean isSplit() {
    return split;
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
    horizontal = true;
    return null;
  }

  /**
   * Flag value for horizontal flip.
   *
   * @return boolean flag.
   */
  public boolean isHorizontalFlip() {
    return horizontal;
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
    vertical = true;
    return null;
  }

  /**
   * Flag value for vertical flip.
   *
   * @return boolean flag.
   */
  public boolean isVerticalFlip() {
    return vertical;
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
