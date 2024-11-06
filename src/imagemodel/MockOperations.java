package imagemodel;

public class MockOperations implements ExtendedOperations {

  private StringBuilder logs;

  public MockOperations(StringBuilder logs) {
    this.logs = logs;
  }

  @Override
  public ImageInterface compressImage(ImageInterface image, int percentage) {
    logs.append("Compressing image with percentage: ").append(percentage).append("%").append("\n");
    return null;
  }

  @Override
  public ImageInterface createHistogram(ImageInterface image) {
    logs.append("Creating histogram with image").append("\n");
    return null;
  }

  @Override
  public ImageInterface colorCorrect(ImageInterface image) {
    logs.append("Color corrected").append("\n");
    return null;
  }

  @Override
  public ImageInterface levelsAdjust(ImageInterface image, int b, int m, int w) {
    logs.append("Levels adjusted").append("\n");
    return null;
  }

  @Override
  public ImageInterface splitViewOperation(String[] tokens, ImageInterface image) {
    logs.append("Split view operation performed").append("\n");
    return null;
  }

  @Override
  public ImageInterface applyHorizontalFlip(ImageInterface image) {
    logs.append("Applied horizontal flip").append("\n");
    return null;
  }

  @Override
  public ImageInterface applyVerticalFlip(ImageInterface image) {
    logs.append("Applied vertical flip").append("\n");
    return null;
  }

  @Override
  public ImageInterface applyBrightness(ImageInterface image, int increment) {
    logs.append("Applied brightness").append("\n");
    return null;
  }

  @Override
  public ImageInterface applySepia(ImageInterface image) {
    logs.append("Applied sepia").append("\n");
    return null;
  }

  @Override
  public ImageInterface applyBlur(ImageInterface image) {
    logs.append("Applied blur").append("\n");
    return null;
  }

  @Override
  public ImageInterface applySharpen(ImageInterface image) {
    logs.append("Applied sharpen").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image) {
    logs.append("Visualized red component").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image) {
    logs.append("Visualized green component").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image) {
    logs.append("Visualized blue component").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeValue(ImageInterface image) {
    logs.append("Visualized value").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeIntensity(ImageInterface image) {
    logs.append("Visualized intensity").append("\n");
    return null;
  }

  @Override
  public ImageInterface visualizeLuma(ImageInterface image) {
    logs.append("Visualized luma").append("\n");
    return null;
  }

  @Override
  public ImageInterface combineRGB(ImageInterface redImage, ImageInterface greenImage, ImageInterface blueImage) {
    logs.append("Combined RGB").append("\n");
    return null;
  }
}
