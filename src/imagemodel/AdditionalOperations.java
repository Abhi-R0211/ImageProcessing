package imagemodel;

public interface AdditionalOperations extends ExtendedOperations {

  ImageInterface downscaleImage(ImageInterface original, int targetWidth, int targetHeight);

  ImageInterface applyBlur(ImageInterface image, ImageInterface maskImage);

  ImageInterface applySharpen(ImageInterface image, ImageInterface maskImage);

  ImageInterface applySepia(ImageInterface image, ImageInterface maskImage);

  ImageInterface visualizeLuma(ImageInterface image, ImageInterface maskImage);

  ImageInterface visualizeRedComponent(ImageInterface image, ImageInterface maskImage);

  ImageInterface visualizeGreenComponent(ImageInterface image, ImageInterface maskImage);

  ImageInterface visualizeBlueComponent(ImageInterface image, ImageInterface maskImage);

  ImageInterface visualizeIntensity(ImageInterface image, ImageInterface maskImage);

  ImageInterface visualizeValue(ImageInterface image, ImageInterface maskImage);

  ImageInterface applyBrightness(ImageInterface image, int increment, ImageInterface maskImage);
}
