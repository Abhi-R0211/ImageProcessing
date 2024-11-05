package imagemodel;

public class MockImageOperations implements ExtendedOperations {

  private StringBuilder logs;

  @Override
  public ImageInterface compressImage(ImageInterface image, double threshold) {
    return null;
  }

  @Override
  public ImageInterface createHistogram(ImageInterface image) {
    logs.append("Test histogram\n");
    return null;
  }

  @Override
  public ImageInterface colorCorrect(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface levelsAdjust(ImageInterface image, int b, int m, int w) {
    return null;
  }

  @Override
  public ImageInterface splitViewOperation(String[] tokens, ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface applyHorizontalFlip(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface applyVerticalFlip(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface applyBrightness(ImageInterface image, int increment) {
    return null;
  }

  @Override
  public ImageInterface applySepia(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface applyBlur(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface applySharpen(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface visualizeRedComponent(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface visualizeGreenComponent(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface visualizeBlueComponent(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface visualizeValue(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface visualizeIntensity(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface visualizeLuma(ImageInterface image) {
    return null;
  }

  @Override
  public ImageInterface combineRGB(ImageInterface redImage, ImageInterface greenImage, ImageInterface blueImage) {
    return null;
  }
}
